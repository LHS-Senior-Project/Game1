import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Launcher extends Canvas implements Runnable{

	private Game game;
	private JFrame jframe;
	final long TIME_STEP = 60/1000;
	
	public static void main(String[] args){
		Launcher l = new Launcher();
		l.init();
		l.run();
	}
	
	public Launcher(){
		jframe = new JFrame();
		jframe.add(this);
		jframe.pack();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setVisible(true);
		jframe.setSize(new Dimension(300,300));
	}
	
	private void init(){
		this.setGame(new Game(this));
		Entity test = new Entity();
		test.addComponent(new RenderableComponent("/Images/ok_16x16.gif"));
		game.getGraphicsSystem().addRenderable(test);	
		game.addEntities(test);
		game.getPhysicsSystem().addToPhysics(test);
		KeyInput k = new KeyInput();
		game.setKeyInput(k);
		this.addKeyListener(k);
		test.addComponent(new PlayerInputComponent(test,k,null));
		
		
//		Entity player2 = new Entity();
//		player2.addComponent(new RenderableComponent("/Images/ok_16x16.gif"));
//		game.getGraphicsSystem().addRenderable(player2);
//		game.addEntities(player2);
//		game.getPhysicsSystem().addToPhysics(player2);
//		player2.addComponent(new Player2InputComponent(player2,k,null));
		
//		Entity car = new Entity(100,100);
//		car.addComponent(new RenderableComponent("/Images/car.png"));
//		game.getGraphicsSystem().addRenderable(car);
//		game.addEntities(car);
		
		for(int i = 0; i < 1; i ++){
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
		}
	}
	
	private void setGame(Game game) {
		this.game = game;
	}

//	@Override
//	public void run() {
//		game.setRunning(true);
//		
//		long deltaTime = 0;
//		long timeCount = 0;
//		
//		while(game.getRunning()){
//		
//			if(timeCount > TIME_STEP){
//				game.update();
//				timeCount = 0;
//			}
//			game.render();
//		}
//	}

	public void run() {
		game.setRunning(true);
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		
		while(game.getRunning()){
			long now = System.nanoTime();
			delta += (now-lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				game.update();
				updates++;
				delta--;
			}
			game.render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				jframe.setTitle( " | " + updates + " ups " + frames + " fps");
				frames = 0;
				updates = 0;
				
			}
			
		}
	}
	
		
}
