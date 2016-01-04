package Math;

import java.util.ArrayList;

public class Shape  {
	
	private ArrayList<Vector2D> vertices;
	private ArrayList<Vector2D> axis;
	private Vector2D centroid;
	private float radius;
	
	public static void main(String[] args){
		
	}
	
	public Shape(){
		vertices = new ArrayList<Vector2D>();
		axis = new ArrayList<Vector2D>();
		centroid = new Vector2D();
	}
	
	public Shape(float x, float y, float radius){
		vertices = new ArrayList<Vector2D>();
		axis = new ArrayList<Vector2D>();
		this.radius = radius;
		centroid = new Vector2D(radius,radius);
	}
	
	public Shape(float sizeX, float sizeY){
		vertices = new ArrayList<Vector2D>();
		axis = new ArrayList<Vector2D>();
		centroid = new Vector2D();
		vertices.add(new Vector2D(0,0));
		vertices.add(new Vector2D(sizeX,0));
		vertices.add(new Vector2D(sizeX,sizeY));
		vertices.add(new Vector2D(0,sizeY));
		
		for(int i=0;i<4;i++){
			axis.add(calcNormal(i, 4));
		}
		centroid.setX(sizeX/2);
		centroid.setY(sizeY/2);
	}
	
	public Shape(float[] x, float[] y, int numVertices){
		vertices = new ArrayList<Vector2D>();
		axis = new ArrayList<Vector2D>();
		centroid = new Vector2D(0,0);
		
		for(int i=0;i<numVertices;i++){
			vertices.add(new Vector2D(x[i],y[i]));
		}
		
		for(int i=0;i<numVertices;i++){
			axis.add(calcNormal(i, numVertices));
			
			centroid.setX(centroid.getX()+vertices.get(i).getX());
			centroid.setY(centroid.getY()+vertices.get(i).getY());
		}
		centroid.setX(centroid.getX()/numVertices);
		centroid.setY(centroid.getY()/numVertices);
	}

	
	public Vector2D getVertex(int loc){
		return vertices.get(loc);
	}
	
	public Vector2D getAxis(int loc){
		return axis.get(loc);
	}
	
	public float getRadius(){
		return radius;
	}
	
	public Vector2D getCentroid(){
		return centroid;
	}
	
	public int getNumVertices(){
		return vertices.size();
	}
	
//	private Vector2D calcNormal(int i, int numVertices){
//		Vector2D normal = new Vector2D(this.getVertex(i).getX()-this.getVertex((i+1==numVertices ? 0 : i+1)).getX(), this.getVertex(i).getY()-this.getVertex((i+1==numVertices ? 0 : i+1)).getY());
//		float temp = normal.getX();
//		if(normal.getX()==normal.getY()&&normal.getX()<0){
//			normal.setX(normal.getX()*-1);
//		}
//		else if(normal.getX()==normal.getY()&&normal.getX()>0){
//			normal.setY(normal.getY()*-1);
//		}
//		else if(normal.getX()<normal.getY()){
//			normal.setX(normal.getY());
//			normal.setY(temp * -1);
//		}
//		else {
//			normal.setX(normal.getY() * -1);
//			normal.setY(temp);
//		}
//		return normal;
//	}
	
	private Vector2D calcNormal(int i, int numVertices){
		Vector2D normal = new Vector2D(this.getVertex(i).getX()-this.getVertex((i+1==numVertices ? 0 : i+1)).getX(), this.getVertex(i).getY()-this.getVertex((i+1==numVertices ? 0 : i+1)).getY());
		float temp = normal.getX();
		normal.setX(normal.getY() * -1);
		normal.setY(temp);
		return normal;
	}
	
	

}
