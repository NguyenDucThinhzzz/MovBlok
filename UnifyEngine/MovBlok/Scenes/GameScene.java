package MovBlok.Scenes;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

import UnifyEngine.Scene;
import UnifyEngine.Debug;
import UnifyEngine.Grid;
import UnifyEngine.Vector2;

import MovBlok.Scripts.MovBlokApp;
import MovBlok.Scripts.Player;

public class GameScene extends Scene {
	
	private Grid grd;
	private Player plr;
	
	public GameScene() {
		super();
		grd = new Grid(20, 10, (new ImageIcon("UnifyEngine/resources/None_Tile.png")).getImage());
		plr = new Player(5,5);
		grd.arr[plr.position.y][plr.position.x] = plr;
	}

	@Override
	protected void Start() {
		MovBlokApp.GetWindow().addKeyListener(new ControlAdapter());
	}

	@Override
	protected void Update() {
		
	}

	@Override
	protected void LateUpdate() {
		
	}
	
	@Override
	protected void doDrawing(Graphics g) {
		g.drawImage((new ImageIcon("UnifyEngine/resources/UNiFY-Engine.png")).getImage(),0,0,this);
		grd.drawGrid(g, this, plr.position);

	}
	
	public class ControlAdapter extends KeyAdapter {
		
	    @Override
	    public void keyPressed(KeyEvent e) {

	        int key = e.getKeyCode();

	        if (key == KeyEvent.VK_ESCAPE) {
	        	Debug.Log("\t\tPause Menu");
	        	return;
	        }
	        
	        if (key == KeyEvent.VK_A && plr.position.x>0) {
	        	Debug.Log("L");
	        	grd.switchObj(new Vector2(plr.position.x-1,plr.position.y), plr.position);
	        	plr.position.x--;
	        	return;
	        }

	        if (key == KeyEvent.VK_D && plr.position.x<grd.GetGridBoundX()-1) {
	        	Debug.Log("R");
	        	grd.switchObj(new Vector2(plr.position.x+1,plr.position.y), plr.position);
	        	plr.position.x++;
	        	return;
	        }

	        if (key == KeyEvent.VK_W && plr.position.y>0) {
	        	Debug.Log("U");
	        	grd.switchObj(new Vector2(plr.position.x,plr.position.y-1), plr.position);
	        	plr.position.y--;
	        	return;
	        }

	        if (key == KeyEvent.VK_S && plr.position.y<grd.GetGridBoundY()-1) {
	        	Debug.Log("D");
	        	grd.switchObj(new Vector2(plr.position.x,plr.position.y+1), plr.position);
	        	plr.position.y++;
	        	return;
	        }
	    }
	}
	
}
