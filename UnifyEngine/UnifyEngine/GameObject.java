package UnifyEngine;

import java.awt.Component;
import java.awt.Image;
import javax.swing.ImageIcon;

public abstract class GameObject{
	public Vector2 position;
	public Image img = null;
	
	public GameObject() {
	}
	public GameObject(int _x, int _y) {
		this.position = new Vector2(_x,_y);
		this.img = (new ImageIcon("UnifyEngine/resources/None_Tile.png")).getImage();
	}
	
	public GameObject(int _x, int _y, String _dir) {
		this.position = new Vector2(_x,_y);
		this.img = (new ImageIcon(_dir)).getImage(); 
	}
	
	public abstract void update();
}
