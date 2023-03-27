package UnifyEngine;

import java.awt.Component;
import java.awt.Image;
import javax.swing.ImageIcon;

public abstract class GameObject{
	private int ID;
	public int GetID() {
		return ID;
	}
	public Vector2 position;
	public Image img = null;
	
	public GameObject() {
	}
	public GameObject(int _x, int _y) {
		this.position = new Vector2(_x,_y);
		this.img = (new ImageIcon("UnifyEngine/resources/None_Tile.png")).getImage();
	}
	
	public GameObject(int _x, int _y, String _dir, int _ID) {
		this.position = new Vector2(_x,_y);
		this.img = (new ImageIcon(_dir)).getImage(); 
		this.ID = _ID;
	}
	
	public abstract void update();
}
