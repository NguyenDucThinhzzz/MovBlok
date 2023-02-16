package MovBlok.Scenes;

import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.*;
import UnifyEngine.Scene;
import javax.swing.JPanel;

public class GameScene extends Scene {
	public GameScene() {
		super(new JFrame());
	}

	@Override
	protected void Start() {
		
	}

	@Override
	protected void Update() {
		
	}

	@Override
	protected void LateUpdate() {
		
	}
	
	//testing
	int WIDTH = 1, HEIGHT = 10;
	@Override
	protected void doDrawing(Graphics g) {
		
		g.drawOval(0, 0, WIDTH, HEIGHT);
		WIDTH = WIDTH%10+1;
		HEIGHT = HEIGHT%10+1;
	}
	
	
}
