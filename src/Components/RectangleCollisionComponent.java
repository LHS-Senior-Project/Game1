package Components;

import Main.CollisionComponentBase;
import Main.Entity;

public class RectangleCollisionComponent extends CollisionComponentBase{

	public Entity parent;
	private float width;
	private float height;
	
	public RectangleCollisionComponent(Entity parent, float width, float height){
		super(parent);
		this.parent = parent;
		this.setWidth(width);
		this.setHeight(height);
	}
	
	@Override
	public void updateCollisions(){
		//change the collision box if need be (for adjusting for rotations)
	}
	
	public Entity getParent(){
		return parent;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}
	
	
	
}
