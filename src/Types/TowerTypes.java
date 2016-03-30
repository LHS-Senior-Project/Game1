package Types;

import Components.TowerInfoComponent;

public class TowerTypes {

	//TowerInfoComponent(String towerName, String towerDescription,String imageLoc, int cost, float range, float speed, int damage, float xPos,float yPos, float xSize, float ySize)
	
	public static TowerInfoComponent BasicTower = new TowerInfoComponent();
	public static TowerInfoComponent CarTower = new TowerInfoComponent("Car", "This is a six-four", "/Images/car.png",50,30,10,10,0,0,60,30,ProjectileTypes.Cannonball);
	public static TowerInfoComponent LightHouseTower = new TowerInfoComponent("Light House", "Shines light on your foes.","/Images/Lighthouse.png",100,350,1,300,0,0,30,30,ProjectileTypes.Cannonball);
	public static TowerInfoComponent NessieTower = new TowerInfoComponent("Nessie", "May or may not exist\nTest of second line.","/Images/SmallNessie.png",100,350,1f,300,0,0,30,30,ProjectileTypes.Cannonball);
	public static TowerInfoComponent RBoat = new TowerInfoComponent("RadBoat", "This is a six-four", "/Images/RadBoat.png",100,300,10,10,0,0,160,160,ProjectileTypes.Cannonball);
	
}
