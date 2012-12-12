package gabewest.project2;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public abstract class Entity implements BBOwner {
	public Vector pos = new Vector(0,0);
	public Vector radius;
	public Room room;
	protected Game game;
	public boolean solid = true;
	public final int ABOVE = 0;
	public final int BELOW = 0;
	public final int RIGHTOF = 0;
	public final int LEFTOF = 0;
	protected ImageIcon sprite;
	
	
	Entity(int posX, int posY, int radiusX, int radiusY, Room room){
		this.radius = new Vector(radiusX, radiusY);
		this.pos = new Vector(posX, posY);
		this.room = room;
	}
	
	public void setPos(double x, double y){
		pos.set(x, y);
	}
	
	public void setSize(int xr, int yr){
		radius.set(xr, yr);
	}
	
	
	public boolean intersects(double xx0, double yy0, double xx1, double yy1){
		return getBB().intersects(xx0, yy0, xx1, yy1);
	}
	public boolean intersects(Entity e){
		return getBB().intersects(e.getBB());
	}
	
	public BB getBB(){
		return new BB(this, pos.x, pos.y, pos.x + radius.x*2, pos.y + radius.y*2 );
	}
	
	public void drawBB(Graphics g){
		Graphics ga = g;
		ga.setColor(Color.red);
		ga.drawRect((int)pos.x,(int)pos.y, (int)(getBB().x1 - getBB().x0), (int)(getBB().y1 - getBB().y0));
	}
	
	public void render(Graphics g){
		
		
	}
	
	public void update(){
		
	}
	
	public void drawRect(Graphics g, double x, double y){
		g.fillRect((int)pos.x, (int)pos.y, (int)(radius.x*2), (int)(radius.y*2));
	}
	
	@Override
	public void handleCollision(Entity entity, double xa, double ya) {
		if(this.blocks(entity)){
			this.collide(entity, xa, ya);
			entity.collide(this, -xa, -ya);
		}
		
	}
	
	private boolean blocks(Entity entity) {
		return solid && entity.solid;
	}
	
	public void collide(Entity entity, double xa, double ya){
	}
}
