
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
		parent.positionComponent.setAccelX(0.0f);
		parent.positionComponent.setAccelY(0.0f);
		if(k.d) parent.positionComponent.setAccelX(.0100f);
		if(k.a) parent.positionComponent.setAccelX(-.0100f);
		if(k.s) parent.positionComponent.setAccelY(.0100f);
		if(k.w) parent.positionComponent.setAccelY(-.0100f);
	}
}
