package gabewest.project2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.logging.Level;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Human extends Entity {
	static final int SIZE = 9;
	
	public int stepMonitor = 0;
	public Chest chest;
	public Keys keys;
	public Room room;
	public List<Entity> entList;
	private int xd, yd;
	private final int SPEED = 3;
	public int currentSpeed = SPEED;
	public int facing = 0;
	public int offsetX = 2;
	public int numKeys = 0;
	public int numDeaths = 0;
	public int distanceFromRight;
	Human(Keys keys, Room room, Game game){
		super(0, 0, SIZE, SIZE, room);
		//set the starting position
		this.keys = keys;
		setPos(Game.WIDTH/2-radius.x, Game.HEIGHT/2-radius.y + 100);
		this.room = room;
		this.game = game;
		sprite = new ImageIcon("images/human.png");
		for (Entity e : room.getEntities()){
			if (e instanceof Chest){
				chest = (Chest)e;
			}
		}
	}
	public void update(){
		move();
		if (stepMonitor >= 15){
			SoundPlayer.STEP.play();
			stepMonitor = 0;
		}
		
		if(numKeys >= 5)
			chest.open();
		BB bbShadow = getBB();
		bbShadow.x0 += xd;
		bbShadow.x1 += xd;
		bbShadow.y0 += yd;
		bbShadow.y1 += yd;
		
		if ( xd != 0 || yd != 0){
		
			if (!isBlocked(new BB(this, bbShadow.x0 , bbShadow.y0, bbShadow.x1, bbShadow.y1), room.getEntities())){
				setPos(pos.x + xd, pos.y + yd);
				stepMonitor++;
				
			}
			else if (yd != 0 && !isBlocked(new BB(this, getBB().x0 , bbShadow.y0, getBB().x1, bbShadow.y1), room.getEntities())){
				setPos(pos.x, pos.y + yd);
				stepMonitor++;
			}
			
			else if (xd != 0 && !isBlocked(new BB(this, bbShadow.x0 , getBB().y0, bbShadow.x1, getBB().y1), room.getEntities())){
				setPos(pos.x + xd, pos.y);
				stepMonitor++;
			}
			xd = 0;
			yd = 0;
		}
		for (DoorKey key : room.getKeys()){
			if (intersects(key) && !key.pickedUp){
				numKeys +=1;
				key.pickedUp = true;
				System.out.println("picked up a key!");
				SoundPlayer.KEY.play();
				if (room.getRoom() == "images/level1.bmp"){
					keys.release();
					JOptionPane.showMessageDialog(game, "You got a key! Collect all five yellow keys in all five rooms to open the treasure chest!", "Game Info", JOptionPane.INFORMATION_MESSAGE);
					
				}
			}
		}
		
		for (Fireball fb : room.getHazards()){
			if (intersects(fb)){
				SoundPlayer.OUCH.play();
				game.changeRooms();
				numDeaths++;
				
			}
		}
		
		if (outsideRoom()){
			game.changeRooms();
		}
		
	}
	
	private boolean outsideRoom() {
		if (pos.x > Game.WIDTH || pos.x < 0 || pos.y > Game.HEIGHT || pos.y < 0){
			return true;
		}
		return false;
	}
	private void move() {
		keys.tick();
		if (keys.up.isDown){
			yd -= (1 * SPEED);
		}
		if (keys.down.isDown){
			yd += (1*SPEED);		
		}
		if (keys.right.isDown){
			xd += (1*SPEED);		
		}
		if (keys.left.isDown){
			xd -= (1*SPEED);
		}
	}
	public void render(Graphics g){
		sprite.paintIcon(null, g, (int)pos.x, (int)pos.y);
	}
	
	public void setSpeed(int speed){
		this.currentSpeed = speed;
	}
	
	public boolean isBlocked(BB bb, List<Entity> entities){
		for ( Entity entity : entities){
			if (bb.intersects(entity.getBB()) && entity.solid){
				if (numKeys >= 5 && (entity instanceof Chest)){
					SoundPlayer.WIN.play();
					game.winGame();
				}
				return true;
			}
		}
		return false;
	}
	
	public void collide(Entity entity, double xa, double ya){
		if (entity instanceof Wall){
			
		}
	}
	public void setRoom(Room room) {
		this.room = room;
	}
}
