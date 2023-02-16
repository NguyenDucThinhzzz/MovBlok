package UnifyEngine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;

public abstract class Scene extends JPanel{
	private boolean exit = false;

	protected Scene(JFrame _frame) {
		initScene(_frame);
		SceneLoop();
	}
	
	private void initScene(JFrame _frame) {
		setBackground(Color.black);
        setFocusable(true);
        _frame.add(this);
	}
	
	private void SceneLoop() {
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
	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    protected abstract void doDrawing(Graphics g);
}
