import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Launcher extends Canvas implements Runnable{

	private Game game;
	private JFrame jframe;
	
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
		
		
		Entity player2 = new Entity();
		player2.addComponent(new RenderableComponent("/Images/ok_16x16.gif"));
		game.getGraphicsSystem().addRenderable(player2);
		game.addEntities(player2);
		game.getPhysicsSystem().addToPhysics(player2);
		player2.addComponent(new Player2InputComponent(player2,k,null));
		
	}
	
	private void setGame(Game game) {
		this.game = game;
	}

	@Override
	public void run() {
		game.setRunning(true);
		while(game.getRunning()){
			game.update();
			game.render();
		}
	}

		
}
