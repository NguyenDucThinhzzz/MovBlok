package MovBlok.Scripts;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.TimeUnit;

import UnifyEngine.Scene;

public class LoadingBar {
	private Scene scene;
	private double progress;
	
	public LoadingBar(Scene _scene) {
		scene = _scene;
		progress = 0;
	}
	public double getProgress(){
		return progress;
	}
	public void reset(){
		progress = 0;
	}
	public void addProgress(double _amount) {
		if(progress+_amount>1f) {
			progress = 1f;
		}
		else {
			progress+=_amount;
		}
		scene.repaint();
	}
	
	public void draw(Graphics g,int _x,int _y,int _width, int _height) {
		g.setColor(Color.green);
		g.fillRect(_x, _y, (int)(_width*progress), _height);
	}
}
