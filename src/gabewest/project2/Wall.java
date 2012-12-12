package gabewest.project2;

import java.awt.Color;
import java.awt.Graphics;

public class Wall extends Entity{
	
	private final int RADIUS = 10;
	static final int SIZE = 10;
	
	Wall(int x, int y, int radiusX, int radiusY, boolean solid, Room room){
		super(x, y, radiusX, radiusY, room);
		this.solid = solid;
	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		drawRect(g, pos.x, pos.y);
		
	}
	

	@Override
	public void handleCollision(Entity entity, double xa, double ya) {
		// TODO Auto-generated method stub
		
	}

}
