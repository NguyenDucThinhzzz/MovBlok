package UnifyEngine;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class GameObject{
	private int ID;
	private Vector2 position;
	private BufferedImage img = null;
	
	public GameObject() {
	}
	public GameObject(int _x, int _y,int _ID){
		this.position = new Vector2(_x,_y);
		try {
			this.img = ImageIO.read(new File("UnifyEngine/resources/None_Tile.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.ID = _ID;
	}
	
	public GameObject(int _x, int _y, int _ID, String _dir){
		this.position = new Vector2(_x,_y);
		try {
			this.img = ImageIO.read(new File(_dir));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		this.ID = _ID;
	}
	
	public void draw(Graphics g, ImageObserver Osv) {
		
	}
	public void draw(Graphics g, int _x, int _y, int _width, int _height, ImageObserver Osv) {
		// TODO Auto-generated method stub
		g.drawImage(img, _x, _y, _width, _height, Osv);
	}

	public abstract void update();
	
	public int getID() {
		return ID;
	}
	public Vector2 getPos() {
		return position;
	}
	public void setPos(Vector2 _vec) {
		position = _vec;
	}
	public BufferedImage getImg() {
		return img;
	}
	public void setImg(BufferedImage _img) {
		this.img = _img;
	}
}
