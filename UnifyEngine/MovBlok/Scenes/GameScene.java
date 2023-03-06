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
import MovBlok.Objects.*;
import MovBlok.Objects.Box;
import MovBlok.Scripts.MovBlokApp;

public class GameScene extends Scene {
	
	private Grid grd;
	private Player plr;
	
	public GameScene() {
		super();
		grd = new Grid(20, 10, (new ImageIcon("MovBlok/resources/test_box.png")).getImage());
		plr = new Player(5,5);
		grd.addObj(plr, plr.position);
	}

	@Override
	protected void Start() {
		MovBlokApp.GetWindow().addKeyListener(new ControlAdapter());
		for(int i=0;i<grd.GetGridBoundY();i++) {
			for(int j=0;j<grd.GetGridBoundX();j++) {
				if(grd.getObj(j,i)==null){
					grd.addObj(new Ground(j,i),j,i);
				}
			}
		}
		//Testing
		grd.addObj(new Box(5,6),5,6);
		grd.addObj(new Wall(7,5),7,5);
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
	        	switch(grd.checkObjType(plr.position.x-1,plr.position.y)) {
					case box:
					{
						break;					
					}
					case ground:
					{
						grd.switchObj(new Vector2(plr.position.x-1,plr.position.y), plr.position);
						plr.position.x--;
						break;
					}	
					case none:
					{
						break;
					}
					case wall:
					{
						break;
					}
	        	}
	        	return;
	        }

	        if (key == KeyEvent.VK_D && plr.position.x<grd.GetGridBoundX()-1) {
	        	Debug.Log("R");
	        	switch(grd.checkObjType(plr.position.x+1,plr.position.y)) {
					case box:
					{
						
						break;					
					}
					case ground:
					{
						grd.switchObj(new Vector2(plr.position.x+1,plr.position.y), plr.position);
						plr.position.x++;
						break;
					}	
					case none:
					{
						break;
					}
					case wall:
					{
						break;
					}
	        	}
	        	return;
	        }

	        if (key == KeyEvent.VK_W && plr.position.y>0) {
	        	Debug.Log("U");
	        	switch(grd.checkObjType(plr.position.x,plr.position.y-1)) {
					case box:
					{
						
						break;					
					}
					case ground:
					{
						grd.switchObj(new Vector2(plr.position.x,plr.position.y-1), plr.position);
						plr.position.y--;
						break;
					}	
					case none:
					{
						break;
					}
					case wall:
					{
						break;
					}
	        	}		
	        	return;
	        }

	        if (key == KeyEvent.VK_S && plr.position.y<grd.GetGridBoundY()-1) {
	        	Debug.Log("D");
	        	switch(grd.checkObjType(plr.position.x,plr.position.y+1)) {
					case box:
					{	
						
						break;					
					}
					case ground:
					{
						grd.switchObj(new Vector2(plr.position.x,plr.position.y+1), plr.position);
						plr.position.y++;
						break;
					}	
					case none:
					{
						break;
					}
					case wall:
					{
						break;
					}
	        	}	
	        	return;
	        }
	    }
	}
	
}
