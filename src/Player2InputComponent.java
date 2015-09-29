
public class Player2InputComponent extends InputComponentBase{

	KeyInput k;
	MouseInput m;
	Entity parent;

	public Player2InputComponent(Entity parent,KeyInput k, MouseInput m){
			this.k = k;
			this.m = m;
			this.parent = parent;
			this.name = "InputCompnentBase";
		}

	public void updateInputs() {
		float speed = .0075f;
		parent.positionComponent.setAccelX(0.0f);
		parent.positionComponent.setAccelY(0.0f);
		if (k.l) parent.positionComponent.setAccelX(speed);
		if (k.j) parent.positionComponent.setAccelX(-1 * speed);
		if (k.k) parent.positionComponent.setAccelY(speed);
		if (k.i) parent.positionComponent.setAccelY(-1 * speed);
		if(k.space) parent.positionComponent.setAccelX(0);
		if(k.space) parent.positionComponent.setAccelY(0);
		if(k.space) parent.positionComponent.setX(10);
		if(k.space) parent.positionComponent.setY(10);
		if(k.space) parent.positionComponent.setVelX(0);
		if(k.space) parent.positionComponent.setVelY(0);
		
	}

}
