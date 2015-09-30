package Components;
import Main.BaseComponent;

public class PositionComponent extends BaseComponent{

	public final String name = "PositionComponent";
	
	private float x;
	private float y;
	private float accelX;
	private float accelY;
	private float velX;
	private float velY;
	
	public PositionComponent(){
		this.x = 0;
		this.y = 0;
		this.setAccelX(0);
		this.setVelX(0);
		this.setAccelY(0);
		this.setVelY(0);
	}
	
	public float getY(){
		return y;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x){
		this.x = x;
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
