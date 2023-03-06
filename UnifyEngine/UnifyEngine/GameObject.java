package UnifyEngine;

import java.awt.Component;
import java.awt.Image;
import javax.swing.ImageIcon;

public class GameObject{
	public Vector2 position;
	public Image img = null;
	public ObjectType obj_type = ObjectType.none;
	
	public GameObject() {
	}
	public GameObject(int _x, int _y, ObjectType _type) {
		this.position = new Vector2(_x,_y);
		this.img = (new ImageIcon("UnifyEngine/resources/None_Tile.png")).getImage();
		this.obj_type = _type;
	}
	
	public GameObject(int _x, int _y, String _dir, ObjectType _type) {
		this.position = new Vector2(_x,_y);
		this.img = (new ImageIcon(_dir)).getImage(); 
		this.obj_type = _type;
	}
}
