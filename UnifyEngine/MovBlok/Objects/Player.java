package MovBlok.Objects;

import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import UnifyEngine.Debug;
import UnifyEngine.GameObject;
import UnifyEngine.ObjectType;
import UnifyEngine.Vector2;

public class Player extends GameObject {
	
	public Player(int _x, int _y) {
		super(_x,_y,"MovBlok/resources/player_low_res.png", ObjectType.player);
	}
}
