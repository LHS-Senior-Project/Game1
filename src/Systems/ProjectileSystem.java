package Systems;

import java.util.ArrayList;

import Components.MobInfoComponent;
import Components.ProjectileInfoComponent;
import Components.RenderableComponent;
import Main.ComponentName;
import Main.Entity;
import Main.Game;
import Main.Level;
import Math.Vector2D;

public class ProjectileSystem {

	public Level currentLevel;

	public Game game;

	public ProjectileSystem(Game game) {
		this.game = game;
	}

	public void update() {
		if (game.getRunning()) {
			ArrayList<Entity> mobsToRemove = new ArrayList<Entity>();
			ArrayList<Entity> projToRemove = new ArrayList<Entity>();
			if (currentLevel.getProjectiles().isEmpty())
				return;
			for (Entity proj : currentLevel.getProjectiles()) {
				if(((ProjectileInfoComponent) proj.getComponent(ComponentName.ProjectileInfoComponent)).flyTime == 0){
					((ProjectileInfoComponent) proj.getComponent(ComponentName.ProjectileInfoComponent)).flyTime = System.currentTimeMillis();
				}
				if (proj.hasComponent(ComponentName.ProjectileInfoComponent)) {
					ProjectileInfoComponent info = (ProjectileInfoComponent) proj.getComponent(ComponentName.ProjectileInfoComponent);
					Vector2D heading = info.getTarget();
					heading = heading.getUnit();
					heading.multiplyScalar(info.speed);
					proj.positionComponent.setVelX(heading.getX());
					proj.positionComponent.setVelY(heading.getY());
					//System.out.println(info.flyTime + "     " + proj.positionComponent.getX() + " " + proj.positionComponent.getY());
//					if (Math.abs(deltaX) <= 10 && Math.abs(deltaY) <= 10) {
//						projToRemove.add(proj);
//					}
					System.out.println(info.getTarget());
					if ((System.currentTimeMillis() - info.flyTime)/1000>info.getRange()) {
						projToRemove.add(proj);
					}
					else{
						for (Entity mob : game.getMobSystem().currentLevel.mobs) {
							if (mob.hasComponent(ComponentName.MobInfoComponent)&&proj.positionComponent.checkCollide(mob.positionComponent)) {
								MobInfoComponent mic = (MobInfoComponent) mob
										.getComponent(ComponentName.MobInfoComponent);
								mic.damage(info.getDamage());
								projToRemove.add(proj);
								if (mic.getHealth() <= 0) {
									mobsToRemove.add(mob);
									game.addGold(mic.goldOnKill);
								}
								break;
							}
						}
					}
				}
			}
			game.getMobSystem().removeMobs(mobsToRemove);
			game.getProjectileSystem().removeProjectile(projToRemove);
		}
	}

	public void removeProjectile(ArrayList<Entity> toRemove) {
		for (Entity proj : toRemove) {
			game.getEntities().remove(proj);
			game.getGraphicsSystem().remove(proj);
			this.currentLevel.removeProjectile(proj);
		}
	}

	public void removeProjectile(Entity toRemove) {
		game.getEntities().remove(toRemove);
		game.getGraphicsSystem().remove(toRemove);
		this.currentLevel.removeProjectile(toRemove);
	}
	
	public void createProjectile(ProjectileInfoComponent projectile, float x, float y, Vector2D target){
		Entity newProj = new Entity(x, y, projectile.getBorder());
		ProjectileInfoComponent pic = new ProjectileInfoComponent(projectile, target);
		newProj.addComponent(new RenderableComponent(projectile.imageString, projectile.getSizeX(), projectile.getSizeY(),true));
		newProj.addComponent(pic);
		game.getProjectileSystem().currentLevel.addProjectile(newProj);
		game.addEntities(newProj);
		game.getPhysicsSystem().addToPhysics(newProj);
		game.getGraphicsSystem().addRenderable(newProj);
		
	}

	public void createProjectile(ProjectileInfoComponent projectile, float x, float y, boolean local){
		Entity newProj = new Entity(x, y, projectile.getBorder());
		ProjectileInfoComponent pic = new ProjectileInfoComponent(projectile);
		newProj.addComponent(new RenderableComponent(projectile.imageString, projectile.getSizeX(), projectile.getSizeY(),local));
		newProj.addComponent(pic);
		game.getProjectileSystem().currentLevel.addProjectile(newProj);
		game.addEntities(newProj);
		game.getPhysicsSystem().addToPhysics(newProj);
		game.getGraphicsSystem().addRenderable(newProj);
		
	}
	
}
