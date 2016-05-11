package Systems;

import java.util.ArrayList;

import Components.MobInfoComponent;
import Components.RenderableComponentS;
import Main.ComponentName;
import Main.Entity;
import Main.Game;
import Main.Level;
import Math.Vector2D;

public class MobSystem {

	public Level currentLevel;

	public Game game;

	public MobSystem(Game game) {
		this.game = game;
	}

	public void update() {
		if (game.getRunning()) {
			ArrayList<Entity> toRemove = new ArrayList<Entity>();
			if (currentLevel.getMobs().isEmpty())
				return;
			for (Entity mob : currentLevel.getMobs()) {
				if (mob.hasComponent(ComponentName.MobInfoComponent)) {
					MobInfoComponent info = (MobInfoComponent) mob.getComponent(ComponentName.MobInfoComponent);
					float deltaX = 0;
					float deltaY = 0;
					if (currentLevel.path.hasNext(info.getLevelProgress())) {
						deltaX = currentLevel.path.getNext(info.getLevelProgress()).getX()
								- (mob.positionComponent.getX() - .5f * mob.positionComponent.getSizeX());
						deltaY = currentLevel.path.getNext(info.getLevelProgress()).getY()
								- (mob.positionComponent.getY()) - .5f * mob.positionComponent.getSizeY();
						Vector2D heading = new Vector2D(deltaX, deltaY);
						// if(info.levelProgress == 8){
						// System.out.println("heading: \n" + heading);
						// System.out.println("Next point: \n" +
						// currentLevel.path.getNext(9));
						// System.out.println("Cur Position: \n" +
						// mob.positionComponent.getX() + ", " +
						// mob.positionComponent.getY());
						// System.out.println("worked");
						// }
						heading = heading.getUnit();
						heading.multiplyScalar(info.speed);
						mob.positionComponent.setVelX(heading.getX());
						mob.positionComponent.setVelY(heading.getY());
						if (Math.abs(deltaX) <= 10 && Math.abs(deltaY) <= 10) {
							info.levelProgress++;
						}
					} else {
						game.addGold(info.goldOnKill);
						game.addHeart(-1);
						toRemove.add(mob);

					}
				}
			}
			removeMobs(toRemove);
			//System.out.println("Mob Array Size: " + toRemove.size());
		}
	}

	public void removeMobs(ArrayList<Entity> toRemove) {
		for (Entity mob : toRemove) {
			this.currentLevel.removeMob(mob);
			game.getEntities().remove(mob);
			game.getGraphicsSystem().remove(mob);
			game.getPhysicsSystem().removeFromPhysics(mob);
		}
	}

	public void removeMob(Entity toRemove) {
		this.currentLevel.removeMob(toRemove);
		game.getEntities().remove(toRemove);
		game.getGraphicsSystem().remove(toRemove);
		game.getPhysicsSystem().removeFromPhysics(toRemove);
	}
	
	/*
	 * 	Entity testMob = new Entity(50,50,16,16);
		MobInfoComponent mic = new MobInfoComponent();
		testMob.addComponent(new RenderableComponent("/Images/ok_16x16_2.gif"));
		testMob.addComponent(mic);
		game.getMobSystem().currentLevel.addMob(testMob);
		game.addEntities(testMob);
		game.getPhysicsSystem().addToPhysics(testMob);
		game.getGraphicsSystem().addRenderable(testMob);
	 */
	
	public void createMob(MobInfoComponent mob, float x, float y){
		Entity newMob = new Entity(x, y, mob.getBorder());
		MobInfoComponent mic = new MobInfoComponent(mob);
		newMob.addComponent(new RenderableComponentS(mob.imageString, mob.xSize(), mob.ySize(),true));
		newMob.addComponent(mic);
		game.getMobSystem().currentLevel.addMob(newMob);
		game.addEntities(newMob);
		game.getPhysicsSystem().addToPhysics(newMob);
		game.getGraphicsSystem().addRenderable(newMob);
		
	}


	public void createMob(MobInfoComponent mob, float x, float y, boolean local){
		Entity newMob = new Entity(x, y, mob.getBorder());
		MobInfoComponent mic = new MobInfoComponent(mob);
		newMob.addComponent(new RenderableComponentS(mob.imageString, mob.xSize(), mob.ySize(),local));
		newMob.addComponent(mic);
		game.getMobSystem().currentLevel.addMob(newMob);
		game.addEntities(newMob);
		game.getPhysicsSystem().addToPhysics(newMob);
		game.getGraphicsSystem().addRenderable(newMob);
		
	}
	
}
