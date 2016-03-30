package Components;

import Main.BaseComponent;
import Main.ComponentName;
import Main.Entity;
import Math.Shape;
import Types.ProjectileTypes;

public class TowerInfoComponent extends BaseComponent{

	//Tower Name
	public String towerName;
	//Tower Description
	public String towerDescription;
	//Image Location
	public String imageLoc;
	//Tower Cost
	public int cost;
		
	//Attack Range
	public float range;
	//Attack Speed (per second)
	public float speed;
	//float xPos
	public float xPos;
	//float yPos
	public float yPos;
	//Shape border
	public Shape border;
	//range Position Component
	public PositionComponent rangePC;
	//lastshot/damage
	public long lastShot;
	//damage
	public int damage;
	//projectile
	public ProjectileInfoComponent projectile;
	
	
	public TowerInfoComponent() {
		this.name = ComponentName.TowerInfoComponent;
		towerName = "default";
		this.cost = 10;
		this.range = 150;
		towerDescription = "default";
		this.imageLoc = "/Images/ok_16x16.gif";
		xPos = 0;
		yPos = 0;
		this.border = new Shape(16,16);
		this.speed = 1;
		this.damage = 50;
		rangePC = new PositionComponent(xPos, yPos, range + .5f * this.border.getSizeX(), range * .5f *this.border.getSizeY());
		this.lastShot = System.currentTimeMillis();
		this.projectile = ProjectileTypes.Cannonball;
	}
	
	public TowerInfoComponent(String towerName, String towerDescription,
			String imageLoc, int cost, float range, float speed, int damage, float xPos,
			float yPos, float xSize, float ySize, ProjectileInfoComponent projectile) {
		super();
		this.name = ComponentName.TowerInfoComponent;
		this.towerName = towerName;
		this.towerDescription = towerDescription;
		this.imageLoc = imageLoc;
		this.cost = cost;
		this.range = range;
		this.speed = speed;
		this.xPos = xPos;
		this.yPos = yPos;
		this.border = new Shape(xSize,ySize);
		this.damage = damage;
		this.projectile = projectile;
		
		rangePC = new PositionComponent(xPos, yPos, range + .5f * xSize, range * .5f *ySize);
		this.lastShot = System.currentTimeMillis();
	}



	public TowerInfoComponent(String imageLocation) {
		this.name = ComponentName.TowerInfoComponent;
		towerName = "default";
		this.cost = 10;
		towerDescription = "default";
		this.imageLoc = imageLocation;
		this.range = 150;
		xPos = 0;
		yPos = 0;
		this.border = new Shape(16,16);
		this.speed = 1;
		this.damage = 50;
		this.projectile = ProjectileTypes.Cannonball;
		rangePC = new PositionComponent(xPos, yPos, range + .5f * this.border.getSizeX(), range * .5f *this.border.getSizeY());
		this.lastShot = System.currentTimeMillis();
	}

	public String getTowerName() {
		return towerName;
	}

	public void setTowerName(String towerName) {
		this.towerName = towerName;
	}

	public String getTowerDescription() {
		return towerDescription;
	}

	public void setTowerDescription(String towerDescription) {
		this.towerDescription = towerDescription;
	}

	public String getImageLoc() {
		return imageLoc;
	}

	public void setImageLoc(String imageLoc) {
		this.imageLoc = imageLoc;
	}

	public float getRange() {
		return range;
	}

	public void setRange(float range) {
		this.range = range;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public ProjectileInfoComponent getProjectile(){
		return this.projectile;
	}

	public float getxPos() {
		return xPos;
	}

	public void setxPos(float xPos) {
		this.xPos = xPos;
	}

	public float getyPos() {
		return yPos;
	}

	public void setyPos(float yPos) {
		this.yPos = yPos;
	}

	public float getxSize() {
		return this.border.getSizeX();
	}

	public void setxSize(float xSize) {
		this.border = new Shape(xSize,this.border.getSizeY());
	}

	public float getySize() {
		return this.border.getSizeY();
	}

	public void setySize(float ySize) {
		this.border = new Shape(this.border.getSizeX(),ySize);
	}
	
	public Shape getBorder(){
		return this.border;
	}

	public PositionComponent getRangePC() {
		return rangePC;
	}

	public void setRangePC(PositionComponent rangePC) {
		this.rangePC = rangePC;
	}

	public long getLastShot() {
		return lastShot;
	}

	public void setLastShot(long lastShot) {
		this.lastShot = lastShot;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost){
		this.cost = cost;
	}
	
	public void setX(float x){
		this.xPos = x;
	}
	
	public void setY(float y){
		this.yPos = y;
	}
	
	public void updatePos(Entity e){
		this.xPos = e.positionComponent.getX();
		this.yPos = e.positionComponent.getY();
		updateRange();
	}
	
	public void updateRange(){
		rangePC = new PositionComponent(xPos - (.5f * range), yPos - (.5f * range), range + this.border.getSizeX(), range + this.border.getSizeY());
	}
	
	public TowerInfoComponent clone(){		
		return new TowerInfoComponent(this.towerName,this.towerDescription,this.imageLoc,this.cost, this.range, this.speed, this.damage, this.xPos, this.yPos, this.border.getSizeX(), this.border.getSizeY(),this.projectile); 
	}
}
