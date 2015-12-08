package Components;

import Main.BaseComponent;
import Main.ComponentName;

public class TowerInfoComponent extends BaseComponent{

	//Tower Name
	public String towerName;
	//Tower Description
	public String towerDescription;
	//Image Location
	public String imageLoc;
	//Tower Cost
	public int cost;
		
	//Attack Range
	public float range;
	//Attack Speed
	public float speed;
	//float xPos
	public float xPos;
	//float yPos
	public float yPos;
	//size x
	public float xSize;
	//size y
	public float ySize;
	
	public TowerInfoComponent() {
		towerName = "default";
		this.cost = 10;
		towerDescription = "default";
		this.name = ComponentName.TowerInfoComponent;
		this.imageLoc = "/Images/ok_16x16.gif";
		xPos = 0;
		yPos = 0;
		xSize = 16;
		ySize = 16;
	}
	
	public TowerInfoComponent(String imageLocation) {
		towerName = "default";
		this.cost = 10;
		towerDescription = "default";
		this.name = ComponentName.TowerInfoComponent;
		this.imageLoc = imageLocation;
		xPos = 0;
		yPos = 0;
		xSize = 16;
		ySize = 16;
	}

	public int getCost() {
		return cost;
	}
	
	public void setCost(int cost){
		this.cost = cost;
	}
			
}
