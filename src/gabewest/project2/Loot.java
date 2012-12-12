package gabewest.project2;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Loot extends Entity {
	ImageIcon image;
	public boolean pickedUp = false;
	
	Loot(int posX, int posY, int radiusX, int radiusY, Room room) {
		super(posX, posY, radiusX, radiusY, room);
		solid = false;
	}
	
	public void render(Graphics g){
	}

}
