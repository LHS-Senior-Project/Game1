package Math;

public class Vector{

	float x;
	float y;
	
	public Vector(){
		
	}
	
	public Vector(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public float getMag(){
		return (float) Math.sqrt((float)(((x*x) + (y*y))));
	}
	
	public float getMagSq(){
		return ((x*x) + (y*y));
	}

	public Vector getUnit(){
		float mag = getMag();
		if(mag == 0) return new Vector(0,0);
		return new Vector(x/mag, y/mag);
	}
	
	public void add(Vector v){
		this.x += v.getX();
		this.y += v.getY();
	}
	
	public void subtract(Vector v){
		this.x -= v.getX();
		this.y -= v.getY();
	}
	
	public void multiplyScalar(float scalar){
		this.x *= scalar;
		this.y *= scalar;
	}
	
	@Override
	public String toString(){
		String toBeReturned = "Vector: " + this.hashCode() + "\n";
		toBeReturned += "X: " + x + "\n";
		toBeReturned += "Y: " + y + "\n";
		toBeReturned += "Mag: " + getMag() + "\n";
		return toBeReturned;
	}
	
	static Vector add(Vector one, Vector two){
		return new Vector(one.getX() + two.getX(), one.getY() + two.getY());
	}
	
	static Vector subtract(Vector one, Vector two){
		return new Vector(one.getX() - two.getX(), one.getY() - two.getY());
	}
	
	static Vector multiplyScalar(Vector v, float scalar){
		return new Vector(v.getX() * scalar, v.getY() * scalar);
	}
	
	static Vector getUnit(Vector v){
		float mag = v.getMag();
		if(mag == 0) return new Vector(0,0);
		return new Vector(v.getX()/mag, v.getY()/mag);
	}
	
	
	static float getAngle(Vector v, Vector c){
		return (float) Math.acos((v.getX() * c.getX() + v.getY() * c.getY())/(v.getMag() * c.getMag()));
	}
	
	public static void main(String... args){
		Vector one = new Vector(0,0);
		Vector two = new Vector(10,0);
		one=one.getUnit();
		System.out.println(one);
		one.x = one.x + 0;
		System.out.println(one);
		one.y = one.y - 1;
		System.out.println(one);
		one = one.getUnit();
		one.multiplyScalar(.0075f);
		System.out.println(one);
//		System.out.println(Vector.getAngle(one, two));
		
		
	}
	
}

