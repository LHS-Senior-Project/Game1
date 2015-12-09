package Main;
import java.awt.Canvas;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import Components.Player2InputComponent;
import Components.PlayerInputComponent;
import Components.RenderableComponent;

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
		jframe.setSize(new Dimension(700,700));
	}
	
	
	private void init(){
		this.setGame(new Game(this));
//		Entity test = new Entity(391,248,150,100);
//		test.addComponent(new RenderableComponent("/Images/ok_16x16.gif"));
//		game.getGraphicsSystem().addRenderable(test);	
//		game.addEntities(test);
//		game.getPhysicsSystem().addToPhysics(test);
//		KeyInput k = new KeyInput();
//		game.setKeyInput(k);
//		this.addKeyListener(k);
//		test.addComponent(new PlayerInputComponent(test,k,null));
		
		Entity test = new Entity(500,250,30);
		test.addComponent(new RenderableComponent("/Images/ok_16x16.gif"));
		game.getGraphicsSystem().addRenderable(test);	
		game.addEntities(test);
		game.getPhysicsSystem().addToPhysics(test);
		KeyInput k = new KeyInput();
		game.setKeyInput(k);
		this.addKeyListener(k);
		test.addComponent(new PlayerInputComponent(test,k,null));
		
		Entity player2 = new Entity(100,100,new float[]{-60f,0f,60f,120f,60f,0f},new float[]{60f,0f,0f,60f,120f,120f},6);
		player2.addComponent(new RenderableComponent("/Images/ok_16x16.gif"));
		game.getGraphicsSystem().addRenderable(player2);
		game.addEntities(player2);
		game.getPhysicsSystem().addToPhysics(player2);
		player2.addComponent(new Player2InputComponent(player2,k,null));
		
		Entity car = new Entity(100,100, new float[]{-30f,30f,90f}, new float[]{30f,-60f,30f}, 3);
		car.addComponent(new RenderableComponent("/Images/car.png"));
		game.getGraphicsSystem().addRenderable(car);
		game.addEntities(car);
		game.getPhysicsSystem().addToPhysics(car);
		
		Entity car2 = new Entity(400,300, new float[]{-50f,25f,100f,50f,0f}, new float[]{50f,-75f,50f,100f,100f}, 5);
		car2.addComponent(new RenderableComponent("/Images/car.png"));
		game.getGraphicsSystem().addRenderable(car2);
		game.addEntities(car2);
		game.getPhysicsSystem().addToPhysics(car2);
		
		Entity car3 = new Entity(100, 500, 100, 100);
		car3.addComponent(new RenderableComponent("/Images/car.png"));
		game.getGraphicsSystem().addRenderable(car3);
		game.addEntities(car3);
		game.getPhysicsSystem().addToPhysics(car3);
		
		Entity car4 = new Entity(500, 300, 30);
		car4.addComponent(new RenderableComponent("/Images/car.png"));
		game.getGraphicsSystem().addRenderable(car4);
		game.addEntities(car4);
		game.getPhysicsSystem().addToPhysics(car4);
		
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
