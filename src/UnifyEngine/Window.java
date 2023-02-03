package UnifyEngine;

import javax.swing.*;

public class Window {
	private int wd_width, wd_height;
	private String wd_title;
	private JFrame wd_frame = null;
	private ImageIcon engine_icon = new ImageIcon("src/UnifyEngine/resources/UNiFY-Engine.png");
	
	private static Window window = null;
	
	private Window(int _width, int _height, String _title) {
		this.wd_width = _width;
		this.wd_height = _height;
		this.wd_title = _title;
	}
	
	public static Window get(int _width, int _height, String _title) {
		if(window==null) {
			window = new Window(_width, _height, _title);
		}
		return window;
	}
	
	public void run() {
		System.out.println("Hello World!");
		wd_frame = new JFrame(wd_title);
		wd_frame.setIconImage(engine_icon.getImage());
		wd_frame.setSize(wd_width, wd_height);
		wd_frame.setVisible(true);
	}
	
	public void setIcon(ImageIcon _icon) {
		if(wd_frame!=null) {
			wd_frame.setIconImage(_icon.getImage());
		}
	}
}
