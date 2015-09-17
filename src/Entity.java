import java.util.ArrayList;

public class Entity {

	ArrayList<BaseComponent> components; 
	PositionComponent positionComponent;
	
	public Entity(){
		this.components = new ArrayList<BaseComponent>();
		this.positionComponent = new PositionComponent();
	}
	
	public void addComponent(BaseComponent component){
		this.components.add(component);
	}
	
	public BaseComponent getComponent(String componentName) {
		return components.get(getComponentID(componentName));
	}
	
	public BaseComponent getComponent(int id){
		return components.get(id);
	}
	
	public int getComponentID(String componentName){
		int index = 0;
		for(BaseComponent c : components){
			if(c.getName().equalsIgnoreCase(componentName)) return index;
			index++;
		}
		return -1;
	}
	
}
