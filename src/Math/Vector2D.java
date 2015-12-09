package Math;

public class Vector2D{

	float x;
	float y;
	
	public Vector2D(){
		
	}
	
	public Vector2D(float x, float y){
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

	public Vector2D getUnit(){
		float mag = getMag();
		if(mag == 0) return new Vector2D(0,0);
		return new Vector2D(x/mag, y/mag);
	}
	
	public void add(Vector2D v){
		this.x += v.getX();
		this.y += v.getY();
	}
	
//	private void subtract(Vector2D v){
//		this.x -= v.getX();
//		this.y -= v.getY();
//	}
	
	public void multiplyScalar(float scalar){
		this.x *= scalar;
		this.y *= scalar;
	}
	
	public float dotProduct(Vector2D v){
		return (this.getX() * v.getX() + this.getY() * v.getY());
	}
	
	@Override
	public String toString(){
		String toBeReturned = "Vector: " + this.hashCode() + "\n";
		toBeReturned += "X: " + x + "\n";
		toBeReturned += "Y: " + y + "\n";
		toBeReturned += "Mag: " + getMag() + "\n";
		return toBeReturned;
	}
	
	static Vector2D add(Vector2D one, Vector2D two){
		return new Vector2D(one.getX() + two.getX(), one.getY() + two.getY());
	}
	
	public Vector2D subtract(Vector2D one){
		return new Vector2D(this.getX() - one.getX(), this.getY() - one.getY());
	}
	
	static Vector2D multiplyScalar(Vector2D v, float scalar){
		return new Vector2D(v.getX() * scalar, v.getY() * scalar);
	}
	
	static Vector2D getUnit(Vector2D v){
		float mag = v.getMag();
		if(mag == 0) return new Vector2D(0,0);
		return new Vector2D(v.getX()/mag, v.getY()/mag);
	}
	
	
	public float getAngle(Vector2D c){
		return (float)(180/Math.PI * Math.acos((this.getX() * c.getX() + this.getY() * c.getY())/(this.getMag() * c.getMag())));
	}
	
	public static void main(String... args){
		Vector2D one = new Vector2D(0,0);
		Vector2D two = new Vector2D(10,0);
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

