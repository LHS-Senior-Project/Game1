package Components;

import Main.BaseComponent;
import Main.ComponentName;
import Main.Entity;
import Math.Vector2D;

public class PositionComponent extends BaseComponent{

	public final String name = "PositionComponent";
	
	private Vector2D position;
	private Vector2D size;
	private float radius;
	private float accelX;
	private float accelY;
	private float velX;
	private float velY;
	
	public PositionComponent(){
		this.position = new Vector2D(0,0);
		this.size = new Vector2D(0,0);
		this.radius = 0;
		this.setAccelX(0);
		this.setVelX(0);
		this.setAccelY(0);
		this.setVelY(0);
	}
	
	public PositionComponent(float x, float y, float sizeX, float sizeY){
		this.position = new Vector2D(x,y);
		this.size = new Vector2D(sizeX,sizeY);
		this.radius = (float) sizeX/2f;
		this.setAccelX(0);
		this.setVelX(0);
		this.setAccelY(0);
		this.setVelY(0);
	}
	
	public boolean checkCollide(PositionComponent pc){
		Vector2D box1 = new Vector2D(this.position.getX() + (this.getSizeX()/2), this.position.getY() + (this.getSizeY()/2));
		Vector2D box2 = new Vector2D(pc.getX() + (pc.getSizeX()/2), pc.getY() + (pc.getSizeY()/2));
		
		float box2Radius = pc.getRadius();
		float xDif = (box1.getX() - box2.getX()) * (box1.getX() - box2.getX());
		float yDif = (box1.getY() - box2.getY()) * (box1.getY() - box2.getY());
		float radDistance = (this.radius + box2Radius) * (this.radius + box2Radius);
		

//		float radDif = (this.radius * this.radius + pRad * pRad) * (this.radius * this.radius + pRad * pRad);
		
		//System.out.println(Math.sqrt(xDif) + " + " + Math.sqrt(yDif) + " < " + (this.radius + pc.getRadius()) + "   " + Math.sqrt(xDif + yDif) + " radius = " + this.radius);
		if(xDif + yDif <= radDistance){
			Vector2D minTransDist = new Vector2D(box1.getX()-box2.getX(), box1.getY()-box2.getY());
			float distance = minTransDist.getMag();
			minTransDist.multiplyScalar(((this.radius+box2Radius)-distance)/distance);
			//minTransDist.multiplyScalar(.5f);
			box1.setX(box1.getX()-minTransDist.getX());
			box1.setY(box1.getY()-minTransDist.getY());
			System.out.println(box1.getX() + " " + box1.getY() + " " + minTransDist.getX() + " " + minTransDist.getY());
			//box2.setX(box2.getX()+minTransDist.getX());
			//box2.setY(box2.getY()+minTransDist.getY());
			xDif = box2.getX()-box1.getX();
			yDif = box2.getY()-box1.getY();
			float angle1 = (float)Math.atan2(yDif,xDif);
			//System.out.println(Math.toDegrees(angle1));
			return true;
		}
		return false;
	}
	
	public float getSizeX(){
		return this.size.getX();
	}
	
	public float getSizeY(){
		return this.size.getY();
	}
	
	public float getRadius(){
		return this.radius;
	}
	
	public float getY(){
		return this.position.getY();
	}
	
	public void setY(float y){
		this.position.setY(y);
	}
	
	public float getX() {
		return this.position.getX();
	}

	public void setX(float x){
		this.position.setX(x);
	}
	
	public Vector2D getVector(){
		return this.position;
	}
	
	public float getAccelX() {
		return accelX;
	}

	public void setAccelX(float accelX) {
		this.accelX = accelX;
	}

	public float getAccelY() {
		return accelY;
	}

	public void setAccelY(float accelY) {
		this.accelY = accelY;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

}
