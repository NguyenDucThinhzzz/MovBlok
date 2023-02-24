package UnifyEngine;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import MovBlok.Scripts.MovBlokApp;

public class Grid {
	
	public GameObject[][] arr;
	public int bound_x=0,bound_y=0;
	
	public int renderDist;
	public int boxSize;
	
	public Grid(int _x,int _y) {
		this.bound_x = _x;
		this.bound_y = _y;
		arr = new GameObject[_x][_y];
		for(GameObject[] i: arr) {
			for(GameObject j: i) {
				j=null;
			}
		}
		//Aspect ratio change in the future for now it's 1280x720 (16:9)
		boxSize=80;
		renderDist = 3;
	}
	public Grid(int _x, int _y, GameObject[][] _arr) {
		this.bound_x = _x;
		this.bound_y = _y;
		arr = new GameObject[_x][_y];
		this.arr = _arr;
	}
	
	public void drawGrid(Graphics g, JPanel p,Vector2 _renderpos) {
//		for(int i = _renderpos.y + renderDist; i < _renderpos.y + renderDist; i++) {
//			for(int j = _renderpos.x + renderDist; j < _renderpos.x + renderDist; j++) {
//				if(arr[i][j] == null) {
//					g.setColor(Color.green);
//					g.drawRect(i*boxSize, j*boxSize, boxSize, boxSize);
//				}
//				else {
//					if(arr[i][j].img!=null)
//						g.drawImage(arr[i][j].img, i*boxSize, j*boxSize, p);
//				}
//			}
//		}
	}
	
	//Grid fuctions
	public void addObj(Vector2 _pos) {
		
	}
}
