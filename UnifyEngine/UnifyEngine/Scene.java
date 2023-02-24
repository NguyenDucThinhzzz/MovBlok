package UnifyEngine;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

public abstract class Scene extends JPanel{
	private boolean exit = false;

	protected Scene() {
	}
    
	private void initScene() {
		setBackground(Color.black);
        setFocusable(true);
	}
	
	private void sceneLoop() {
		Start();
		while(!exit) {
			Update();
			LateUpdate();
			repaint();
		}
	}
	
	protected abstract void Start();
	
	protected abstract void Update();
	
	protected abstract void LateUpdate();
	
	protected void exitScene() {
		exit = true;
	}
	
    public void execute() {
    	Debug.Log("\tScene Execute");
		initScene();
		sceneLoop();
    }
	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    protected abstract void doDrawing(Graphics g);
}
