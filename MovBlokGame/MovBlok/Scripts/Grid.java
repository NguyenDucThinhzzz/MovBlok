package MovBlok.Scripts;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import MovBlok.Objects.Box;
import MovBlok.Objects.Ground;
import MovBlok.Objects.Portal;
import MovBlok.Objects.Wall;
import MovBlok.Scripts.SaveManager.DataHandler;
import UnifyEngine.Debug;
import UnifyEngine.GameObject;
import UnifyEngine.Vector2;

public class Grid {
	
	private GameObject[][] arr;
	private int renderDist;
	private int boxSize;
	private int hlf_wdt;
	private int hlf_hgt;
	
	private int bound_x=0;
	public int getBoundX() {
		return bound_x;
	}
	private int bound_y=0;
	public int getBoundY() {
		return bound_y;
	}
	
	public Grid(int _x,int _y, int _width, int _height) {
		this.bound_x = _x;
		this.bound_y = _y;
		this.hlf_wdt = _width;
		this.hlf_hgt = _height;
		arr = new GameObject[_y][_x];
		for(GameObject[] i: arr) {
			for(GameObject j: i) {
				j=null;
			}
		}
		//Aspect ratio change in the future for now it's 1280x720 (16:9)
		boxSize=80;
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
				if(i<0 || j<0 || j>=bound_x || i>=bound_y) continue;
				if(arr[i][j] != null) {
					arr[i][j].draw(g,(j-left-renderDist)*boxSize+hlf_wdt-boxSize/2, (i-bot-renderDist)*boxSize+hlf_hgt-boxSize/2, boxSize, boxSize, p);
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
	
	//DataHandling
	public Vector2 ReadFileData(String _filePath){
		try {
			Vector2 plrPos = new Vector2();
			int x,y;
			Scanner scn = new Scanner(new File(_filePath));
			//Get x,y of the playing field
			x = scn.nextInt();
			y = scn.nextInt();
			arr = new GameObject[y][x];
			//Read the file data
			for(int i=0;i<y;i++) {
				for(int j=0;j<x;j++) {
					switch(scn.nextInt()) {
					case 0:
						plrPos = new Vector2(i,j);
						break;
					case 1:
						addObj(new Box(j,i));
						break;
					case 2:
						addObj(new Ground(j,i));;
						break;
					case 3:
						addObj(new Wall(j,i));;
						break;
					case 4:
						addObj(new Portal(j,i));;
						break;
					}
				}
			}
			scn.close();
			return plrPos;
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Debug.Log("The file you are trying to read is NULL!");
			return null;
		}	
	}
	
	public void WriteFileData(String _filePath) {
		
		try {
			PrintWriter prt = new PrintWriter(new File(_filePath));
			int x = getBoundX();
			int y = getBoundY();
			prt.println(x+" "+y);
			for(int i=0;i<y;i++) {
				for(int j=0;j<x;j++) {
					prt.print(getObj(j, i).getID()+" ");
				}
				prt.println();
			}
			prt.close();
		}
		catch (FileNotFoundException e) {
			Debug.Log("The file you are trying to read is NULL!");
		}
		catch (ArrayIndexOutOfBoundsException e) {
			Debug.Log("The pointer you are trying to access is NULL!");
		}
	}
	
	//GridFuctions
	public void addObj(GameObject _obj) {
		if(_obj == null) return;
		if(_obj.position.x<0 || _obj.position.y<0 || _obj.position.x>=bound_x || _obj.position.y>=bound_y) return;
		 arr[_obj.position.y][_obj.position.x] = _obj;
	}
	
	public void switchObj(Vector2 _pos1,Vector2 _pos2) {
		if(_pos1.x<0 || _pos1.y<0 || _pos1.x>=bound_x || _pos1.y>=bound_y || _pos2.x<0 || _pos2.y<0 || _pos2.x>=bound_x || _pos2.y>=bound_y) return;
		GameObject _temp = arr[_pos1.y][_pos1.x];
		arr[_pos1.y][_pos1.x] = arr[_pos2.y][_pos2.x];
		arr[_pos2.y][_pos2.x] = _temp;
	}
	
	public GameObject getObj(Vector2 _pos) { 
		if(_pos.x<0 || _pos.y<0 || _pos.x>=bound_x || _pos.y>=bound_y) return null;
		return arr[_pos.y][_pos.x];
	}
	public GameObject getObj(int _x, int _y) {
		if(_x<0 || _y<0 || _x>=bound_x || _y>=bound_y) return null;
		return arr[_y][_x];
	}
	
}
