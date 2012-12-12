package gabewest.project2;

public class BB {
	public double x0, y0;
	public double x1, y1;
	public BBOwner owner;
	
	BB(BBOwner owner, double x0, double y0, double x1, double y1){
		this.owner = owner;
		this.x0 = x0;
		this.x1 = x1;
		this.y0 = y0;
		this.y1 = y1;
	}
	
	public boolean intersects(double xx0, double yy0, double xx1, double yy1){
		if(xx0 >= x1 || yy0 >= y0 || xx1 <= x1 || yy1 <= y1)
			return false;
		return true;
	}
	
	public boolean intersects(BB bb){
		if(bb.x0 >= x1 || bb.y0 >= y1 || bb.x1 <= x0 || bb.y1 <= y0)
			return false;
		return true;
	}
}
