import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener{

	public boolean w,a,s,d;
	public boolean i,j,k,l;
	
	public KeyInput(){
		w = false;
		a = false;
		s = false;
		d = false;
		i = false;
		j = false;
		k = false;
		l = false;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W) w = true;
		if(e.getKeyCode() == KeyEvent.VK_A) a = true;
		if(e.getKeyCode() == KeyEvent.VK_S) s = true;
		if(e.getKeyCode() == KeyEvent.VK_D) d = true;
		if(e.getKeyCode() == KeyEvent.VK_I) i = true;
		if(e.getKeyCode() == KeyEvent.VK_J) j = true;
		if(e.getKeyCode() == KeyEvent.VK_K) k = true;
		if(e.getKeyCode() == KeyEvent.VK_L) l = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W) w = false;
		if(e.getKeyCode() == KeyEvent.VK_A) a = false;
		if(e.getKeyCode() == KeyEvent.VK_S) s = false;
		if(e.getKeyCode() == KeyEvent.VK_D) d = false;
		if(e.getKeyCode() == KeyEvent.VK_I) i = false;
		if(e.getKeyCode() == KeyEvent.VK_J) j = false;
		if(e.getKeyCode() == KeyEvent.VK_K) k = false;
		if(e.getKeyCode() == KeyEvent.VK_L) l = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
