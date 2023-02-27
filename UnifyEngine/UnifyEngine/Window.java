package UnifyEngine;

import javax.swing.*;
import java.awt.*;

public abstract class Window extends JFrame {
	//Window values
	protected String wd_title;
	public String GetTitle() {
		return wd_title;
	}
	
	protected ImageIcon engine_icon = new ImageIcon("MovblokGame/UnifyEngine/resources/UNiFY-Engine.png");
	
	protected boolean wd_exit = false;
	
	protected Window(int _width, int _height, String _title) {
		super(_title);
		setSize(_width, _height);
		this.wd_title = _title;
	}
	
	public void run() {
		Debug.Log("Game Initialize");
		init();
		Debug.Log("Game Loop");
		gameLoop();
		Debug.Log("Game Quit");
		quit();
	}
	
	protected abstract void init();
	protected abstract void gameLoop();
	protected abstract void quit();
	
}
