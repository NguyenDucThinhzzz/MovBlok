package MovBlok.Scripts;

import UnifyEngine.*;
import javax.swing.*;

public class MovBlokCore extends UnifyCore {
	
	private static MovBlokCore core= null;
	
	private JFrame frame = null; 
	private GamePanel gamePan;
	private MainMenuPanel menuPan;
	
	private MovBlokCore(Window _wd) {
		super(_wd);
	}
	
	@Override
	public void Start() {
		frame  = window.GetJFrame();
		frame.add(menuPan);
	}
	@Override
	public void Update() {
		
	}
	@Override
	public void LateUpdate() {
		
	}
	
	public static void run(Window _wd) {
		core = new MovBlokCore(_wd);
	}
}
