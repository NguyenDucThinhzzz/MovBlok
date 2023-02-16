package MovBlok.Scripts;

import java.awt.GridLayout;

import javax.swing.JFrame;

import UnifyEngine.Window;

public class MovBlokApp extends Window {
	
	private static MovBlokApp window = null;
	
	private MovBlokApp(int _width, int _height, String _title) {
		super(_width,_height,_title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(engine_icon.getImage());
		setLayout(new GridLayout(2, 2));
		setSize(wd_width, wd_height);
		setVisible(true);
		init();
		quit();
	}
	
	public static MovBlokApp get(int _width, int _height, String _title) {
		if(window==null) {
			window = new MovBlokApp(_width,_height,_title);
		}
		return window;
	}
	
	@Override
	protected void init() {
		MovBlokCore.run(this);
	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub
		
	}
}
