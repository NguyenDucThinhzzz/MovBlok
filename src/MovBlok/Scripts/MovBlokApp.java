package MovBlok.Scripts;

import UnifyEngine.Window;

public class MovBlokApp extends Window {
	
	private static MovBlokApp window = null;
	
	private MovBlokApp(int _width, int _height, String _title) {
		super(_width,_height,_title);
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
}
