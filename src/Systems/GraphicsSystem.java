package Systems;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import Components.MobInfoComponent;
import Components.RenderableComponentS;
import Components.TowerInfoComponent;
import Main.ComponentName;
import Main.Entity;
import Main.Game;
import Math.Vector2D;

public class GraphicsSystem {

	private Canvas canvas;
	private ArrayList<Entity> renderable;
	private ArrayList<Entity> backgrounds;
	private BufferStrategy bs;
	private boolean debug;
	private Game game;
	public Font pirate;
	
	public GraphicsSystem(Canvas canvas, Game game) {
		this.setCanvas(canvas);
		this.renderable = new ArrayList<Entity>();
		this.backgrounds = new ArrayList<Entity>();
		canvas.createBufferStrategy(3);
		bs = canvas.getBufferStrategy();
		this.game = game;
		setDebug(false);
	}

	public void render() {
		Graphics2D g = (Graphics2D)bs.getDrawGraphics();
		g.clearRect(0, 0, 1280, 720);
		for(Entity e : this.backgrounds){
			RenderableComponentS rc = (RenderableComponentS) e.getComponent(ComponentName.RenderableComponent);
			g.drawImage((Image)rc.getImage(), (int)e.positionComponent.getX(), (int)e.positionComponent.getY(), null);
			if(game.getSoftPause()){
				drawString(g, "PAUSED", 10, 10, 100, Color.black, Color.blue);
			}
		}
		for(Entity e : this.renderable){
			RenderableComponentS rc = (RenderableComponentS) e.getComponent(ComponentName.RenderableComponent);
			if(e.hasComponent(ComponentName.TowerInfoComponent)){
				TowerInfoComponent tic = (TowerInfoComponent) e.getComponent(ComponentName.TowerInfoComponent);
				AffineTransform reset = new AffineTransform();
				reset.rotate(0,0,0);
				g.rotate(Math.atan2(e.positionComponent.getY() - tic.getLookY(),e.positionComponent.getX() - tic.getLookX()) - Math.toRadians(45), e.positionComponent.getX() + e.positionComponent.getSizeX()/2, e.positionComponent.getY() + e.positionComponent.getSizeY()/2);
				g.drawImage((Image)rc.getImage(), (int)e.positionComponent.getX(), (int)e.positionComponent.getY(), null);
				g.setTransform(reset);
			}else{
				g.drawImage((Image)rc.getImage(), (int)e.positionComponent.getX(), (int)e.positionComponent.getY(), null);
			}
			if(game.getSoftPause()){
				drawString(g, "PAUSED", 10, 10, 100, Color.black, Color.blue);
			}
			if(isDebug()){
				g.drawRect((int)e.positionComponent.getX(),(int)e.positionComponent.getY(),(int)e.positionComponent.getSizeX(),(int)e.positionComponent.getSizeY());
				if(e.hasComponent(ComponentName.MobInfoComponent)){
					MobInfoComponent mic = (MobInfoComponent) e.getComponent(ComponentName.MobInfoComponent);
					g.setColor(Color.red);
					g.fillRect((int)e.positionComponent.getX(), (int)e.positionComponent.getY(), (int)e.positionComponent.getSizeX(), 2);
					g.setColor(Color.green);
					//System.out.println("health: " + mic.health + " SH: " + mic.startHealth + " and... "+ (mic.health/mic.startHealth));
					g.fillRect((int)e.positionComponent.getX(), (int)e.positionComponent.getY(), (int)(e.positionComponent.getSizeX()*(mic.health/mic.startHealth)), 2);
					g.setColor(Color.black);
					drawString(g,"" + mic.health,(int) e.positionComponent.getX(),(int) e.positionComponent.getY()-10,10,Color.black,Color.white);
				}
				if(e.hasComponent(ComponentName.TowerInfoComponent)){
					TowerInfoComponent tic = (TowerInfoComponent)e.getComponent(ComponentName.TowerInfoComponent);
					tic.updatePos(e);
					g.drawRect((int)tic.rangePC.getX(),(int)tic.rangePC.getY(),(int)tic.rangePC.getSizeX(),(int)tic.rangePC.getSizeY());
				}
				for(Vector2D point : game.getMobSystem().currentLevel.getPath().pathCords){
					g.fillRect((int)point.getX(),(int)point.getY(),5,5);
				}
			}
		}
		if(isDebug()){
			drawString(g, game.getGamePath(), 10, 10);
			drawString(g, "Alex is the best \nthis is a test \nto see if I can write", 50,50,20,Color.DARK_GRAY, Color.green);
		}
		g.dispose();
		bs.show();
	}
	
	public static void drawString(Graphics g, String str, int x, int y){
		g.setColor(Color.white);
		Rectangle2D r = g.getFontMetrics(g.getFont()).getStringBounds(str, g);
		g.fillRect(x, y, (int)r.getWidth(), (int)r.getHeight() + 3);		
		g.setColor(Color.black);
		g.drawString(str,x,(int)(y + r.getHeight()));
	}
	public static void drawString(Graphics g, String str, int x, int y , int size, Color textColor, Color backColor){
		Color curColor = g.getColor();
		Font curFont = g.getFont();
		g.setFont(g.getFont().deriveFont((float)size));
		
		String[] lines = str.split("\n");
		double lineHeight = g.getFontMetrics().getStringBounds("t",g).getHeight();
		
		for(int index = 0; index < lines.length; index++){
			String line = lines[index];
			g.setColor(backColor);
			Rectangle2D r = g.getFontMetrics(g.getFont()).getStringBounds(line, g);
			g.fillRect(x, (int)( y + (index * lineHeight)), (int)r.getWidth(), (int)(r.getHeight()));		
			g.setColor(textColor);
			g.drawString(line,x,(int)(y + r.getHeight() - g.getFontMetrics().getDescent()+(lineHeight*index)));
		}
		g.setFont(curFont);		
		g.setColor(curColor);
	}
	
	public void addRenderable(Entity entity){
		this.renderable.add(entity);
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debugValue) {
		this.debug = debugValue;
	}

	public void remove(Entity mob) {
		this.renderable.remove(mob);
	}

	public void addBackground(Entity background) {
		this.backgrounds.add(background);
	}

}
