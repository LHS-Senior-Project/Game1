package Main;

import java.util.ArrayList;

import Math.Path;

public class Level {

	public Game game;
	public ArrayList<Entity> mobs;
	public Path path;
	
	public Level(Game game, Path path){
		this.game = game;
		this.mobs = new ArrayList<Entity>();
		this.path = path;
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
	
	
}
