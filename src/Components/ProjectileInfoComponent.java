package Components;

import Main.BaseComponent;
import Main.ComponentName;
import Math.Shape;
import Math.Vector2D;

public class ProjectileInfoComponent extends BaseComponent{
	
	public Shape border;
	public Vector2D target;
	public float speed;
	public float accuracy;
	public float range;
	public float AoE;
	public float AoEDamage;
	public float slow;
	public int DoT;
	public long flyTime;
	public int damage;
	public String imageString;
	
	public ProjectileInfoComponent(){
		this.border = new Shape();
		this.target = new Vector2D();
		this.speed = 0;
		this.accuracy = 0;
		this.range = 0;
		this.AoE = 0;
		this.AoEDamage = 0;
		this.slow = 0;
		this.DoT = 0;
		this.flyTime = 0;
		this.damage = 0;
		this.imageString = "/Images/ok_16x16.gif";
	}
	
	public ProjectileInfoComponent(Shape border, Vector2D target, float speed, float accuracy, float range, float AoE, float AoEDamage, float slow, int DoT, long flyTime, int damage, String image){
		this.border = border;
		this.target = target;
		this.speed = speed;
		this.accuracy = accuracy;
		this.range = range;
		this.AoE = AoE;
		this.AoEDamage = AoEDamage;
		this.slow = slow;
		this.DoT = DoT;
		this.flyTime = flyTime;
		this.damage = damage;
		this.imageString = image;
	}
	
	public ProjectileInfoComponent(ProjectileInfoComponent proj){
		this.name = ComponentName.ProjectileInfoComponent;
		this.border = proj.getBorder();
		this.target = proj.getTarget();
		this.speed = proj.speed;
		this.accuracy = proj.accuracy;
		this.range = proj.range;
		this.AoE = proj.AoE;
		this.AoEDamage = proj.AoEDamage;
		this.slow = proj.slow;
		this.DoT = proj.DoT;
		this.flyTime = proj.flyTime;
		this.damage = proj.damage;
		this.imageString = proj.imageString;
	}
	
	public ProjectileInfoComponent(ProjectileInfoComponent proj, Vector2D target){
		this.name = ComponentName.ProjectileInfoComponent;
		this.border = proj.getBorder();
		this.target = target;
		this.speed = proj.speed;
		this.accuracy = proj.accuracy;
		this.range = proj.range;
		this.AoE = proj.AoE;
		this.AoEDamage = proj.AoEDamage;
		this.slow = proj.slow;
		this.DoT = proj.DoT;
		this.flyTime = proj.flyTime;
		this.damage = proj.damage;
		this.imageString = proj.imageString;
	}
	
	public Shape getBorder(){
		return this.border;
	}
	
	public int getSizeX() {
		return (int) this.border.getSizeX();
	}
	
	public int getSizeY() {
		return (int) this.border.getSizeY();
	}
	
	public Vector2D getTarget(){
		return this.target;
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
	
	public float getAoE(){
		return this.AoE;
	}
	
	public void setAoE(int AoE){
		this.AoE = AoE;
	}
	
	public float getAoEDamage(){
		return this.AoEDamage;
	}
	
	public void setAoEDamage(int AoEDamage){
		this.AoEDamage = AoEDamage;
	}
	
	public float getSlow(){
		return this.slow;
	}
	
	public void setSlow(int slow){
		this.slow = slow;
	}
	
	public int getDoT(){
		return this.DoT;
	}
	
	public void setDoT(int DoT){
		this.DoT = DoT;
	}
	
	public float getRange(){
		return this.range;
	}
	
	public void setRange(int range){
		this.range = range;
	}
	
	public long getFlyTime(){
		return this.flyTime;
	}
	
	public void setFlyTime(long flyTime){
		this.flyTime = flyTime;
	}
	
	public int getDamage(){
		return this.damage;
	}
	
	public void setDamage(int damage){
		this.damage = damage;
	}
	
	public String getImage(){
		return this.imageString;
	}
	
}
