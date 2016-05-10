package Main;

import java.util.ArrayList;

import Components.MobInfoComponent;
import Math.Path;
import Types.MobTypes;

public class Level {

	public Game game;
	public ArrayList<MobInfoComponent> levelMobInfo;
	public ArrayList<Integer> mobsToSpawn;
	public ArrayList<Entity> mobs;
	public ArrayList<Entity> proj;
	public Path path;
	public String name;
	public String description; 
	public String backgroundPath;
	
	public Level(Game game, Path path){
		this.game = game;
		this.levelMobInfo = new ArrayList<MobInfoComponent>();
		this.levelMobInfo.add(MobTypes.lockNessMob);
		this.mobsToSpawn = new ArrayList<Integer>();
		this.mobs = new ArrayList<Entity>();
		this.proj = new ArrayList<Entity>();
		this.path = path;
	}
	
	public void addMobInfo(MobInfoComponent mic){
		this.levelMobInfo.add(mic);
	}
	
	public ArrayList<Entity> getMobs() {
		return mobs;
	}
	
	public void addMob(Entity mob){
		this.mobs.add(mob);
	}

	public void setMobs(ArrayList<Entity> mobs) {
		this.mobs = mobs;
	}
	
	public ArrayList<Entity> getProjectiles() {
		return proj;
	}
	
	public void addProjectile(Entity newProj) {
		this.proj.add(newProj);
	}
	
	public void setProjectiles(ArrayList<Entity> proj) {
		this.proj = proj;
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public void setGame(Game game){
		this.game = game;
	}
	
	public Game getGame(){
		return this.game;
	}
	
	public void removeMob(Entity mob){
		this.mobs.remove(mob);
	}
	
	public void removeProjectile(Entity projectile){
		this.proj.remove(projectile);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBackgroundPath() {
		return backgroundPath;
	}

	public void setBackgroundPath(String backgroundPath) {
		this.backgroundPath = backgroundPath;
	}
	
	public void spawnMobs() {
		if(!this.mobsToSpawn.isEmpty() && this.levelMobInfo.size() >= this.mobsToSpawn.get(0) && this.mobsToSpawn.get(0) != -1){
			game.getMobSystem().createMob(this.levelMobInfo.get(this.mobsToSpawn.get(0)), 50, 50, false);
			this.mobsToSpawn.remove(0);
		}else{
			if(!this.mobsToSpawn.isEmpty()){
				this.mobsToSpawn.remove(0);
			}
		}
	}

	
}
