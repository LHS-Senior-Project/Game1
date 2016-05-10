package Types;

import Components.ProjectileInfoComponent;
import Math.Shape;
import Math.Vector2D;

public class ProjectileTypes {
	//                                                 new ProjectileInfoComponent(border, target, speed, accuracy, range, AoE, AoEDamage, slow, DoT flytime, damage, image);
	
	public static ProjectileInfoComponent Cannonball = new ProjectileInfoComponent(new Shape(16), new Vector2D(0,0), 2f, 5f, 2f, 75f, .25f, 0, 0, 0, 100,  "/Images/car.png");
	public static ProjectileInfoComponent HeavyCannonball = new ProjectileInfoComponent(new Shape(16), new Vector2D(0,0), 1f, 0f, 1f, 125f, .75f, 0, 0, 0, 200,  "/Images/car.png");
	public static ProjectileInfoComponent PirateArcher = new ProjectileInfoComponent(new Shape(16), new Vector2D(0,0), 3f, 0f, .5f, 0, 0, 0, 0, 0, 50,  "/Images/car.png");
}
