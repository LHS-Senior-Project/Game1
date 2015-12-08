package Main;
import java.util.ArrayList;

import Components.PositionComponent;

public class Entity {

	ArrayList<BaseComponent> components; 
	public PositionComponent positionComponent;
	
	public Entity(){
		this.components = new ArrayList<BaseComponent>();
		this.positionComponent = new PositionComponent();
	}
	
	public Entity(float x, float y, float sizeX, float sizeY){
		this.components = new ArrayList<BaseComponent>();
		this.positionComponent = new PositionComponent(x,y,sizeX,sizeY);
	}
	
	public Entity(PositionComponent positionComponent) {
		this.components = new ArrayList<BaseComponent>();
		this.positionComponent = positionComponent;
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
	
	public boolean hasCompoent(String componentName){
		if(getComponentID(componentName) == -1) return false;
		return true;
	}
}
