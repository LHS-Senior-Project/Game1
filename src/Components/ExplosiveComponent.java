package Components;

import Main.BaseComponent;

public class ExplosiveComponent extends BaseComponent{
	public float radius;
	public float percentDamage;
	
	public ExplosiveComponent(){
		this.radius = 50;
		this.percentDamage = 75;
	}
	
	public float getRadius(){
		return this.radius;
	}
	public void setRadius(float radius){
		this.radius = radius;
	}
	
	public float getPercentDamage(){
		return this.percentDamage;
	}
	public void setPercentDamge(float percentDamage){
		this.percentDamage = percentDamage;
	}

}
