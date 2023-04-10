package MovBlok.Scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import UnifyEngine.Scene;

public class WinScene extends Scene{

	@Override
	protected void Start() {
		// TODO Auto-generated method stub
		setBackground(Color.cyan);
		
	}

	@Override
	protected void Update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void LateUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doDrawing(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("YOU WIN!!!!", 100, 100);
	}

}
