package MovBlok.Scripts;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import MovBlok.Scenes.*;
import UnifyEngine.Window;
import UnifyEngine.Debug;

public class MovBlokApp extends Window {
	//Singleton
	private static MovBlokApp window = null;
	public static MovBlokApp GetWindow() {
		return window;
	}
	//Game values
	private ApplicationStates currentState = ApplicationStates.Menu;
	public void SetCurrentState(ApplicationStates _state) {
		currentState = _state;
	}
	//Game scenes
	private GameScene gamePan;
	private MenuScene menuPan;
	
	private MovBlokApp(int _width, int _height, String _title) {
		super(_width,_height,_title);
	}
	
	public static MovBlokApp get(int _width, int _height, String _title) {
		Debug.enable = true;
		if(window==null) {
			window = new MovBlokApp(_width,_height,_title);
		}
		return window;
	}
	
	@Override
	protected void init() {
		setIconImage(engine_icon);
		setLayout(new BorderLayout());
	}
	
	@Override
	protected void gameLoop() {
		while(!wd_exit) {
			switch(currentState) {
				case Menu:
				{
					Debug.Log("Menu==========");
					menuPan = new MenuScene();
					window.add(menuPan);
					menuPan.execute();
					window.remove(menuPan);
					menuPan=null;
					break;
				}
				case Game:
				{
					Debug.Log("Game==========");
					gamePan = new GameScene();
					window.add(gamePan);
					gamePan.execute();
					window.remove(gamePan);
					gamePan=null;
					break;
				}
				case Quit:
				{
					Debug.Log("Quit==========");
					wd_exit = true;
					break;
				}
			}
		}
	}

	@Override
	protected void quit() {
		
	}
}
