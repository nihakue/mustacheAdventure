package gabewest.project2;

public class Vector {
	
	public double x, y;
	
	Vector(){
		x = y = 0;
	}
	
	Vector(double x1, double y1){
		x = x1;
		y = y1;
	}
	
	public boolean equals(final Vector p){
		return x==p.x && y == p.y;
	}
	
	public double distanceSqrt(final Vector p){
		double distX = x - p.x;
		double distY = y - p.y;
		return distX * distX + distY * distY;
				
	}
	
	public double distance(final Vector pos){
		return Math.sqrt(distanceSqrt(pos));
	}
	
	public Vector clone(){
		return new Vector(x, y);
	}
	
	public Vector add (final Vector p){
		return new Vector(x+p.x, y+p.y);
	}
	
	public void set(double x1, double y1){
		x = x1;
		y = y1;
		validate();
	}

	private void validate() {
		if (Double.isInfinite(x) || Double.isInfinite(y) || Double.isNaN(x) || Double.isNaN(y)){
			System.out.println("Something broke: " + toString());
		}
		
	}
	

}
