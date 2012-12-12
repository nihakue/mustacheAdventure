package gabewest.project2;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public GameFrame(){
		add(new Game(), BorderLayout.CENTER);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(Game.WIDTH, Game.HEIGHT);
		setLocationRelativeTo(null);
		setTitle("Mustachems Adventure In Space");
		
		setResizable(false);
		setVisible(true);
		setFocusable(false);
	}
	
	public static void main(String[] args){
		GameFrame game = new GameFrame();
	}
}
