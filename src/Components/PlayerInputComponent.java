package Components;
import Main.Entity;
import Main.InputComponentBase;
import Main.KeyInput;
import Main.MouseInput;
import Math.Vector;

public class PlayerInputComponent extends InputComponentBase{

	KeyInput k;
	MouseInput m;
	Entity parent;
	
	public PlayerInputComponent(Entity parent,KeyInput k, MouseInput m){
		this.k = k;
		this.m = m;
		this.parent = parent;
		this.name = "InputCompnentBase";
	}
	
	public void updateInputs(){
		float speed = .0075f;
		parent.positionComponent.setAccelX(0.0f);
		parent.positionComponent.setAccelY(0.0f);
		
		Vector xy = new Vector(0,0);
		if (k.d) xy.setX(speed);
		if (k.a) xy.setX(-speed);
		if (k.s) xy.setY(speed);
		if (k.w) xy.setY(-speed);
		
		xy = xy.getUnit();
		xy.multiplyScalar(speed);
		parent.positionComponent.setAccelX(xy.getX());
		parent.positionComponent.setAccelY(xy.getY());
		
		if(k.space) parent.positionComponent.setAccelX(0);
		if(k.space) parent.positionComponent.setAccelY(0);
		if(k.space) parent.positionComponent.setX(10);
		if(k.space) parent.positionComponent.setY(10);
		if(k.space) parent.positionComponent.setVelX(0);
		if(k.space) parent.positionComponent.setVelY(0);
	}
}
