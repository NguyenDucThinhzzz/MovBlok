package MovBlok.Scripts;

import java.awt.GridLayout;
import javax.swing.JFrame;
import MovBlok.Scenes.*;
import UnifyEngine.Window;

public class MovBlokApp extends Window {
	//Singleton
	private static MovBlokApp window = null;
	public static MovBlokApp GetWindow() {
		return window;
	}
	//Game values
	private GameStates currentState = GameStates.Menu;
	//Game scenes
	private GameScene gamePan;
	private MenuScene menuPan;
	
	private MovBlokApp(int _width, int _height, String _title) {
		super(_width,_height,_title);
	}
	
	public static MovBlokApp get(int _width, int _height, String _title) {
		if(window==null) {
			window = new MovBlokApp(_width,_height,_title);
		}
		return window;
	}
	
	public static void run() {
		if(window == null) return;
		window.init();
		window.gameLoop();
		window.quit();
	}
	
	@Override
	protected void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(engine_icon.getImage());
		setLayout(new GridLayout(1,4));
		setVisible(true);
	}
	
	@Override
	protected void gameLoop() {
		while(!wd_exit) {
			switch(currentState) {
				case Menu:
				{
					menuPan = new MenuScene();
					window.add(menuPan);
					menuPan.execute();
					window.remove(menuPan);
					break;
				}
				case Game:
				{
					
					break;
				}
				case Quit:
				{
					wd_exit = true;
					break;
				}
			}
		}
	}

	@Override
	protected void quit() {
		// TODO Auto-generated method stub
		
	}
}
