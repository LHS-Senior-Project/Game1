package Main;

import java.awt.Canvas;
import java.util.ArrayList;

import Systems.GraphicsSystem;
import Systems.MobSystem;
import Systems.PhysicsSystem;
import Systems.TowerHandingSystem;
import Systems.UISystem;

public class Game {
	private GraphicsSystem graphicsSystem;
	private KeyInput keyInput;
	private MouseInput mouseInput;
	private PhysicsSystem physicsSystem;
	private UISystem uiSystem;
	private TowerHandingSystem towerHandlingSystem;
	

	private MobSystem mobSystem;
	private ArrayList<Entity> entities;
	private boolean running;
	private int gold;
	private int heart;

	public Game(Canvas canvas) {
		this.graphicsSystem = new GraphicsSystem(canvas);
		this.keyInput = new KeyInput();
		this.mouseInput = new MouseInput();
		this.physicsSystem = new PhysicsSystem();
		this.setTowerHandlingSystem(new TowerHandingSystem());
		this.entities = new ArrayList<Entity>();
		this.uiSystem = new UISystem(mouseInput, keyInput, this);
		this.mobSystem = new MobSystem(this);
		this.gold = 300;
	}

	public GraphicsSystem getGraphicsSystem() {
		return this.graphicsSystem;
	}

	public KeyInput getKeyInput() {
		return this.keyInput;
	}

	public MouseInput getMouseInput() {
		return this.mouseInput;
	}

	public PhysicsSystem getPhysicsSystem() {
		return this.physicsSystem;
	}

	public void render() {
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

	public void addEntities(Entity... entities) {
		for (Entity e : entities) {
			this.entities.add(e);
		}
	}

	public boolean getRunning() {
		return running;
	}

	public void update() {
		this.physicsSystem.update();
		this.uiSystem.update();
		this.mobSystem.update();
	}

	public UISystem getUISystem() {
		return uiSystem;
	}

	public void setMouseInput(MouseInput mouse) {
		mouseInput = mouse;
		this.uiSystem = new UISystem(mouseInput,keyInput, this);
	}
	
	public void setKeyInput(KeyInput key){
		this.keyInput = key;
		this.uiSystem = new UISystem(mouseInput,keyInput,this);
	}

	public TowerHandingSystem getTowerHandlingSystem() {
		return towerHandlingSystem;
	}

	public void setTowerHandlingSystem(TowerHandingSystem towerHandlingSystem) {
		this.towerHandlingSystem = towerHandlingSystem;
	}

	public int getGold() {
		return this.gold;
	}
	
	public void addGold(int gold){
		this.gold += gold;
	}

	public int getHeart() {
		return heart;
	}

	public void addHeart(int heart){
		this.heart += heart;
	}
	
	public void setHeart(int heart) {
		this.heart = heart;
	}
	
	public UISystem getUiSystem() {
		return uiSystem;
	}

	public void setUiSystem(UISystem uiSystem) {
		this.uiSystem = uiSystem;
	}

	public MobSystem getMobSystem() {
		return mobSystem;
	}

	public void setMobSystem(MobSystem mobSystem) {
		this.mobSystem = mobSystem;
	}

	public void setGraphicsSystem(GraphicsSystem graphicsSystem) {
		this.graphicsSystem = graphicsSystem;
	}

	public void setPhysicsSystem(PhysicsSystem physicsSystem) {
		this.physicsSystem = physicsSystem;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

}
