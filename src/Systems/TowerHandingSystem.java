package Systems;

import java.util.ArrayList;

import Components.RenderableComponent;
import Components.TowerInfoComponent;
import Main.ComponentName;
import Main.Entity;

public class TowerHandingSystem {
	
	private ArrayList<Entity> towers;
	
	public TowerHandingSystem(){
		towers = new ArrayList<Entity>();
	}
	
	public void addTower(Entity tower){
		if(tower.getComponent(ComponentName.TowerInfoComponent).name.equals(ComponentName.TowerInfoComponent)){
			this.towers.add(tower);
		}
	}
	
	public void updateTowers(){
		for(Entity tower : towers){
			TowerInfoComponent ti = (TowerInfoComponent)tower.getComponent(ComponentName.TowerInfoComponent);
			
		}
	}
	
	public Entity buildTower(TowerInfoComponent tic){
		Entity tower = new Entity(tic.xPos, tic.yPos, tic.xSize, tic.ySize);
		tower.addComponent(new RenderableComponent(tic.imageLoc));
		return tower;
	}
	
}
