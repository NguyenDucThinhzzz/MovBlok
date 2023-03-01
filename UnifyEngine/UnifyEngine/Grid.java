        package UnifyEngine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import MovBlok.Scripts.MovBlokApp;

public class Grid {
	
	public GameObject[][] arr;
	public int bound_x=0,bound_y=0;
	
	int hlf_wdt = MovBlokApp.GetWindow().getWidth()/2;
	int hlf_hgt = MovBlokApp.GetWindow().getHeight()/2;
	public int renderDist;
	public int boxSize;
	
	public Image defaultTile;
	
	public Grid(int _x,int _y, Image _defaultTile) {
		this.bound_x = _x;
		this.bound_y = _y;
		this.defaultTile = _defaultTile;
		arr = new GameObject[_x][_y];
		for(GameObject[] i: arr) {
			for(GameObject j: i) {
				j=null;
			}
		}
		//Aspect ratio change in the future for now it's 1280x720 (16:9)
		boxSize=10;
		renderDist = hlf_wdt/boxSize+1;
	}
//	public Grid(int _x, int _y, GameObject[][] _arr) {
//		this.bound_x = _x;
//		this.bound_y = _y;
//		arr = new GameObject[_x][_y];
//		this.arr = _arr;
//	}
	
	public void drawGrid(Graphics g, JPanel p, Vector2 _renderpos) {
		int top = _renderpos.y + renderDist;
		int left = _renderpos.x - renderDist;
		int bot = _renderpos.y - renderDist;
		int right = _renderpos.x + renderDist;
		
		g.setColor(Color.green);
		for(int i = bot; i <=top ; i++) {
			for(int j = left; j <= right; j++) {
				if(i<0 || j<0 || i>=bound_x || j>=bound_y) continue;
				if(arr[i][j] != null) {
					if(arr[i][j].img!=null) {
						g.drawImage(arr[i][j].img, (j-left-renderDist)*boxSize+hlf_wdt-boxSize/2, (i-bot-renderDist)*boxSize+hlf_hgt-boxSize/2, boxSize, boxSize, p);
					}
				}
				else {
					//g.drawImage(defaultTile , (j-left-renderDist)*boxSize+hlf_wdt-boxSize/2, (i-bot-renderDist)*boxSize+hlf_hgt-boxSize/2, boxSize, boxSize, p);
				}
				if(Debug.enable) {
					g.drawRect((j-left-renderDist)*boxSize+hlf_wdt-boxSize/2, (i-bot-renderDist)*boxSize+hlf_hgt-boxSize/2, boxSize, boxSize);
					g.drawString("( "+ i +", "+j+")",(j-left-renderDist)*boxSize+boxSize/4+hlf_wdt-boxSize/2, (i-bot-renderDist)*boxSize+boxSize/2+hlf_hgt-boxSize/2);
				}
			}
		}
		if(Debug.enable) {
			g.setColor(Color.pink);
			g.drawRect(hlf_wdt-boxSize/2, hlf_hgt-boxSize/2, boxSize, boxSize);
		}
	}
	
	//Grid fuctions
	public void addObj(Vector2 _pos) {
		
	}
}
