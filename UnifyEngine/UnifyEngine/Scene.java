package UnifyEngine;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

public abstract class Scene extends JPanel{
	private boolean exit = false;
	private boolean isDirty;

	protected Scene() {
	}
    
	protected abstract void Start();
	
	protected abstract void Update();
	
	protected abstract void LateUpdate();
	
	protected abstract void doDrawing(Graphics g);
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		doDrawing(g);
	}
	
    public void execute() {
    	Debug.Log("\tScene Execute");
		initScene();
		sceneLoop();
    }
    
    protected void exitScene() {
    	exit = true;
    }
    
    private void initScene() {
    	setBackground(Color.black);
    	setFocusable(false);
    	setVisible(true);
    }
    
    private void sceneLoop() {
    	revalidate();
    	Start();
    	repaint();
    	while(!exit) {
    		Update();
    		LateUpdate();
    		repaint();
    	}
    }
	
    
}
