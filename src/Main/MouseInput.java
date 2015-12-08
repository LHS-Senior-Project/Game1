package Main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Components.PositionComponent;

public class MouseInput implements MouseListener, MouseMotionListener{

	public static int LEFT_CLICK = MouseEvent.BUTTON1;
	public static int RIGHT_CLICK = MouseEvent.BUTTON3;
	
	private boolean click; 
	private boolean drag;
	private int mouseButton;
	
	private PositionComponent posComp;
	
	public MouseInput(){
		click = false;
		drag = false;
		mouseButton = MouseEvent.BUTTON1;
		posComp = new PositionComponent(0, 0, 1, 1);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		click = true;
		mouseButton = e.getButton();
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		drag = true;
		mouseButton = e.getButton();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		drag = false;
		mouseButton = e.getButton();
	}

	public int lastMouseButton(){
		return mouseButton;
	}
	
	public boolean isClick(){
		return click;
	}
	
	public boolean isDrag(){
		return drag;
	}
	
	public PositionComponent getPositionComponent(){
		return posComp;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		posComp.setX(e.getX());
		posComp.setY(e.getY());
		click = false;
	}
	
	public void clickHandled(){
		this.click = false;
	}
	
}
