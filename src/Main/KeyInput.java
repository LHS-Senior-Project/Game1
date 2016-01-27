package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

	public int a = KeyEvent.VK_A;
	public int b = KeyEvent.VK_B;
	public int c = KeyEvent.VK_C;
	public int d = KeyEvent.VK_D;
	public int e = KeyEvent.VK_E;
	public int f = KeyEvent.VK_F;
	public int g = KeyEvent.VK_G;
	public int h = KeyEvent.VK_H;
	public int i = KeyEvent.VK_I;
	public int j = KeyEvent.VK_J;
	public int k = KeyEvent.VK_K;
	public int l = KeyEvent.VK_L;
	public int m = KeyEvent.VK_M;
	public int n = KeyEvent.VK_N;
	public int o = KeyEvent.VK_O;
	public int p = KeyEvent.VK_P;
	public int q = KeyEvent.VK_Q;
	public int r = KeyEvent.VK_R;
	public int s = KeyEvent.VK_S;
	public int t = KeyEvent.VK_T;
	public int u = KeyEvent.VK_U;
	public int v = KeyEvent.VK_V;
	public int w = KeyEvent.VK_W;
	public int x = KeyEvent.VK_X;
	public int y = KeyEvent.VK_Y;
	public int z = KeyEvent.VK_Z;
	public int k1 = KeyEvent.VK_1;
	public int k2 = KeyEvent.VK_2;
	public int k3 = KeyEvent.VK_3;
	public int k4 = KeyEvent.VK_4;
	public int k5 = KeyEvent.VK_5;
	public int k6 = KeyEvent.VK_6;
	public int k7 = KeyEvent.VK_7;
	public int k8 = KeyEvent.VK_8;
	public int k9 = KeyEvent.VK_9;
	
	public int debug = 192;
	public int space = KeyEvent.VK_SPACE;
	public int esc = KeyEvent.VK_ESCAPE;

	public boolean[] keys = new boolean[KeyEvent.KEY_LAST];

	public KeyInput() {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == debug){
				keys[e.getKeyCode()] = false;
			}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public boolean isPressed(int keyCode) {
		return keys[keyCode];
	}
	
	public void handled(int keyCode){
		keys[keyCode] = false;
	}

}
