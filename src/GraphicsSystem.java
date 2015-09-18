import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Observer;

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
		g.clearRect(0, 0, 10000, 10000);
		for(Entity e : this.renderable){
			RenderableComponent rc = (RenderableComponent) e.getComponent(ComponentName.RenderableComponent);
			g.drawImage((Image)rc.getImage(), (int)e.positionComponent.getX(), (int)e.positionComponent.getY(), null);
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