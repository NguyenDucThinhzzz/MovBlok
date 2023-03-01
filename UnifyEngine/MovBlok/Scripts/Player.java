package MovBlok.Scripts;

import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import UnifyEngine.Debug;
import UnifyEngine.GameObject;
import UnifyEngine.Vector2;

public class Player extends GameObject {
	
	public Player(int _x, int _y) {
		super(_x,_y);
		MovBlokApp.GetWindow().addKeyListener(new PAdapter());
	}
	
	public class PAdapter extends KeyAdapter {
		
	    @Override
	    public void keyPressed(KeyEvent e) {

	        int key = e.getKeyCode();

	        if (key == KeyEvent.VK_A) {
	        	Debug.Log("L");
	        	position.x--;
	        }

	        if (key == KeyEvent.VK_D) {
	        	Debug.Log("R");
	        	position.x++;
	        }

	        if (key == KeyEvent.VK_W) {
	        	Debug.Log("U");
	        	position.y--;
	        }

	        if (key == KeyEvent.VK_S) {
	        	Debug.Log("D");
	        	position.y++;
	        }
	    }
	}
}
