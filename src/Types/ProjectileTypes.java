package Types;

import Components.ProjectileInfoComponent;
import Math.Shape;
import Math.Vector2D;

public class ProjectileTypes {
	//                                                 new ProjectileInfoComponent(border, target, speed, accuracy, range, AoE, AoEDamage, slow, DoT flytime, damage, image);
	
	public static ProjectileInfoComponent Cannonball = new ProjectileInfoComponent(new Shape(10), new Vector2D(0,0), 1f, 0f, 1f, 75f, .75f, 0, 0, 0, 100,  "/Images/ok_16x16_2.gif");


}
