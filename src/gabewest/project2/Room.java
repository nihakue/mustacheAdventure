package gabewest.project2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

public class Room {
	public List<Entity> entities;
	public String name;
	protected Game game;
	
	
	Room(){
		entities = new ArrayList<Entity>();
//		for (int i = 0; i < 3; i++) {
//			entities.add(new Wall((int)(Math.random() * 199), (int)(Math.random() * 199), 10, 10, true));
//			entities.add(new Door((int)(Math.random() * 199), (int)(Math.random() * 199), 5, 25, true));
//		}
		
	}
	
	public List<Entity> getEntities(){
		return entities;
	}
	
	public void update(){
		for (Entity en : entities){
			if (en instanceof Loot && !((Loot) en).pickedUp)
				en.update();
			else if (!(en instanceof Loot))
				en.update();
		}
	}
	
	public void render(Graphics g){
		for (Entity en : entities){
			if (en instanceof Loot && !((Loot) en).pickedUp)
				en.render(g);
			else if (!(en instanceof Loot))
				en.render(g);
		}
	}
	
	public static Room fromFile(String path, Game game) throws IOException{
		File file = new File(path);
		BufferedImage bufferedImage = ImageIO.read(file);
		int w = bufferedImage.getWidth();
		int h = bufferedImage.getHeight();
		
		int[] rgbs = new int[w * h];
		Arrays.fill(rgbs, 0xffA8A800);
		
		Room room = new Room();
		room.name = path;
		room.game = game;
		
		bufferedImage.getRGB(0, 0, w, h, rgbs, 0, w);
		
		
		for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
     
                int col = rgbs[x + y * w] & 0xffffff;
                if (col == 0x000000){
                	room.addEntity(x*20, y*20, "wall", room);
                }
                else if (col == 0x1eff00){
                	room.addEntity(x*20, y*20, "door", room);
                }
                else if (col == 0xfff000){
                	room.addEntity(x*20, y*20, "key", room);
                }
                else if (col == 0xff0000){
                	room.addEntity(x*20, y*20, "fireball", room);
                }
                else if (col == 0xea00ff){
                	room.addEntity(x*20, y*20, "fireballX", room);
                }
                else if (col == 0x00f6ff){
                	room.addEntity(x*20, y*20, "chest", room);
                }
                	
            }
		}
		return room;
	}

	private void addEntity(int x, int y, String type, Room room) {
		if (type == "wall"){
			entities.add(new Wall(x, y, 10, 10, true, room));
		}
		else if (type == "door"){
			entities.add(new Door(x, y, 10, 10, false, room));
		}
		else if (type == "key"){
			entities.add(new DoorKey(x, y, 10, 10, room));
		}
		else if (type == "fireball"){
			entities.add(new Fireball(x, y, 10, 10, room, false));
		}
		else if (type == "fireballX"){
			entities.add(new Fireball(x, y, 10, 10, room, true));
		}
		else if (type == "chest"){
			entities.add(new Chest(x, y, 10, 10, room));
		}
	}

	public void removeEntity(Entity key) {
		entities.remove(key);
		
	}

	public List<DoorKey> getKeys() {
		List<DoorKey> keys = new ArrayList<DoorKey>();
		for (Entity e : entities){
			if (e instanceof DoorKey)
				keys.add((DoorKey)e);
		}
		return keys;
	}

	public List<Fireball> getHazards() {
		List<Fireball> fb = new ArrayList<Fireball>();
		for (Entity e : entities){
			if (e instanceof Fireball)
				fb.add((Fireball)e);
		}
		return fb;
	}

	public String getRoom() {
		return name;
	}
}
