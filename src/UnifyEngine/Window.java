package UnifyEngine;

import javax.swing.*;
import java.awt.*;

public abstract class Window {
	private int wd_width, wd_height;
	private String wd_title;
	private ImageIcon engine_icon = new ImageIcon("src/UnifyEngine/resources/UNiFY-Engine.png");
	
	private static JFrame wd_frame = null;
	
	protected Window(int _width, int _height, String _title) {
		this.wd_width = _width;
		this.wd_height = _height;
		this.wd_title = _title;
	}
	
	public void run() {
		wd_frame = new JFrame(wd_title);
		wd_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wd_frame.setIconImage(engine_icon.getImage());
		wd_frame.setSize(wd_width, wd_height);
		wd_frame.setVisible(true);
		init();
		quit();
	}
	public void quit() {
		wd_frame.setVisible(false);
		wd_frame.dispose();
	}
	
	public static JFrame GetJFrame() {
		return wd_frame;
	}
	
	public void setIcon(ImageIcon _icon) {
		if(wd_frame!=null) {
			wd_frame.setIconImage(_icon.getImage());
		}
	}
	
	protected abstract void init();
}
