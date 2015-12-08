package Components;

import Main.BaseComponent;
import Main.ComponentName;
import Main.Level;

public class MobInfoComponent extends BaseComponent {

	public int health;
	public int speed;
	public int goldOnKill;
	
	public int levelProgress;
	
	public Level level;
	
	public MobInfoComponent(){
		this.name = ComponentName.MobInfoComponent;
		this.health = 100;
		this.speed = 10;
		this.goldOnKill = 5;
	}
	
	public void setLevel(Level level){
		this.level = level;
	}
	
	public void setHealth(int health){
		this.health = health;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
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
	
}
