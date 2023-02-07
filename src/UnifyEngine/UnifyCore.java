package UnifyEngine;

import UnifyEngine.Window;
import javax.swing.*;

public abstract class UnifyCore{
	
	protected static Window window = null;
	
	protected UnifyCore(Window _wd) {
		if(_wd==null) throw new NullPointerException();
		window = _wd;
		initUnify();
		GameLoop();
	}
	
	private void initUnify() {
		
	}
	
	private void GameLoop() {
		Start();
		while(true) {
			Update();
			LateUpdate();
		}
	}
	
	protected abstract void Start();
	
	protected abstract void Update();
	
	protected abstract void LateUpdate();
	
}
