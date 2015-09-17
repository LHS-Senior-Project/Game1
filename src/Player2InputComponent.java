
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
		parent.positionComponent.setAccelX(0.0f);
		parent.positionComponent.setAccelY(0.0f);
		if (k.l) parent.positionComponent.setAccelX(.0100f);
		if (k.j) parent.positionComponent.setAccelX(-.0100f);
		if (k.k) parent.positionComponent.setAccelY(.0100f);
		if (k.i) parent.positionComponent.setAccelY(-.0100f);
	}

}
