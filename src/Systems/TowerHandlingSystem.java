package Systems;

import java.util.ArrayList;

import Components.MobInfoComponent;
import Components.RenderableComponent;
import Components.TowerInfoComponent;
import Main.ComponentName;
import Main.Entity;
import Main.Game;

public class TowerHandlingSystem {

	private ArrayList<Entity> towers;
	private Game game;

	public TowerHandlingSystem(Game game) {
		towers = new ArrayList<Entity>();
		this.game = game;
	}

	public void addTower(Entity tower) {
		if (tower.getComponent(ComponentName.TowerInfoComponent).name.equals(ComponentName.TowerInfoComponent)) {
			this.towers.add(tower);
		}
	}

	public void updateTowers() {
		for (Entity tower : towers) {
			if (tower.hasComponent(ComponentName.TowerInfoComponent)) {
				TowerInfoComponent ti = (TowerInfoComponent) tower.getComponent(ComponentName.TowerInfoComponent);
				ArrayList<Entity> mobsToRemove = new ArrayList<Entity>();
				for (Entity mob : game.getMobSystem().currentLevel.mobs) {
					if (mob.positionComponent.checkCollide(ti.rangePC)) {
						if (System.currentTimeMillis() - ti.lastShot >= 1000 / ti.speed) {
							if (mob.hasComponent(ComponentName.MobInfoComponent)) {
								MobInfoComponent mic = (MobInfoComponent) mob
										.getComponent(ComponentName.MobInfoComponent);
								mic.damage(ti.getDamage());
								System.out.println(mic.health);
								ti.lastShot = System.currentTimeMillis();
								if (mic.getHealth() <= 0) {
									mobsToRemove.add(mob);
									game.addGold(mic.goldOnKill);
								}
							}
						}
					}
				}
				game.getMobSystem().removeMobs(mobsToRemove);
			}
		}
	}

	public Entity buildTower(TowerInfoComponent tic) {
		Entity tower = new Entity(tic.xPos, tic.yPos, tic.getBorder());
		tower.addComponent(new RenderableComponent(tic.imageLoc, (int) tic.getxSize(), (int) tic.getySize(),true));
		return tower;
	}
	
	public void createTower(TowerInfoComponent towerInfo, float x, float y){
		TowerInfoComponent tic = towerInfo.clone();
		Entity newTower = new Entity(x,y, tic.getBorder());
		newTower.addComponent(new RenderableComponent(tic.imageLoc, tic.getxSize(), tic.getySize(),true));
		tic.updatePos(newTower);
		tic.updateRange();
		newTower.addComponent(tic);
		this.addTower(newTower);
		game.getUISystem().addElement(newTower);
		game.getPhysicsSystem().addToPhysics(newTower);
		game.getGraphicsSystem().addRenderable(newTower);
		game.addEntities(newTower);
		game.addGold(-1 * tic.cost);
	}

}
