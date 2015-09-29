
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
		
		if (k.d) parent.positionComponent.setAccelX(speed);
		if (k.a) parent.positionComponent.setAccelX(-1 * speed);
		if (k.s) parent.positionComponent.setAccelY(speed);
		if (k.w) parent.positionComponent.setAccelY(-1 * speed);
		
		if((k.d || k.a) && (k.w ||k.s)){
			
			parent.positionComponent.setAccelX(parent.positionComponent.getAccelX() / 2);
			parent.positionComponent.setAccelY(parent.positionComponent.getAccelY() / 2);
			System.out.println("things");
		}
		
		if(k.space) parent.positionComponent.setAccelX(0);
		if(k.space) parent.positionComponent.setAccelY(0);
		if(k.space) parent.positionComponent.setX(10);
		if(k.space) parent.positionComponent.setY(10);
		if(k.space) parent.positionComponent.setVelX(0);
		if(k.space) parent.positionComponent.setVelY(0);
	}
}
