package MovBlok.Scenes;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;
import UnifyEngine.Scene;

public class MenuScene extends Scene{
	public MenuScene(JFrame _frame) {
		super(_frame);
	}

	@Override
	protected void Start() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void Update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void LateUpdate() {
		// TODO Auto-generated method stub
		
	}
	
	//testing
	int WIDTH = 1, HEIGHT = 10;
	@Override
	protected void doDrawing(Graphics g) {
		
		g.drawOval(50, 50, WIDTH, HEIGHT);
		WIDTH = WIDTH%10+1;
		HEIGHT = HEIGHT%10+1;
	}
	
}
