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
	
	public Entity(float x, float y, float[] xVert, float[] yVert, int numVertices){
		this.components = new ArrayList<BaseComponent>();
		this.positionComponent = new PositionComponent(x,y,xVert,yVert,numVertices);
	}
	
	public Entity(float x, float y, float sizeX, float sizeY){
		this.components = new ArrayList<BaseComponent>();
		this.positionComponent = new PositionComponent(x,y,sizeX,sizeY);
	}
	
	public Entity(float x, float y, float radius){
		this.components = new ArrayList<BaseComponent>();
		this.positionComponent = new PositionComponent(x,y,radius);
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
