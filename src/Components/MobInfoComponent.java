package Components;

import Main.BaseComponent;
import Main.ComponentName;
import Main.Level;

public class MobInfoComponent extends BaseComponent {

	public float startHealth;
	public int health;
	public float speed;
	public int goldOnKill;
	
	public int xSize;
	public int ySize;
	
	public int levelProgress;
	
	public Level level;
	
	public String imageString;
	
	public MobInfoComponent(){
		this.name = ComponentName.MobInfoComponent;
		this.health = 100;
		this.startHealth = 100;
		this.speed = .15f;
		this.goldOnKill = 5;
		this.imageString = "/Images/ok_16x16.gif";
	}
	
	public MobInfoComponent(int xSize, int ySize, float startHealth, int health, float speed, int goldOnKill, int levelProgress, String imageString) {
		super();
		this.name = ComponentName.MobInfoComponent;
		this.xSize = xSize;
		this.ySize = ySize;
		this.startHealth = startHealth;
		this.health = health;
		this.speed = speed;
		this.goldOnKill = goldOnKill;
		this.levelProgress = levelProgress;
		this.imageString = imageString;
	}
	
	public MobInfoComponent(MobInfoComponent mob) {
		this.name = ComponentName.MobInfoComponent;
		this.xSize = mob.xSize;
		this.ySize = mob.ySize;
		this.startHealth = mob.startHealth;
		this.health = mob.health;
		this.speed = mob.speed;
		this.goldOnKill = mob.goldOnKill;
		this.levelProgress = mob.levelProgress;
		this.imageString = mob.imageString;
	}

	public void setLevel(Level level){
		this.level = level;
	}
	
	public void setHealth(int health){
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public int getGoldOnKill() {
		return goldOnKill;
	}

	public void setGoldOnKill(int goldOnKill) {
		this.goldOnKill = goldOnKill;
	}

	public int getHealth() {
		return health;
	}

	public Level getLevel() {
		return level;
	}
	
	public int getLevelProgress() {
		return levelProgress;
	}

	public void setLevelProgress(int levelProgress) {
		this.levelProgress = levelProgress;
	}

	public void damage(int damage) {
		this.health -= damage;
	}

	public int xSize() {
		return xSize;
	}
	
	public int ySize() {
		return ySize;
	}
	
}
