package Types;

import Components.ProjectileInfoComponent;
import Math.Shape;
import Math.Vector2D;

public class ProjectileTypes {
	
	public static ProjectileInfoComponent Cannonball = new ProjectileInfoComponent(new Shape(10), new Vector2D(0,0), new Vector2D(0,0), .5f, .25f, 25, "/Images/ok_16x16.gif");


}
