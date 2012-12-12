package gabewest.project2;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Chest extends Loot {
	public boolean open = false;
	public ImageIcon openSprite;

	Chest(int posX, int posY, int radiusX, int radiusY, Room room) {
		super(posX, posY, radiusX, radiusY, room);
		sprite = new ImageIcon("images/chestClosed.png");
		openSprite = new ImageIcon("images/chestOpen.png");
		solid = true;
		radius.x = sprite.getIconWidth()/2;
		radius.y = sprite.getIconHeight()/2;
		
	}
	
	public void render(Graphics g){
		sprite.paintIcon(null, g, (int)pos.x, (int)pos.y);
	}
	
	public void open(){
		sprite = openSprite;
	}

}
