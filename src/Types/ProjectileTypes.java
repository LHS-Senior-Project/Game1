package Types;

import Components.ProjectileInfoComponent;
import Math.Shape;
import Math.Vector2D;

public class ProjectileTypes {
	//new ProjectileInfoComponent(border, target, speed, accuracy, range, flytime, damage, image);
	public static ProjectileInfoComponent Cannonball = new ProjectileInfoComponent(new Shape(10), new Vector2D(0,0), 1f, 0, 1f, 0, 100, "/Images/ok_16x16.gif");


}
