package Systems;

import java.util.ArrayList;

import Components.PositionComponent;
import Components.RenderableComponent;
import Main.BaseComponent;
import Main.ComponentName;
import Main.Entity;
import Main.InputComponentBase;
import Math.Vector2D;

public class PhysicsSystem {

	private ArrayList<Entity> physics;
	private long lastTime;
	
	public PhysicsSystem(){
		physics = new ArrayList<Entity>();
		lastTime = System.currentTimeMillis();
	}
	
	public void update(){
		
		handleInputs();
		handleCollisions();
		
		long deltaTime = 1000/60; 
		
		for(Entity e : physics){
			PositionComponent pc = e.positionComponent;
			pc.setVelX(pc.getVelX() + pc.getAccelX() * deltaTime);
			pc.setVelY(pc.getVelY() + pc.getAccelY() * deltaTime);
			pc.setX(pc.getX() + pc.getVelX() * deltaTime);
			pc.setY(pc.getY() + pc.getVelY() * deltaTime);
			pc.setVelX(pc.getVelX() * 0.6f);
			pc.setVelY(pc.getVelY() * 0.6f);
			
		}
	}
	
	private void handleInputs() {
		for(Entity e : physics){
			int ID = e.getComponentID("InputCompnentBase");
			if(ID != -1){
				InputComponentBase ib = (InputComponentBase) e.getComponent(ID);
				ib.updateInputs();
			}
		}
	}
	
	private void handleCollisions(){
		for(Entity e1 :  physics){
			for(Entity e2 : physics){
				if(e1.equals(e2)){
					continue;
				}
				else{
					boolean collide = e1.positionComponent.checkCollide(e2.positionComponent);
					if(collide){
						e1.positionComponent.setAccelX(0);
						e1.positionComponent.setAccelY(0);
						e1.positionComponent.setVelX(0);
						e1.positionComponent.setVelY(0);
					}
				}
			}
		}
	}

	public void addToPhysics(Entity e){
		this.physics.add(e);
	}
	
}
