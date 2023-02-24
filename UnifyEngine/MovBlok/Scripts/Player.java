package MovBlok.Scripts;

import java.awt.Image;

import UnifyEngine.GameObject;
import UnifyEngine.Vector2;

public class Player extends GameObject {
	
	public Player(int _x, int _y) {
		super(_x,_y);
	}
	public Player(int _x, int _y, Image _img) {
		super(_x,_y,_img);
	}
}
