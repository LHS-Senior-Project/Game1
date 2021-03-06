package Systems;

import java.util.ArrayList;

import Components.MobInfoComponent;
import Components.RenderableComponent;
import Components.TowerInfoComponent;
import Main.ComponentName;
import Main.Entity;
import Main.Game;

public class TowerHandingSystem {

	private ArrayList<Entity> towers;
	private Game game;

	public TowerHandingSystem(Game game) {
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
			if (tower.hasCompoent(ComponentName.TowerInfoComponent)) {
				TowerInfoComponent ti = (TowerInfoComponent) tower.getComponent(ComponentName.TowerInfoComponent);
				ArrayList<Entity> mobsToRemove = new ArrayList<Entity>();
				for (Entity mob : game.getMobSystem().currentLevel.mobs) {
					if (mob.positionComponent.checkCollide(ti.rangePC)) {
						if (System.currentTimeMillis() - ti.lastShot >= 1000 / ti.speed) {
							if (mob.hasCompoent(ComponentName.MobInfoComponent)) {
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
		Entity tower = new Entity(tic.xPos, tic.yPos, tic.xSize, tic.ySize);
		tower.addComponent(new RenderableComponent(tic.imageLoc, (int) tic.xSize, (int) tic.ySize,true));
		return tower;
	}
	
	public void createTower(TowerInfoComponent towerInfo, float x, float y){
		TowerInfoComponent tic = towerInfo.clone();
		Entity newTower = new Entity(x,y, (int)tic.getxSize(),(int) tic.getySize());
		newTower.addComponent(new RenderableComponent(tic.imageLoc, tic.xSize, tic.ySize,true));
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
