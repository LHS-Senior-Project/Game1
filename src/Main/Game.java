package Main;
import java.awt.Canvas;
import java.util.ArrayList;

import Systems.GraphicsSystem;
import Systems.PhysicsSystem;

public class Game {
	private GraphicsSystem graphicsSystem;
	private KeyInput keyInput;
	private MouseInput mouseInput;
	private PhysicsSystem physicsSystem;
	private ArrayList<Entity> entities;
	private boolean running;
	
	public Game(Canvas canvas){
		this.graphicsSystem = new GraphicsSystem(canvas);
		this.keyInput = new KeyInput();
		this.mouseInput = new MouseInput();
		this.physicsSystem = new PhysicsSystem();
		this.entities = new ArrayList<Entity>();
	}
	
	public GraphicsSystem getGraphicsSystem(){
		return this.graphicsSystem;
	}
	public KeyInput getKeyInput(){
		return this.keyInput;
	}
	public MouseInput getMouseInput(){
		return this.mouseInput;
	}
	public PhysicsSystem getPhysicsSystem(){
		return this.physicsSystem;
	}
	
	public void render(){
		this.graphicsSystem.render();
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public void addEntities(Entity ... entities){
		for(Entity e : entities){
			this.entities.add(e);
		}
	}
	
	public boolean getRunning(){
		return running;
	}

	public void update() {
		this.physicsSystem.update();
	}
	
	public void setKeyInput(KeyInput k){
		this.keyInput = k;
	}
	
	
}
