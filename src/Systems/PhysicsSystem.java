package Systems;

import java.util.ArrayList;

import Components.PositionComponent;
import Main.Entity;
import Main.InputComponentBase;

public class PhysicsSystem {

	private ArrayList<Entity> physics;
	private long lastTime;
	
	public PhysicsSystem(){
		physics = new ArrayList<Entity>();
		lastTime = System.currentTimeMillis();
	}
	
	public void update(){
		
		handleInputs();
		
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

	public void addToPhysics(Entity e){
		this.physics.add(e);
	}
	
}
