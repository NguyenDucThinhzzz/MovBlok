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
		grd = new Grid(100,100, (new ImageIcon("MovBlok/resources/Stone_Tile.png")).getImage());
		plr = new Player(25,25);
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
	        }
	    }
	}
}
