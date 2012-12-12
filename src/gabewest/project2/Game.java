package gabewest.project2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class Game extends JPanel implements ActionListener{


	/**Gabriel West
	 * CS 2410 - Project 2
	 * Java Game
	 * Written and compiled using Eclipse
	 * This is the main class(please refer to the README).
	 */
	
	private static final long serialVersionUID = 1L;
	private Timer timer;
	private boolean running = true;
	private GameLoop looper;
	private int secondsSinceStart = 0;
	private Human player;
	private Keys keys = new Keys();
	public List<Room> allRooms;
	public Room currentRoom;
	public int currentRoomID = 0;
	public UI ui;
	
	public Room levelN;
	public Room levelMid;
	public Room levelS;
	public Room levelE;
	public Room levelW;

	
	public static final int WIDTH = 400;
	public static final int HEIGHT = 320;
	
	
	public Game(){
		
		
		setFocusable(true);
		requestFocus();
		
		initializeRooms();
		currentRoom = levelMid;
		
		player = new Human(keys, currentRoom, this);
		player.setPos(WIDTH/2 , HEIGHT/2);
		looper = new GameLoop();
		timer = new Timer();
		timer.schedule(looper, 0, 1000/60);
		addKeyListener(new InputHandler(keys));
		
		init();
		SoundPlayer.init();
		ui = new UI(this, player);
	}
	
	private void init() {
		
	}

	private class GameLoop extends java.util.TimerTask{

		@Override
		public void run() {
			secondsSinceStart+=1;
			
			//Do game updates
			update();
			
			
			//Render
			repaint();
			
			if(!running){
				timer.cancel();
			}
		}
		
	}
	
	public void update(){
		player.update();
		currentRoom.update();
		
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		currentRoom.render(g);
		
		player.render(g);
		
		ui.render(g);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void changeRooms(){
		int lastRoomID = currentRoomID;
		if (player.pos.x <= 0)
			currentRoomID -=1;
		if (player.pos.x >= WIDTH)
			currentRoomID +=1;
		if (player.pos.y < 0)
			currentRoomID -=2;
		if (player.pos.y > HEIGHT)
			currentRoomID +=2;
		switch (currentRoomID) {
		case -2:
			currentRoom = levelN;
			player.setRoom(levelN);
			player.setPos(WIDTH/2, HEIGHT - 40);
			break;
		case -1:
			currentRoom = levelW;
			player.setRoom(levelW);
			player.setPos(WIDTH-20, HEIGHT/2);
			break;
		case 0:
			currentRoom = levelMid;
			player.setRoom(levelMid);
			switch (lastRoomID) {
			case -2:
				player.setPos(WIDTH/2, 20);
				break;
			case -1:
				player.setPos(20, HEIGHT/2);
				break;
			case 2:
				player.setPos(WIDTH/2, HEIGHT - 20);
				break;
			case 1:
				player.setPos(WIDTH - 20, HEIGHT/2);
				break;
			default:
				break;
			}
			break;
		case 1:
			currentRoom = levelE;
			player.setRoom(levelE);
			player.setPos(20, HEIGHT/2);
			break;
		case 2:
			currentRoom = levelS;
			player.setRoom(levelS);
			player.setPos(WIDTH/2, 20);
			break;
		default:
			break;
		}
	}
	
	public void initializeRooms(){
		try {
			levelMid = Room.fromFile("images/level1.bmp", this);
			levelN = Room.fromFile("images/levelN.bmp", this);
			levelS = Room.fromFile("images/levelS.bmp", this);
			levelE = Room.fromFile("images/levelE.bmp", this);
			levelW = Room.fromFile("images/levelW.bmp", this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void winGame() {
		double score = 500000 - (secondsSinceStart * 60) - (player.numDeaths * 100);
		JOptionPane.showMessageDialog(null, "YOU WIN!!!!" + "\n Your score: " + score);
		System.exit(0);
	}

}
