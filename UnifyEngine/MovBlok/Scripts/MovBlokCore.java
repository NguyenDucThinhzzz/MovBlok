package MovBlok.Scripts;

import MovBlok.Scenes.*;
import UnifyEngine.*;

public class MovBlokCore extends UnifyCore {
	
	private boolean exit = false;
	
	private static MovBlokCore core= null;
	private GameStates currentState = GameStates.Menu;
	
	private GameScene gamePan;
	private MenuScene menuPan;
	
	private MovBlokCore(MovBlokApp _app) {
		super(_app);
		GameLoop();
	}

	public void GameLoop() {
		while(!exit) {
			switch(currentState) {
				case Menu:
				{
					testPan a = new testPan();
					window.add(a);
					//menuPan = new MenuScene(window.GetJFrame());
					break;
				}
				case Game:
				{
					
					break;
				}
				case Quit:
				{
					exit = true;
					break;
				}
			}
		}
		window.quit();
	}
	public static void run(MovBlokApp _app) {
		core = new MovBlokCore(_app);
	}
}
