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
	public Path path;
	public String name;
	public String description; 
	public String backgroundPath;
	
	public Level(Game game, Path path){
		this.game = game;
		this.levelMobInfo = new ArrayList<MobInfoComponent>();
		this.mobsToSpawn = new ArrayList<Integer>();
		this.levelMobInfo.add(MobTypes.lockNessMob);
		this.mobs = new ArrayList<Entity>();
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
			game.getMobSystem().createMob(this.levelMobInfo.get(this.mobsToSpawn.get(0)), 0, 0);
			this.mobsToSpawn.remove(0);
		}else{
			if(!this.mobsToSpawn.isEmpty()){
				this.mobsToSpawn.remove(0);
			}
		}
	}
	
}
