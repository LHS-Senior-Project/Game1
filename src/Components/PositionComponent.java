package Components;

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

	private boolean collide;
	
	public PositionComponent(){
		this.position = new Vector2D(0,0);
		this.border = new Shape();
		this.setAccelX(0);
		this.setVelX(0);
		this.setAccelY(0);
		this.setVelY(0);
		this.collide = true;
	}
	
	public PositionComponent(float x, float y, float[] xVert, float[] yVert, int numVertices){
		this.position = new Vector2D(x,y);
		this.border = new Shape(xVert, yVert, numVertices);
		this.setAccelX(0);
		this.setVelX(0);
		this.setAccelY(0);
		this.setVelY(0);
		this.collide = true;
	}
	
	public PositionComponent(float x, float y, float sizeX, float sizeY){
		this.position = new Vector2D(x,y);
		this.border = new Shape(sizeX, sizeY);
		this.setAccelX(0);
		this.setVelX(0);
		this.setAccelY(0);
		this.setVelY(0);
		this.collide = true;
	}
	
	public PositionComponent(float x, float y, float radius){
		this.position = new Vector2D(x,y);
		this.border = new Shape(radius);
		this.setAccelX(0);
		this.setVelX(0);
		this.setAccelY(0);
		this.setVelY(0);
		this.collide = true;
	}
	
	public PositionComponent(float x, float y, Shape border){
		this.position = new Vector2D(x,y);
		this.border = border;
		this.setAccelX(0);
		this.setVelX(0);
		this.setAccelY(0);
		this.setVelY(0);
		this.collide = true;
	}
	
	public boolean checkCollide(PositionComponent pc){
		//Collision Detection
		boolean correctCollide = false; //move into method header if/when implementing collision correction

		if(pc.collide == false) return false;
		if(this.collide == false) return false;
		
		Vector2D axis;
		Vector2D smallestAxis = new Vector2D();
		float minOverlap = Float.MAX_VALUE;
		if(pc.border.getNumVertices()==0&&this.border.getNumVertices()==0){
			axis = pc.getPosition().subtract(this.getPosition());
			float r1 = pc.border.getRadius();
			float r2 = this.border.getRadius();
			if((r1+r2)<axis.getMag()){
				return false;
			}
			else{
				minOverlap = (r1+r2) - axis.getMag();
				smallestAxis = axis;
			}
		}
		else if(this.border.getNumVertices()==0||pc.border.getNumVertices()==0){
			PositionComponent circle = this;
			PositionComponent poly = pc;
			if(pc.border.getNumVertices()==0){
				circle = pc;
				poly = this;
			}
			Vector2D minVertex = new Vector2D();
			Vector2D distance;
			float maxMag = Float.MAX_VALUE;
			for(int i = 0; i<poly.getShape().getNumVertices(); i++){
				axis = poly.border.getAxis(i);
				distance = new Vector2D(circle.getShape().getCentroid().getX()+circle.getX()-(poly.getShape().getVertex(i).getX()+poly.getX()),circle.getShape().getCentroid().getY()+circle.getY()-(poly.getShape().getVertex(i).getY()+poly.getY()));
				if(distance.getMag()<maxMag){
					maxMag = distance.getMag();
					minVertex = poly.getShape().getVertex(i);
				}
				Vector2D projPoly = project(axis, poly);
				Vector2D projCircle = project(axis, circle);
				if (projPoly.getY() < projCircle.getX() || projCircle.getY() < projPoly.getX()) {
					return false;
				} else {
					float overlap = calcOverlap(projPoly, projCircle);
					if (overlap <= minOverlap) {
						minOverlap = overlap;
						smallestAxis = axis;
					}
				}
			}
			if(pc.border.getNumVertices()==0){
				axis = new Vector2D((circle.getShape().getCentroid().getX()+circle.getX())-(minVertex.getX()+poly.getX()),(circle.getShape().getCentroid().getY()+circle.getY())-(minVertex.getY()+poly.getY()));
			}
			else{
				axis = new Vector2D((minVertex.getX()+poly.getX())-(circle.getShape().getCentroid().getX()+circle.getX()),(minVertex.getY()+poly.getY())-(circle.getShape().getCentroid().getY()+circle.getY()));
			}
			Vector2D projPoly = project(axis, poly);
			Vector2D projCircle = project(axis, circle);
			if (projPoly.getY() < projCircle.getX() || projCircle.getY() < projPoly.getX()) {
				return false;
			} else {
				float overlap = calcOverlap(projPoly, projCircle);
				if (overlap < minOverlap) {
					minOverlap = overlap;
					smallestAxis = axis;
				}
			}
			minOverlap /= smallestAxis.getMag();
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
					if (overlap <= minOverlap) {
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
		if(correctCollide){
			float overlapX, overlapY;
			smallestAxis = smallestAxis.getUnit();
			overlapX = minOverlap * smallestAxis.getX();
			overlapY = minOverlap * smallestAxis.getY();
			Vector2D overlapCorrect = new Vector2D(overlapX, overlapY);
			if ((this.getVelX() > 0.000001 || this.getVelX() < -0.000001 || this.getVelY() > 0.000001|| this.getVelY() < -0.000001) && (pc.getVelX() <= 0.000001 && pc.getVelX() >= -0.000001 && pc.getVelY() <= 0.000001 && pc.getVelY() >= -0.000001)){
				this.setX(this.getX()+overlapCorrect.getX());
				this.setY(this.getY()+overlapCorrect.getY());
				Vector2D projThis = project(smallestAxis, this);
				Vector2D projPc = project(smallestAxis, pc);
				float overlap = calcOverlap(projThis, projPc);
				if(overlap>minOverlap){
					this.setX(this.getX()-(overlapCorrect.getX()*2));
					this.setY(this.getY()-(overlapCorrect.getY()*2));
				}
			}
			else if ((pc.getVelX() > 0.000001 || pc.getVelX() < -0.000001 || pc.getVelY() > 0.000001 || pc.getVelY() < -0.000001) && (this.getVelX() <= 0.000001 && this.getVelX() >= -0.000001 && this.getVelY() <= 0.000001 && this.getVelY() >= -0.000001)){
				pc.setX(pc.getX()+overlapCorrect.getX());
				pc.setY(pc.getY()+overlapCorrect.getY());
				Vector2D projpc = project(smallestAxis, pc);
				Vector2D projPc = project(smallestAxis, pc);
				float overlap = calcOverlap(projpc, projPc);
				if(overlap>minOverlap){
					pc.setX(pc.getX()-(overlapCorrect.getX()*2));
					pc.setY(pc.getY()-(overlapCorrect.getY()*2));
				}
			}
		}
		return true;
	}
	
	private Vector2D project(Vector2D axis, PositionComponent pc){
		float min=Float.MAX_VALUE;
		float max=-Float.MAX_VALUE;
		Vector2D verPos;
		float dp;
		if(pc.border.getNumVertices()!=0){
			for (int j = 0; j < pc.border.getNumVertices(); j++) {
				verPos = new Vector2D(pc.border.getVertex(j).getX() + pc.getX(), pc.border.getVertex(j).getY() + pc.getY());
				dp = axis.dotProduct(verPos);
				if (dp < min) {
					min = dp;
				}
				if (dp > max) {
					max = dp;
				}
			}
		}
		else{
			verPos = new Vector2D(pc.border.getCentroid().getX() + pc.getX(), pc.border.getCentroid().getY() + pc.getY());
			dp = axis.dotProduct(verPos);
			min = dp - pc.border.getRadius()*axis.getMag();
			max = dp + pc.border.getRadius()*axis.getMag();
		}
		Vector2D projPc = new Vector2D(min,max);
		return projPc;
	}
	
	
	private float calcOverlap(Vector2D proj1, Vector2D proj2){
		float max1 = proj1.getY() - proj2.getX();
		float max2 = proj2.getY() - proj1.getX();
		if(max1<max2) return max1;
		else return max2;	
	}
	
	protected void correctCollide(PositionComponent pc, Vector2D overlapCorrect){
		pc.setX(pc.getX()+overlapCorrect.getX());
		pc.setY(pc.getY()+overlapCorrect.getY());
	}
	
//	public void setSizeX(float x){
//		this.size.setX(x);
//		
//	}
	
	public Shape getShape(){
		return this.border;
	}
	
	public void setBorder(Shape border){
		this.border = border;
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

	public void setCollide(boolean b) {
		this.collide = b;		
	}
	
	public float getSizeX(){
		return this.getShape().getSizeX();
	}
	
	public float getSizeY(){
		return this.getShape().getSizeY();
	}
	
	public void setSizeX(float sizeX){
		this.border = new Shape(sizeX,this.getShape().getSizeY());
	}
	public void setSizeY(float sizeY){
		this.border = new Shape(this.getShape().getSizeX(), sizeY);
	}
	
}
