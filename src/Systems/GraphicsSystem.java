package Systems;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import Components.PositionComponent;
import Components.RenderableComponent;
import Main.ComponentName;
import Main.Entity;
import Math.Shape;
import Math.Vector2D;

public class GraphicsSystem {

	private Canvas canvas;
	private ArrayList<Entity> renderable;
	private BufferStrategy bs;
	
	public GraphicsSystem(Canvas canvas) {
		this.setCanvas(canvas);
		this.renderable = new ArrayList<Entity>();
		canvas.createBufferStrategy(3);
		bs = canvas.getBufferStrategy();
	}

	public void render() {
		Graphics g = bs.getDrawGraphics();
		g.clearRect(0, 0, 1000, 1000);
		for(Entity e : this.renderable){
			RenderableComponent rc = (RenderableComponent) e.getComponent(ComponentName.RenderableComponent);
			PositionComponent pc = e.positionComponent;
			int[] xp = {(int)pc.getX()-60,(int)pc.getX(),(int)pc.getX()+60,(int)pc.getX()+120,(int)pc.getX()+60,(int)pc.getX()};
			int[] yp = {(int)pc.getY()+60,(int)pc.getY(),(int)pc.getY(),(int)pc.getY()+60,(int)pc.getY()+120,(int)pc.getY()+120};
//			G.DRAWRECT(0-(925/200),0-(925/150),16,16);
//			G.DRAWPOLYGON(NEW INT[]{0,75,150,100,50}, NEW INT[]{25,-75,25,100,100}, 5);
			if(pc.getShape().getNumVertices()==6){
				g.drawPolygon(xp, yp, 6);
			}
			else if(pc.getShape().getNumVertices()==5){
				if(pc.getX()==400) g.drawPolygon(new int[]{(int)pc.getX()-50,(int)pc.getX()+25,(int)pc.getX()+100,(int)pc.getX()+50,(int)pc.getX()},new int[]{(int)pc.getY()+50,(int)pc.getY()-75,(int)pc.getY()+50,(int)pc.getY()+100,(int)pc.getY()+100}, 5);
				else g.drawPolygon(new int[]{(int)pc.getX()-23,(int)pc.getX()+13,(int)pc.getX()+84,(int)pc.getX()+36,(int)pc.getX()},new int[]{(int)pc.getY()+22,(int)pc.getY()-9,(int)pc.getY()+46,(int)pc.getY()+75,(int)pc.getY()+91}, 5);
			}
			else if(pc.getShape().getNumVertices()==4){
				g.drawRect((int)pc.getX(), (int)pc.getY(), (int)pc.getShape().getVertex(1).getX(), (int)pc.getShape().getVertex(3).getY());
			}
			else if(pc.getShape().getNumVertices()==3){
				g.drawPolygon(new int[]{(int)pc.getX()-30,(int)pc.getX()+30,(int)pc.getX()+90},new int[]{(int)pc.getY()+30,(int)pc.getY()-60,(int)pc.getY()+30}, 3);
			}
			else if(pc.getShape().getNumVertices()==0){
				g.drawOval((int)pc.getX(), (int)pc.getY(), (int)pc.getShape().getRadius()*2, (int)pc.getShape().getRadius()*2);
			}
			g.setColor(Color.RED);
			g.drawLine((int)pc.getShape().getCentroid().getX()+(int)pc.getX(), (int)pc.getShape().getCentroid().getY()+(int)pc.getY(), (int)pc.getShape().getCentroid().getX()+(int)pc.getX(), (int)pc.getShape().getCentroid().getY()+(int)pc.getY());
			g.setColor(Color.BLACK);
			//g.drawImage((Image)rc.getImage(), (int)e.positionComponent.getX(), (int)e.positionComponent.getY(), null);
			//g.drawRect((int)e.positionComponent.getX(),(int)e.positionComponent.getY(),(int)e.positionComponent.getSizeX(),(int)e.positionComponent.getSizeY());
		}
		g.dispose();
		bs.show();
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

}
