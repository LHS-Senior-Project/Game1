package Components;
import Main.BaseComponent;
import Math.Vector2D;

public class PositionComponent extends BaseComponent{

	public final String name = "PositionComponent";
	
	private Vector2D position;
	private Vector2D size;
	private float accelX;
	private float accelY;
	private float velX;
	private float velY;
	
	public PositionComponent(){
		this.position = new Vector2D(0,0);
		this.size = new Vector2D(0,0);
		this.setAccelX(0);
		this.setVelX(0);
		this.setAccelY(0);
		this.setVelY(0);
	}
	
	public PositionComponent(float x, float y, float sizeX, float sizeY){
		this.position = new Vector2D(x,y);
		this.size = new Vector2D(sizeX,sizeY);
		this.setAccelX(0);
		this.setVelX(0);
		this.setAccelY(0);
		this.setVelY(0);
	}
	
	public boolean checkCollide(PositionComponent pc){
		float lineY11, lineY12, lineY21, lineY22;
		float lineX11, lineX12, lineX21, lineX22;
		if(pc.getY()>this.getY()){
			lineY11 = this.getY();
			lineY12 = lineY11 + this.getSizeY();
			lineY21 = pc.getY();
			lineY22 = lineY21 + pc.getSizeY();
		}
		else{
			lineY11 = pc.getY();
			lineY12 = lineY11 + pc.getSizeY();
			lineY21 = this.getY();
			lineY22 = lineY21 + this.getSizeY();
		}
		if(pc.getX()>this.getX()){
			lineX11 = this.getX();
			lineX12 = lineX11 + this.getSizeX();
			lineX21 = pc.getX();
			lineX22 = lineX21 + pc.getSizeX();
		}
		else{
			lineX11 = pc.getX();
			lineX12 = lineX11 + pc.getSizeX();
			lineX21 = this.getX();
			lineX22 = lineX21 + this.getSizeX();
		}
		System.out.println("Test for x = " + this.getX() + " and y = " + this.getY());
		System.out.println("Y12>=Y21: " + lineY12 + " >= " + lineY21);
		System.out.println("Y22-Y11<=Size: " + (Math.abs(lineY22-lineY11) + " <= " + (this.getSizeY()+pc.getSizeY())));
		System.out.println("X12>=X21: " + lineX12 + " >= " + lineX21);
		System.out.println("X22-X11<=Size: " + (Math.abs(lineX22-lineX11) + " <= " + (this.getSizeX()+pc.getSizeX())));
		if((lineY12>lineY21&&Math.abs(lineY22-lineY11)<this.getSizeY()+pc.getSizeY())&&(lineX12>lineX21&&Math.abs(lineX22-lineX11)<this.getSizeX()+pc.getSizeX())){
			Vector2D penetration = new Vector2D(lineX12-lineX21,lineY12-lineY21);
//			if(penetration.getX()>penetration.getY()){
//				this.setVelY(this.getVelY() * -1);	
//				this.setAccelY(0);
//			}
//			else if(penetration.getY()>penetration.getX()){
//				this.setVelX(this.getVelX() * -1);	
//				this.setAccelX(0);
//			}
//			else{
//				this.setAccelX(this.getAccelX() * -1);
//				this.setAccelY(this.getAccelY() * -1);
//				this.setVelY(0);
//				this.setVelX(0);
//			return true;
//			}
			System.out.println("Pen: " + penetration.getX() + " " + penetration.getY());
			if(this.getAccelX()>0){
				if(this.getAccelY()==0){
					this.setVelX(0);
					this.setX(this.getX()-penetration.getX());
				}
				else if(this.getAccelY()>0){
					if(penetration.getX()>penetration.getY()){
						this.setVelY(0);
						this.setY(this.getY()-penetration.getY());
					}
					else if(penetration.getX()<penetration.getY()){
						this.setVelX(0);
						this.setX(this.getX()-penetration.getX());
					}
					else {
						this.setVelX(0);
						this.setVelY(0);
						this.setX(this.getX()-penetration.getX());
						this.setY(this.getY()-penetration.getY());
					}
				}
				else {
					if(penetration.getX()>penetration.getY()){
						this.setVelY(0);
						this.setY(this.getY()+penetration.getY());
					}
					else if(penetration.getX()<penetration.getY()){
						this.setVelX(0);
						this.setX(this.getX()-penetration.getX());
					}
					else {
						this.setVelX(0);
						this.setVelY(0);
						this.setX(this.getX()-penetration.getX());
						this.setY(this.getY()+penetration.getY());
					}
				}
			}
			else if(this.getAccelX()<0){
				if(this.getAccelY()==0){
					this.setVelX(0);
					this.setX(this.getX()+penetration.getX());
				}
				else if(this.getAccelY()>0){
					if(penetration.getX()>penetration.getY()){
						this.setVelY(0);
						this.setY(this.getY()-penetration.getY());
					}
					else if(penetration.getX()<penetration.getY()){
						this.setVelX(0);
						this.setX(this.getX()+penetration.getX());
					}
					else {
						this.setVelX(0);
						this.setVelY(0);
						this.setX(this.getX()+penetration.getX());
						this.setY(this.getY()-penetration.getY());
					}
				}
				else {
					if(penetration.getX()>penetration.getY()){
						this.setVelY(0);
						this.setY(this.getY()+penetration.getY());
					}
					else if(penetration.getX()<penetration.getY()){
						this.setVelX(0);
						this.setX(this.getX()+penetration.getX());
					}
					else {
						this.setVelX(0);
						this.setVelY(0);
						this.setX(this.getX()+penetration.getX());
						this.setY(this.getY()+penetration.getY());
					}
				}
			}
			else if(this.getAccelY()>0){
				this.setVelY(0);
				this.setY(this.getY()-penetration.getY());
			}
			else if(this.getAccelY()<0){
				this.setVelY(0);
				this.setY(this.getY()+penetration.getY());
			}
//			if(this.getAccelX()>0&&this.getAccelY()==0){
//				this.setVelX(0);
//				this.setX(this.getX()-penetration.getX()-);
//			}
//			else if(this.getAccelX()<0&&this.getAccelY()==0){
//				this.setVelX(0);
//				this.setX(this.getX()+penetration.getX()+);
//			}
//			else if(this.getAccelY()>0&&this.getAccelX()==0){
//				this.setVelY(0);
//				this.setY(this.getY()-penetration.getY()-);
//			}
//			else if(this.getAccelY()<0&&this.getAccelX()==0){
//				this.setVelY(0);
//				this.setY(this.getY()+penetration.getY()+);
//			}
//			else if(this.getAccelY()>0&&this.getAccelX()>0){
//				this.setVelX(0);
//				this.setVelY(0);
//				this.setX(this.getX()-penetration.getX());
//				this.setY(this.getY()-penetration.getY());
//			}
//			else if(this.getAccelY()<0&&this.getAccelX()>0){
//				this.setVelX(0);
//				this.setVelY(0);
//				this.setX(this.getX()+penetration.getX());
//				this.setY(this.getY()-penetration.getY());
//			}
//			else if(this.getAccelY()>0&&this.getAccelX()<0){
//				this.setVelX(0);
//				this.setVelY(0);
//				this.setX(this.getX()-penetration.getX());
//				this.setY(this.getY()+penetration.getY());
//			}
//			else if(this.getAccelY()<0&&this.getAccelX()<0){
//				this.setVelX(0);
//				this.setVelY(0);
//				this.setX(this.getX()+penetration.getX());
//				this.setY(this.getY()+penetration.getY());
//			}
//			else{
//				this.setVelX(0);
//				this.setVelY(0);
//			}
//			this.setX(this.getX()-penetration.getX());
//			this.setY(this.getY()-penetration.getY());
			return true;
		}
		return false;
	}
	
	public float getY(){
		return this.position.getY();
	}
	
	public void setY(float y){
		this.position.setY(y);
	}
	
	public float getSizeX(){
		return this.size.getX();
	}
	
	public void setSizeX(float x){
		this.size.setY(x);
	}
	
	public float getSizeY(){
		return this.size.getY();
	}
	
	public void setSizeY(float y){
		this.size.setY(y);
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
