package gabewest.project2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class DoorKey extends Loot{
	JLabel label;

	DoorKey(int posX, int posY, int radiusX, int radiusY, Room room) {
		super(posX, posY, radiusX, radiusY, room);
		image = new ImageIcon("images/key.png");
		pickedUp = false;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.yellow);
//		drawRect(g, pos.x, pos.y);
		image.paintIcon(null, g, (int)pos.x, (int)pos.y);
		if (room.getRoom().equals("images/level1.bmp")){
			g.setColor(Color.black);
			g.drawString("Get me!", (int)(pos.x-radius.x), (int)pos.y-8);
		}
	}
	
	public void update(){
		if (!this.pickedUp){
			
		}
			
	}

}
