package MovBlok.Scripts;

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
	public GameStates currentState = GameStates.Game;
	//Game scenes
	private GameScene gamePan;
	private MenuScene menuPan;
	
	private MovBlokApp(int _width, int _height, String _title) {
		super(_width,_height,_title);
	}
	
	public static MovBlokApp get(int _width, int _height, String _title) {
		Debug.enable = false;
		if(window==null) {
			window = new MovBlokApp(_width,_height,_title);
		}
		return window;
	}
	
	@Override
	protected void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(engine_icon);
		setLayout(new GridLayout(1,4));
		setVisible(true);
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
					break;
				}
				case Game:
				{
					Debug.Log("Game==========");
					gamePan = new GameScene();
					window.add(gamePan);
					gamePan.execute();
					window.remove(gamePan);
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
		// TODO Auto-generated method stub
		
	}
}
