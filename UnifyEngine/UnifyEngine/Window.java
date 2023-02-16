package UnifyEngine;

import javax.swing.*;
import java.awt.*;

public abstract class Window extends JFrame {
	protected int wd_width, wd_height;
	protected String wd_title;
	protected ImageIcon engine_icon = new ImageIcon("UnifyEngine/UnifyEngine/resources/UNiFY-Engine.png");
	
	protected Window(int _width, int _height, String _title) {
		super(_title);
		this.wd_width = _width;
		this.wd_height = _height;
		this.wd_title = _title;
	}
		
	protected abstract void init();
	public abstract void quit();

}
