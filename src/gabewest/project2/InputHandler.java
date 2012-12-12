package gabewest.project2;

import gabewest.project2.Keys.Key;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;


public class InputHandler implements KeyListener {
	private Map<Integer, Key> map = new HashMap<Integer, Key>();

	
	public InputHandler(Keys keys){
		
		map.put(KeyEvent.VK_UP, keys.up);
		map.put(KeyEvent.VK_RIGHT, keys.right);
		map.put(KeyEvent.VK_DOWN, keys.down);
		map.put(KeyEvent.VK_LEFT, keys.left);
		map.put(KeyEvent.VK_SPACE, keys.use);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		toggle(e, true);
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		toggle(e, false);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void toggle(KeyEvent e, boolean state) {
		Key key = map.get(e.getKeyCode());
		if( key != null){
			key.nextState = state;
		}
	}

}
