package gabewest.project2;

import java.awt.Color;
import java.awt.Graphics;

public class Door extends Wall{
	
	public boolean locked = true;
	public String leads;

	Door(int posX, int posY, int radiusX, int radiusY, boolean solid, Room room) {
		super(posX, posY, radiusX, radiusY, solid, room);
	}
	
	public void render(Graphics g){
		Graphics ga = g;
		ga.setColor(Color.green);
		drawRect(ga, pos.x, pos.y);
	}
	
	public void update(){
		if (locked == false) open();
	}

	private void open() {
		// TODO Auto-generated method stub
		
	}
	

}
