package Main;

public class CollisionComponentBase extends BaseComponent{

	Entity parent;
		
	public CollisionComponentBase(Entity parent){
		this.name = "CollisionComponentBase";
		this.parent = parent;
	}
	
	public void updateCollisions(){
		
	}
	
}
