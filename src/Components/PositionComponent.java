package Components;
import java.util.ArrayList;

import Main.BaseComponent;
import Math.Shape;
import Math.Vector2D;

public class PositionComponent extends BaseComponent{

	public final String name = "PositionComponent";
	
	private Vector2D position;
	private Shape border;
	private float accelX;
	private float accelY;
	private float velX;
	private float velY;
	
	public PositionComponent(){
		this.position = new Vector2D(0,0);
		this.border = new Shape();
		this.setAccelX(0);
		this.setVelX(0);
		this.setAccelY(0);
		this.setVelY(0);
	}
	
	public PositionComponent(float x, float y, float[] xVert, float[] yVert, int numVertices){
		this.position = new Vector2D(x,y);
		this.border = new Shape(xVert, yVert, numVertices);
		this.setAccelX(0);
		this.setVelX(0);
		this.setAccelY(0);
		this.setVelY(0);
	}
	
	public PositionComponent(float x, float y, float sizeX, float sizeY){
		this.position = new Vector2D(x,y);
		this.border = new Shape(sizeX, sizeY);
		this.setAccelX(0);
		this.setVelX(0);
		this.setAccelY(0);
		this.setVelY(0);
	}
	
	public PositionComponent(float x, float y, float radius){
		this.position = new Vector2D(x,y);
		this.border = new Shape(x, y, radius);
		this.setAccelX(0);
		this.setVelX(0);
		this.setAccelY(0);
		this.setVelY(0);
	}
	
	public boolean checkCollide(PositionComponent pc){
		//Collision Detection
		Vector2D axis;
		Vector2D smallestAxis = new Vector2D();
		float minOverlap = Float.MAX_VALUE;
		if(pc.border.getNumVertices()==0&&this.border.getNumVertices()==0){
			axis = pc.getPosition().subtract(this.getPosition());
			float r1 = pc.border.getRadius();
			float r2 = this.border.getRadius();
			if(axis.getX()<0)axis.setX(axis.getX()*-1);
			if(axis.getY()<0)axis.setY(axis.getY()*-1);
			if((r1+r2)<axis.getMag()){
				return false;
			}
			else{
				minOverlap = (r1+r2) - axis.getMag();
				smallestAxis = axis;
			}
		}
		else{
			for (int i = 0; i < pc.border.getNumVertices(); i++) {
				axis = pc.border.getAxis(i);
				Vector2D projThis = project(axis, this);
				Vector2D projPc = project(axis, pc);
				if (projPc.getY() < projThis.getX() || projThis.getY() < projPc.getX()) {
					return false;
				}
				else {
					float overlap = calcOverlap(projThis, projPc);
					if (overlap < minOverlap) {
						minOverlap = overlap;
						smallestAxis = axis;
					}
				}
			}
		
			for (int i = 0; i < this.border.getNumVertices(); i++) {
				axis = this.border.getAxis(i);
				Vector2D projThis = project(axis, this);
				Vector2D projPc = project(axis, pc);
				if (projPc.getY() < projThis.getX() || projThis.getY() < projPc.getX()) {

					return false;
				} else {
					float overlap = calcOverlap(projThis, projPc);
					if (overlap < minOverlap) {
						minOverlap = overlap;
						smallestAxis = axis;
					}
				}
			}
			minOverlap /= smallestAxis.getMag();
		}
		
		float overlapX, overlapY;
		
		//Collision Correction
		
		smallestAxis = smallestAxis.getUnit();
		
		overlapX = minOverlap * smallestAxis.getX();
		overlapY = minOverlap * smallestAxis.getY();
		
		Vector2D overlapCorrect = new Vector2D(overlapX,overlapY);
		
		//System.out.println("overlap correct: " + overlapCorrect);
		
		
//		overlapCorrect.setX(overlapCorrect.getX()*overlapCorrect.getUnit().getX());
//		overlapCorrect.setY(overlapCorrect.getY()*overlapCorrect.getUnit().getY());
		
		//System.out.println("minOverlap: " + minOverlap + "\nAxis:\n" + smallestAxis + "\nOverlap:\n" + overlapCorrect);
		if((this.getVelX()>0.000001||this.getVelX()<-0.000001||this.getVelY()>0.000001||this.getVelY()<-0.000001)&&(pc.getVelX()<=0.000001&&pc.getVelX()>=-0.000001&&pc.getVelY()<=0.000001&&pc.getVelY()>=-0.000001)) correctCollide(this, pc, overlapCorrect);
		else if((pc.getVelX()>0.000001||pc.getVelX()<-0.000001||pc.getVelY()>0.000001||pc.getVelY()<-0.000001)&&(this.getVelX()<=0.000001&&this.getVelX()>=-0.000001&&this.getVelY()<=0.000001&&this.getVelY()>=-0.000001)) correctCollide(pc, this, overlapCorrect);
		else{
			// *need mass in order to correctly adjust 2 moving objects colliding*
		}
		
		return true;
	}
	
	private Vector2D project(Vector2D axis, PositionComponent pc){
		float min=Float.MAX_VALUE;
		float max=-Float.MAX_VALUE;
		for (int j = 0; j < pc.border.getNumVertices(); j++) {
			Vector2D verPos = new Vector2D(pc.border.getVertex(j).getX()+pc.getX(),pc.border.getVertex(j).getY()+pc.getY());
			float dp = axis.dotProduct(verPos);
			if(dp<min){
				min = dp;
			}
			else if(dp>max){
				max = dp;
			}
		}
		Vector2D projPc = new Vector2D(min,max);
		return projPc;
	}
	
	private float calcOverlap(Vector2D proj1, Vector2D proj2){
		float max1 = proj1.getY() - proj2.getX();
		if(max1<0) max1 *= -1;
		float max2 = proj2.getY() - proj1.getX();
		if(max2<0) max2 *= -1;
		if(max1<max2) return max1;
		else return max2;	
	}
	
	private void correctCollide(PositionComponent pc, PositionComponent check, Vector2D overlapCorrect){
		float dirX = (pc.border.getCentroid().getX() + pc.getX()) - (check.border.getCentroid().getX() + check.getX());
		float dirY = (pc.border.getCentroid().getY() + pc.getY()) - (check.border.getCentroid().getY() + check.getY());
		//System.out.println("direction: " + dirX + " " + overlapCorrect.getY());
		//System.out.println("overlap correct: " + overlapCorrect.getX() + " " + overlapCorrect.getY());
		if(dirX>0) pc.setX(pc.getX()+overlapCorrect.getX());
		else pc.setX(pc.getX()-overlapCorrect.getX());
		
		if(dirY>0) pc.setY(pc.getY()+overlapCorrect.getY());
		else pc.setY(pc.getY()-overlapCorrect.getY());
	}
	
	public Shape getShape(){
		return this.border;
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
	
	public Vector2D getPosition(){
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
