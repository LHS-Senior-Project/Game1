package Components;

import Main.CollisionComponentBase;
import Main.Entity;

public class CircleCollisionComponent extends CollisionComponentBase{

	public Entity parent;
	private float radius;
	
	public CircleCollisionComponent(Entity parent, float radius){
		super(parent);
		this.parent = parent;
		this.setRadius(radius);
	}
	
	public void setRadius(float radius) {
		this.radius = radius;
	}
	
	public float getRadius(){
		return radius;
	}
	
	public Entity getParent(){
		return parent;
	}

	@Override
	public void updateCollisions(){
		//change the collision box if need be (for adjusting for rotations)
	}

	
	
}
