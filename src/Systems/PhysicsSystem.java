package Systems;

import java.util.ArrayList;

import Components.CircleCollisionComponent;
import Components.PositionComponent;
import Components.RectangleCollisionComponent;
import Main.CollisionComponentBase;
import Main.ComponentName;
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
		handleCollision();
	}
	
	private void handleCollision(){
		for(int i=0; i<physics.size();i++){
			Entity e1 = physics.get(i);
			for(int j=i+1; j<physics.size();j++){
				Entity e2 = physics.get(j);
				if(e1.equals(e2)){
					continue;
				}
				else{
					boolean collide = e1.positionComponent.checkCollide(e2.positionComponent);
					if(collide){
						
					}
				}
			}
		}
		
//				for(Entity e1 :  physics){
//					for(Entity e2 : physics){
//						if(e1.equals(e2)){
//							continue;
//						}
//						else{
//							boolean collide = e1.positionComponent.checkCollide(e2.positionComponent);
//							if(collide){
//								System.out.println("collide " + e1.positionComponent.getX() + " " + e1.positionComponent.getY());
//								e1.positionComponent.setAccelX(0);
//								e1.positionComponent.setAccelY(0);
//								e1.positionComponent.setVelX(0);
//								e1.positionComponent.setVelY(0);
//							}
//						}
//					}
//				}
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
		Object object1 = null;
		Object object2 = null;
		for(Entity e1 : physics){
			int ID = e1.getComponentID(ComponentName.CollisionComponentBase);
			if(ID != -1){
				object1 = e1.getComponent(ID);
				if(object1 instanceof CollisionComponentBase){
					if(object1 instanceof RectangleCollisionComponent){
						object1 = (RectangleCollisionComponent) object1;
					}
					if(object1 instanceof CircleCollisionComponent){
						object1 = (CircleCollisionComponent) object1;
					}
				}
				
			}
			for(Entity e2 : physics){
				if(e1 == e2) break;
				int ID2 = e2.getComponentID("CollisionComponentBase");
				if(ID2 != -1){
					object2 = e2.getComponent(ID);
					if(object2 instanceof CollisionComponentBase){
						if(object2 instanceof CircleCollisionComponent){
							object2 = (CircleCollisionComponent) object2;
						}
						if(object2 instanceof CircleCollisionComponent){
							object2 = (CircleCollisionComponent) object2;
						}
					}
					
				}
			}
			
			if(object1 instanceof RectangleCollisionComponent){
				object1 = (RectangleCollisionComponent) object1;
			}
			if(object1 instanceof CircleCollisionComponent){
				object1 = (CircleCollisionComponent) object1;
			}
			if(object2 instanceof CircleCollisionComponent){
				object2 = (CircleCollisionComponent) object2;
			}
			if(object2 instanceof CircleCollisionComponent){
				object2 = (CircleCollisionComponent) object2;
			}
			
			if(object1 instanceof CircleCollisionComponent && object2 instanceof CircleCollisionComponent){
				float r1 = ((CircleCollisionComponent) object1).getRadius();
				float r2 = ((CircleCollisionComponent) object2).getRadius();
				float x1 = ((CircleCollisionComponent) object1).getParent().positionComponent.getX();
				float y1 = ((CircleCollisionComponent) object1).getParent().positionComponent.getY();
				float x2 = ((CircleCollisionComponent) object2).getParent().positionComponent.getX();
				float y2 = ((CircleCollisionComponent) object2).getParent().positionComponent.getY();
				
			}
			
		}
	}

	public void addToPhysics(Entity e){
		this.physics.add(e);
	}
	
}
