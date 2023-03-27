package MovBlok.Objects;

import java.awt.Image;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.ImageIcon;

import UnifyEngine.Debug;
import UnifyEngine.GameObject;
import UnifyEngine.Vector2;

public class Player extends GameObject{
	
	public Player(int _x, int _y) {
		super(_x,_y,"MovBlok/resources/player_low_res.png",0);
	}
	public Player(Vector2 _vec) {
		super(_vec.x,_vec.y,"MovBlok/resources/player_low_res.png",0);
	}

	@Override
	public void update() {
	}

}
