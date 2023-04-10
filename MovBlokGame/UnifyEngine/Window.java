package UnifyEngine;

import javax.swing.*;
import java.awt.*;

public abstract class Window extends JFrame{
	//Window values
	protected String wd_title;
	public String getTitle() {
		return wd_title;
	}
	
	protected Image engine_icon = (new ImageIcon("UnifyEngine/resources/UNiFY-Engine.png")).getImage();
	protected boolean wd_exit = false;
	
	protected Window(int _width, int _height, String _title) {
		super(_title);
		setSize(_width, _height);
		this.wd_title = _title;
	}
	
	public void run() {
		Debug.Log("Game Initialize");
		init();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		Debug.Log("Game Loop");
		gameLoop();
		Debug.Log("Game Quit");
		quit();
		setVisible(false);
		dispose();
	}
	
	protected abstract void init();
	protected abstract void gameLoop();
	protected abstract void quit();
	
}
