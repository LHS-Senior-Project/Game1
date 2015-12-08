package Main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import Components.MobInfoComponent;
import Components.Player2InputComponent;
import Components.PlayerInputComponent;
import Components.RenderableComponent;
import Components.TowerInfoComponent;
import Components.UIInfoComponent;
import Math.Path;
import Math.Vector2D;

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
		jframe.pack();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setVisible(true);
		jframe.setSize(new Dimension(1080, 720));
		jframe.setResizable(true);
	}

	private void init(){
		this.setGame(new Game(this));
		Entity test = new Entity(100,100,16,16);
		test.addComponent(new RenderableComponent("/Images/ok_16x16.gif"));
		game.getGraphicsSystem().addRenderable(test);	
		game.addEntities(test);
		game.getPhysicsSystem().addToPhysics(test);
		KeyInput k = new KeyInput();
		MouseInput mouseInput = new MouseInput();
		game.setKeyInput(k);
		this.addMouseListener(mouseInput);
		this.addMouseMotionListener(mouseInput);
		game.setMouseInput(mouseInput);
		this.addKeyListener(k);
		
		Path path = new Path(new Vector2D(0,100), new Vector2D(100,100), new Vector2D(100,600), new Vector2D(200,100), new Vector2D(300,700));
		Level testLevel = new Level(game, path);
		game.getMobSystem().currentLevel = testLevel;
		
		test.addComponent(new PlayerInputComponent(test,k,null));
		
		Entity background = new Entity(0,0,946,720);
		background.addComponent(new RenderableComponent("/Images/Background.png"));
		background.positionComponent.setCollide(false);
		game.getGraphicsSystem().addRenderable(background);
		game.addEntities(background);
		
		
		Entity testMob = new Entity(50,50,16,16);
		MobInfoComponent mic = new MobInfoComponent();
		testMob.addComponent(new RenderableComponent("/Images/ok_16x16_2.gif"));
		testMob.addComponent(mic);
		game.getMobSystem().currentLevel.addMob(testMob);
		game.addEntities(testMob);
		game.getPhysicsSystem().addToPhysics(testMob);
		game.getGraphicsSystem().addRenderable(testMob);
		
		Entity UI = new Entity(946,0,134,720);
		UI.addComponent(new RenderableComponent("/Images/UI.png"));
		UIInfoComponent uiInfo = new UIInfoComponent(false, null);
		uiInfo.handleClick = false;
		UI.addComponent(uiInfo);
		game.getGraphicsSystem().addRenderable(UI);
		game.addEntities(UI);
		game.getUISystem().addElemnt(UI);
		
		Entity UIGold = new Entity(991, 38, 114, 25);
		UIGold.addComponent(new RenderableComponent(true,114,20));
		UIInfoComponent goldInfo = new UIInfoComponent(false, null);
		goldInfo.update = true;
		UIGold.addComponent(goldInfo);
		game.getGraphicsSystem().addRenderable(UIGold);
		game.addEntities(UIGold);
		game.getUISystem().addElemnt(UIGold);
		
		Entity UIHeart = new Entity(991,8,114,25);
		UIHeart.addComponent(new RenderableComponent(true,144,20));
		UIInfoComponent heartInfo = new UIInfoComponent(false, null);
		heartInfo.update = true;
		UIHeart.addComponent(heartInfo);
		game.getGraphicsSystem().addRenderable(UIHeart);
		game.addEntities(UIHeart);
		game.getUISystem().addElemnt(UIHeart);
		
		Entity carButton = new Entity(956, 200, 60, 30);
		carButton.addComponent(new RenderableComponent("/Images/car.png"));
		TowerInfoComponent carTower = new TowerInfoComponent();
		carTower.imageLoc = "/Images/car.png";
		carTower.xSize = 60;
		carTower.ySize = 30;
		carTower.cost = 50;
		carTower.towerName = "Car Tower";
		carTower.towerDescription = "This is a car that does everything";
		carButton.addComponent(new UIInfoComponent(true, carTower));
		game.getGraphicsSystem().addRenderable(carButton);
		game.addEntities(carButton);
		game.getUISystem().addElemnt(carButton);
		

		Entity ssButton = new Entity(956,250,16,16);
		ssButton.addComponent(new RenderableComponent("/Buttons/SailingShip_Blue.png"));
		TowerInfoComponent ssInfo = new TowerInfoComponent("/Buttons/SailingShip_Blue.png");
		ssInfo.towerName = "SS_DerP";
		ssInfo.cost = 10;
		ssInfo.towerDescription = "This is a little derpy boat";
		ssButton.addComponent(new UIInfoComponent(true, ssInfo));
		game.getGraphicsSystem().addRenderable(ssButton);
		game.addEntities(ssButton);
		game.getUISystem().addElemnt(ssButton);
				
		
		Entity player2 = new Entity(200,200,16,16);
		player2.addComponent(new RenderableComponent("/Images/ok_16x16.gif"));
		game.getGraphicsSystem().addRenderable(player2);
		game.addEntities(player2);
		game.getPhysicsSystem().addToPhysics(player2);
		player2.addComponent(new Player2InputComponent(player2,k,null));
		game.getUISystem().addElemnt(player2);
		game.getUISystem().addElemnt(test);
		
//		Entity car = new Entity(100,100);
//		car.addComponent(new RenderableComponent("/Images/car.png"));
//		game.getGraphicsSystem().addRenderable(car);
//		game.addEntities(car);
		
/*		for(int i = 0; i < 1; i ++){
//			Entity car = new Entity(i,i);
//			car.addComponent(new RenderableComponent("/Images/car.png"));
//			game.getGraphicsSystem().addRenderable(car);
//			game.addEntities(car);
			Entity player2 = new Entity(i * 2,i * 2);
			player2.addComponent(new RenderableComponent("/Images/ok_16x16.gif"));
			game.getGraphicsSystem().addRenderable(player2);
			game.addEntities(player2);
			game.getPhysicsSystem().addToPhysics(player2);
			player2.addComponent(new Player2InputComponent(player2,k,null));
		}*/
	}

	private void setGame(Game game) {
		this.game = game;
	}

	// @Override
	// public void run() {
	// game.setRunning(true);
	//
	// long deltaTime = 0;
	// long timeCount = 0;
	//
	// while(game.getRunning()){
	//
	// if(timeCount > TIME_STEP){
	// game.update();
	// timeCount = 0;
	// }
	// game.render();
	// }
	// }

	public void run() {
		game.setRunning(true);
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;

		while (game.getRunning()) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				game.update();
				updates++;
				delta--;
			}
			game.render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				jframe.setTitle(" | " + updates + " ups " + frames + " fps");
				frames = 0;
				updates = 0;

			}

		}
	}

}
