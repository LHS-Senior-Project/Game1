package Math;

import java.util.ArrayList;

public class Path {

	public ArrayList<Vector2D> pathCords;
	
	public Path(Vector2D ... points){
		this.pathCords = new ArrayList<Vector2D>();
		for(Vector2D point : points){
			pathCords.add(point);
		}
	}
	
	public boolean hasNext(int position){
		return (position + 1 <= pathCords.size());
	}
	
	public Vector2D getNext(int position){
		return pathCords.get(position);		
	}
}
