package MovBlok.Scripts;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;

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
	private EMovBlokScenes currentScene = EMovBlokScenes.Menu;
	public void setCurrentState(EMovBlokScenes _state) {
		currentScene = _state;
	}
	//Game scenes
	private GameScene gamePan;
	private MapCreateScene mapCreatePan;
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
		setIconImage((new ImageIcon("MovBlok/resources/box_low_res.png")).getImage());
		setLayout(new BorderLayout());
	}
	
	@Override
	protected void gameLoop() {
		while(!wd_exit) {
			switch(currentScene) {
				case Menu:
				{
					Debug.Log("==========Menu==========");
					menuPan = new MenuScene();
					window.add(menuPan);
					menuPan.execute();
					window.remove(menuPan);
					menuPan=null;
					break;
				}
				case Game:
				{
					Debug.Log("==========Game==========");
					gamePan = new GameScene();
					window.add(gamePan);
					gamePan.execute();
					window.remove(gamePan);
					gamePan=null;
					break;
				}
				case MapCreate:
				{
					Debug.Log("==========Map Creator==========");
					mapCreatePan = new MapCreateScene();
					window.add(mapCreatePan);
					mapCreatePan.execute();
					window.remove(mapCreatePan);
					mapCreatePan=null;
					break;
				}
				case Quit:
				{
					Debug.Log("==========Quit==========");
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
