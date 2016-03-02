package Components;

import Math.Shape;
import Math.Vector2D;

public class ProjectileInfoComponent {
	
	public Shape border;
	public Vector2D location;
	public Vector2D target;
	public float speed;
	public float accuracy;
	public float damage;
	public String imageString;
	
	public ProjectileInfoComponent(){
		this.border = new Shape();
		this.location = new Vector2D();
		this.target = new Vector2D();
		this.speed = 0;
		this.accuracy = 0;
		this.damage = 0;
		this.imageString = "/Images/ok_16x16.gif";
	}
	
	public ProjectileInfoComponent(Shape border, Vector2D location, Vector2D target, float speed, float accuracy, float damage, String image){
		this.border = border;
		this.location = location;
		this.target = target;
		this.speed = speed;
		this.accuracy = accuracy;
		this.damage = damage;
		this.imageString = image;
	}
	
	public Shape getBorder(){
		return this.border;
	}
	
	public Vector2D getLocation(){
		return this.location;
	}
	
	public void setX(float x){
		this.location.setX(x);
	}
	
	public void setY(float y){
		this.location.setY(y);
	}
	
	public float getSpeed(){
		return this.speed;
	}
	
	public void setSpeed(float speed){
		this.speed = speed;
	}
	
	public float getAccuracy(){
		return this.accuracy;
	}
	
	public void setAccuracy(float acc){
		this.accuracy = acc;
	}
	
	public float getDamage(){
		return this.damage;
	}
	
	public void setDamage(float damage){
		this.damage = damage;
	}
	
	public String getImage(){
		return this.imageString;
	}
	
}
