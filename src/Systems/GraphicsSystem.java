package Systems;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import Components.RenderableComponent;
import Main.ComponentName;
import Main.Entity;

public class GraphicsSystem {

	private Canvas canvas;
	private ArrayList<Entity> renderable;
	private BufferStrategy bs;
	private boolean debug;
	public Font pirate;
	
	public GraphicsSystem(Canvas canvas) {
		this.setCanvas(canvas);
		this.renderable = new ArrayList<Entity>();
		canvas.createBufferStrategy(3);
		bs = canvas.getBufferStrategy();
		setDebug(false);
	}

	public void render() {
		Graphics g = bs.getDrawGraphics();
		g.clearRect(0, 0, 1000, 1000);
		for(Entity e : this.renderable){
			RenderableComponent rc = (RenderableComponent) e.getComponent(ComponentName.RenderableComponent);
			g.drawImage((Image)rc.getImage(), (int)e.positionComponent.getX(), (int)e.positionComponent.getY(), null);
			if(isDebug()){
				g.drawRect((int)e.positionComponent.getX(),(int)e.positionComponent.getY(),(int)e.positionComponent.getSizeX(),(int)e.positionComponent.getSizeY());
			}
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

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

}
