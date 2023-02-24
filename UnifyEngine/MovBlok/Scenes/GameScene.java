package MovBlok.Scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.JPanel;
import UnifyEngine.Scene;
import UnifyEngine.Grid;

import MovBlok.Scripts.MovBlokApp;
import MovBlok.Scripts.Player;

public class GameScene extends Scene {
	
	private Grid grd;
	private static Player plr;
	
	public GameScene() {
		super();
		grd = new Grid(50,50);
		plr = new Player(25,25);
	}

	@Override
	protected void Start() {
		setBackground(Color.gray);
	}

	@Override
	protected void Update() {
		
	}

	@Override
	protected void LateUpdate() {
		
	}
	
	@Override
	protected void doDrawing(Graphics g) {
		int wd = MovBlokApp.GetWindow().getWidth()/2;
		int hd = MovBlokApp.GetWindow().getHeight()/2;
		g.setColor(Color.pink);
		g.drawRect(0,0,80,80);

	}
	
}
