package gabewest.project2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class UI {
	protected Game owner;
	protected Human player;
	private Font keyFont = new Font("Comic Sans", Font.BOLD, 17);
	
	UI(Game owner, Human player){
		this.owner = owner;
		this.player = player;
	}
	
	public void render(Graphics g){
		g.setColor(Color.black);
		g.drawRect(0, 0, 80, 30);
		g.drawRect(Game.WIDTH-100, 0, 95, 30);
		g.setColor(Color.white);
		g.fillRect(5, 5, 75, 25);
		g.fillRect(Game.WIDTH - 99, 5, 95, 25);
		g.setFont(keyFont);
		g.setColor(Color.black);
		g.drawString("Keys: " + player.numKeys, 5, 25);
		g.drawString("Deaths: " + player.numDeaths, Game.WIDTH - 100, 25);
	}
}
