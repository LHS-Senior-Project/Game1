package Main;

import java.awt.Canvas;

import javax.swing.JFrame;

import Components.RenderableComponentS;
import Components.TowerInfoComponent;
import Components.UIInfoComponent;
import Math.Path;
import Math.Vector2D;
import Types.TowerTypes;

@SuppressWarnings("serial")
public class Launcher extends Canvas implements Runnable {

	private Game game;
	private JFrame jframe;
	final long TIME_STEP = 60 / 1000;
	
	public static void main(String[] args) {
		Launcher l = new Launcher();
		l.init();
		l.run();
	}

	public Launcher() {
		jframe = new JFrame();
		jframe.add(this);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setResizable(true);
//		jframe.getContentPane().setPreferredSize(new Dimension(1280, 720));
		jframe.setSize(1296, 758);
		jframe.setVisible(true);
	}
	
	private void init(){
		this.setGame(new Game(this));
				
//		Entity test = new Entity(500,250,30);
//		test.addComponent(new RenderableComponent("/Images/ok_16x16.gif"));
//		game.getGraphicsSystem().addRenderable(test);	
//		game.addEntities(test);
//		game.getPhysicsSystem().addToPhysics(test);
		KeyInput k = new KeyInput();
		MouseInput mouseInput = new MouseInput();
		game.setKeyInput(k);
		this.addMouseListener(mouseInput);
		this.addMouseMotionListener(mouseInput);
		game.setMouseInput(mouseInput);
		this.addKeyListener(k);
		
		Path path = new Path(new Vector2D(69, 106),new Vector2D(51, 219),new Vector2D(61, 338),new Vector2D(195, 529),new Vector2D(332, 340),new Vector2D(347, 116) ,new Vector2D(417, 12),new Vector2D(514, 27),new Vector2D(531, 120),new Vector2D(540, 232),new Vector2D(452, 314),new Vector2D(413, 546),new Vector2D(574, 559),new Vector2D(674, 125),new Vector2D(706, 211),new Vector2D(701, 321));
		Level testLevel = new Level(game, path);
		game.getMobSystem().currentLevel = testLevel;
		
//		test.addComponent(new PlayerInputComponent(test,k,null));

		/*
		 * No longer need background :)
		 */
		
//		Entity background = new Entity(0,0,1024,720);
//		background.addComponent(new RenderableComponentS("/Images/newBackground.png",true));
//		background.positionComponent.setCollide(false);
//		game.getGraphicsSystem().addBackground(background);
//		game.addEntities(background);
		
		
//		Entity testMob = new Entity(50,50,16,16);
//		MobInfoComponent mic = new MobInfoComponent();
//		testMob.addComponent(new RenderableComponent("/Images/ok_16x16_2.gif"));
//		testMob.addComponent(mic);
//		game.getMobSystem().currentLevel.addMob(testMob);
//		game.addEntities(testMob);
//		game.getPhysicsSystem().addToPhysics(testMob);
//		game.getGraphicsSystem().addRenderable(testMob);
		
//		for(int i = 0; i < 30; i++){
//			game.getMobSystem().createMob(MobTypes.checkMob, 0, i*10);
//		}
		//134
		Entity UI = new Entity(1024,0,256,720);
		UI.addComponent(new RenderableComponentS("/Images/gui.png",256,720,true));
		UIInfoComponent uiInfo = new UIInfoComponent(false, null);
		uiInfo.handleClick = false;
		UI.addComponent(uiInfo);
		game.getGraphicsSystem().addRenderable(UI);
		game.addEntities(UI);
		game.getUISystem().addElement(UI);
		
		Entity UIGold = new Entity(1120, 100, 114, 48);
		UIGold.addComponent(new RenderableComponentS(true,114,48));
		UIInfoComponent goldInfo = new UIInfoComponent(false, null);
		goldInfo.setUpdate(true, UIInfoComponent.GOLD);
		UIGold.addComponent(goldInfo);
		game.getGraphicsSystem().addRenderable(UIGold);
		game.addEntities(UIGold);
		game.getUISystem().addElement(UIGold);
		
//		Entity UIPath = new Entity(91, 38, 114, 25);
//		UIPath.addComponent(new RenderableComponent(true,114,20));
//		UIInfoComponent pathInfo = new UIInfoComponent(false, null);
//		pathInfo.setUpdate(true, UIInfoComponent.PATH);
//		UIPath.addComponent(pathInfo);
//		game.getGraphicsSystem().addRenderable(UIPath);
//		game.addEntities(UIPath);
//		game.getUISystem().addElement(UIPath);
		
		Entity TowerInfoDisplay = new Entity(1041,561,226,146);
		TowerInfoDisplay.addComponent(new RenderableComponentS(true, 222, 144));
		UIInfoComponent towerInfo = new UIInfoComponent(false, null);
		towerInfo.setUpdate(true, UIInfoComponent.TOWER_DISPLAY);
		TowerInfoDisplay.addComponent(towerInfo);
		game.getGraphicsSystem().addRenderable(TowerInfoDisplay);
		game.addEntities(TowerInfoDisplay);
		game.getUiSystem().addElement(TowerInfoDisplay);
		
		Entity UIHeart = new Entity(1120,15,114,48);
		UIHeart.addComponent(new RenderableComponentS(true,114,48));
		UIInfoComponent heartInfo = new UIInfoComponent(false, null);
		heartInfo.setUpdate(true, UIInfoComponent.HEARTS);
		UIHeart.addComponent(heartInfo);
		game.getGraphicsSystem().addRenderable(UIHeart);
		game.addEntities(UIHeart);
		game.getUISystem().addElement(UIHeart);
		
//		Entity carButton = new Entity(1058, 211, 83, 83);
//		carButton.addComponent(new RenderableComponentS("/Buttons/cannon.png",83,83,true));
//		carButton.addComponent(new UIInfoComponent(true, TowerTypes.CarTower));
//		game.getGraphicsSystem().addRenderable(carButton);
//		game.addEntities(carButton);
//		game.getUISystem().addElement(carButton);
//		
//
//		Entity ssButton = new Entity(956,250,16,16);
//		ssButton.addComponent(new RenderableComponentS("/Buttons/SailingShip_Blue.png",true));
//		TowerInfoComponent ssInfo = new TowerInfoComponent("/Buttons/SailingShip_Blue.png");
//		ssInfo.towerName = "SS_DerP";
//		ssInfo.cost = 10;
//		ssInfo.towerDescription = "This is a little derpy boat";
//		ssButton.addComponent(new UIInfoComponent(true, ssInfo));
//		game.getGraphicsSystem().addRenderable(ssButton);
//		game.addEntities(ssButton);
//		game.getUISystem().addElement(ssButton);
//		
//		Entity lhButton = new Entity(956,350,16,16);
//		lhButton.addComponent(new RenderableComponentS("/Images/LightHouse.png", 16,16,true));
//		lhButton.addComponent(new UIInfoComponent(true, TowerTypes.LightHouseTower));
//		game.getGraphicsSystem().addRenderable(lhButton);
//		game.addEntities(lhButton);
//		game.getUISystem().addElement(lhButton);
//		
//		Entity RBButton = new Entity(956,430,16,16);
//		RBButton.addComponent(new RenderableComponentS("/Images/RadBoat.png", 16,16,true));
//		RBButton.addComponent(new UIInfoComponent(true, TowerTypes.RBoat));
//		game.getGraphicsSystem().addRenderable(RBButton);
//		game.addEntities(RBButton);
//		game.getUISystem().addElement(RBButton);
		
		Entity pointer = new Entity(0, 0, 100, 100);
		pointer.addComponent(new RenderableComponentS(true)); 
		pointer.positionComponent.setCollide(false);
		game.getGraphicsSystem().addRenderable(pointer);
		game.addEntities(pointer);
		
		game.getUiSystem().setPointer(pointer);
//		
//		Entity nButton = new Entity(956,400,16,16);
//		nButton.addComponent(new RenderableComponentS("/Images/SmallNessie.png", 16,16,true));
//		nButton.addComponent(new UIInfoComponent(true, TowerTypes.NessieTower));
//		game.getGraphicsSystem().addRenderable(nButton);
//		game.addEntities(nButton);
//		game.getUISystem().addElement(nButton);
		
		Entity playStopButton = new Entity(956,650,16,16);
		playStopButton.addComponent(new RenderableComponentS("/Buttons/pause.png",16,16,true));
		UIInfoComponent playStopInfo = new UIInfoComponent(false, null);
		playStopInfo.setUpdate(false, UIInfoComponent.TOGGLE_PLAY);
		playStopButton.addComponent(playStopInfo);
		game.getGraphicsSystem().addRenderable(playStopButton);
		game.addEntities(playStopButton);
		game.getUISystem().addElement(playStopButton);
		
		Entity settingsButton = new Entity(976, 10,16,16);
		settingsButton.addComponent(new RenderableComponentS("/Buttons/wheel.png",16,16,true));
		UIInfoComponent settingsButtonInfo = new UIInfoComponent(false, null);
		settingsButtonInfo.setUpdate(false, UIInfoComponent.SETTINGS);
		settingsButton.addComponent(settingsButtonInfo);
		game.getGraphicsSystem().addRenderable(settingsButton);
		game.addEntities(settingsButton);
		game.getUISystem().addElement(settingsButton);
				
		
		game.loadLevel(0);


	}

	private void setGame(Game game) {
		this.game = game;
	}

	public void run() {
		game.setRunning(true);
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double spawnDelta = 0;
		double delta = 0;
		int frames = 0;
		int updates = 0;

		while (!game.getStopped()) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			spawnDelta += (now - lastTime)/ns;
			lastTime = now;
			while (delta >= 1) {
				game.update();
				updates++;
				delta--;
			}
			while(spawnDelta >= 30){
				game.getCurLevel().spawnMobs();
				spawnDelta -= 30;
			}
			game.render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				jframe.setTitle(" | " + updates + " ups " + frames + " fps" + " width: " + jframe.getContentPane().getWidth() + " | " + jframe.getWidth() + " |  Height: " + jframe.getContentPane().getHeight() + " | " + jframe.getHeight());
				frames = 0;
				updates = 0;

			}

		}
	}

}
