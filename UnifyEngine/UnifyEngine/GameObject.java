package UnifyEngine;

import java.awt.Component;
import java.awt.Image;
import javax.swing.ImageIcon;

public class GameObject{
	public Vector2 position;
	public Image img = (new ImageIcon("UnifyEngine/resources/default_err.png")).getImage();
	
	public GameObject() {
	}
	public GameObject(int _x, int _y) {
		position = new Vector2(_x,_y);
	}
	
	public GameObject(int _x, int _y, Image _img) {
		position = new Vector2(_x,_y);
		this.img = _img; 
	}
}
