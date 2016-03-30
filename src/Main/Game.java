package Main;

import java.awt.Canvas;
import java.util.ArrayList;

import Components.RenderableComponent;
import Math.Path;
import Math.Vector2D;
import Systems.GraphicsSystem;
import Systems.LevelLoader;
import Systems.MobSystem;
import Systems.PhysicsSystem;
import Systems.ProjectileSystem;
import Systems.TowerHandlingSystem;
import Systems.UISystem;

public class Game {
	private GraphicsSystem graphicsSystem;
	private KeyInput keyInput;
	private MouseInput mouseInput;
	private PhysicsSystem physicsSystem;
	private UISystem uiSystem;
	private TowerHandlingSystem towerHandlingSystem;
	private LevelLoader ll;
	
	

	private MobSystem mobSystem;
	private ProjectileSystem projSystem;
	private ArrayList<Entity> entities;
	private boolean running;
	private boolean stopped;
	private int gold;
	private int heart;
	private boolean hardPause;
	private boolean softPause;
	private Level level;

	public Game(Canvas canvas) {
		this.stopped = false;
		this.graphicsSystem = new GraphicsSystem(canvas, this);
		this.keyInput = new KeyInput();
		this.mouseInput = new MouseInput();
		this.physicsSystem = new PhysicsSystem();
		this.setTowerHandlingSystem(new TowerHandlingSystem(this));
		this.entities = new ArrayList<Entity>();
		this.uiSystem = new UISystem(mouseInput, keyInput, this);
		this.mobSystem = new MobSystem(this);
		this.projSystem = new ProjectileSystem(this);
		this.level = new Level(this, new Path(new Vector2D(69, 106),new Vector2D(51, 219),new Vector2D(61, 338),new Vector2D(195, 529),new Vector2D(332, 340),new Vector2D(347, 116) ,new Vector2D(417, 12),new Vector2D(514, 27),new Vector2D(531, 120),new Vector2D(540, 232),new Vector2D(452, 314),new Vector2D(413, 546),new Vector2D(574, 559),new Vector2D(674, 125),new Vector2D(706, 211),new Vector2D(701, 321)));
		this.ll = new LevelLoader(this);
		this.gold = 300;
		this.heart = 100;
		this.softPause = false;
		this.hardPause = false;
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
	
	public String getGamePath(){
		return ll.getDirectory();
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
		this.uiSystem.update();
		if(!softPause){
			this.physicsSystem.update();
			this.mobSystem.update();
			this.projSystem.update();
			this.towerHandlingSystem.updateTowers();
		}
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

	public TowerHandlingSystem getTowerHandlingSystem() {
		return towerHandlingSystem;
	}

	public void setTowerHandlingSystem(TowerHandlingSystem towerHandlingSystem) {
		this.towerHandlingSystem = towerHandlingSystem;
	}

	public int getGold() {
		return this.gold;
	}
	
	public void addGold(int gold){
		this.gold += gold;
	}

	public boolean getSoftPause(){
		return this.softPause;
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
	
	public ProjectileSystem getProjectileSystem() {
		return projSystem;
	}

	public void setProjectileSystem(ProjectileSystem projectileSystem) {
		this.projSystem = projectileSystem;
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

	public void setHardPause(boolean hardPause){
		this.hardPause = hardPause;
	}
	
	public void setSoftPause(boolean softPause){
		this.softPause = softPause;
	}
	
	public boolean getHardPause(){
		return this.hardPause;
	}
	
	public boolean getStopped(){
		return this.stopped;
	}
	
	public void setStopped(boolean stopped){
		this.stopped = stopped;
	}
	
	public Level getCurLevel(){
		return level;
	}
	
	public void loadLevel(int index){
		this.level = ll.loadLevel(index);
		Entity background = new Entity(0,0,946,720);
		background.addComponent(new RenderableComponent(this.level.getBackgroundPath(),946,720, false));
		background.positionComponent.setCollide(false);
		this.getGraphicsSystem().addBackground(background);
		this.addEntities(background);
		this.getMobSystem().currentLevel = this.level;
		this.getProjectileSystem().currentLevel = this.level;
	}
}
