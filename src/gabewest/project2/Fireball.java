package gabewest.project2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

public class Fireball extends Entity{
	
	Random r = new Random();
	int direction;
	int speed;
	boolean movesLeftRight;

	Fireball(int posX, int posY, int radiusX, int radiusY, Room room, boolean leftRight) {
		super(posX, posY, radiusX, radiusY, room);
		solid = false;
		direction = r.nextInt(2);
		String name = room.getRoom();
		movesLeftRight = leftRight;
		sprite = new ImageIcon("images/fireball.png");
		
		if (name.equals("images/levelS.bmp")){
			speed = 2;
		}
		else{
			speed = 5;
		}
		if(name.equals("images/levelW.bmp")){
			direction = 0;
			speed = 3;
		}
		
	}
	
	public void update(){
		if (direction == 0){
			if (movesLeftRight)
				pos.x += (1*speed);
			else
				pos.y += (1*speed);
		}
		else{
			if (movesLeftRight)
				pos.x -= (1*speed);
			else
				pos.y -= (1*speed);
		}
		for (Entity e : room.getEntities()){
			if (intersects(e)){
				if (e instanceof Wall)
				if (direction == 0)
					direction = 1;
				else{
					direction = 0;
				}
			}
		}
	}
	
	public void render(Graphics g){
		Graphics ga = g;
		sprite.paintIcon(null, ga, (int)pos.x, (int)pos.y);
	}

}
