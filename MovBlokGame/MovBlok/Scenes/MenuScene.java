package MovBlok.Scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import MovBlok.Scripts.MovBlokApp;
import MovBlok.Scripts.EMovBlokScenes;
import UnifyEngine.Debug;
import UnifyEngine.Scene;

public class MenuScene extends Scene implements ActionListener{
	protected int width = MovBlokApp.GetWindow().getWidth()/2;
	protected int height = MovBlokApp.GetWindow().getHeight()/2;

	private Font mainFont = (new Font("TimesRoman",Font.BOLD,100));
	private BufferedImage background;
	
	private JButton startBut = null;
	private JButton mapEditorBut = null;
	private JButton quitBut = null;
	
	public MenuScene() {
		super();
	}
	
	@Override
	protected void loadAssets() {
		// TODO Auto-generated method stub
		//Buttons
		int buttonWidth = 100;
		int buttonHeight = 40;
		
		startBut = new JButton("Start Game");
        startBut.setFocusable(false);
		startBut.setBounds(width-buttonWidth/2, height+30, buttonWidth, buttonHeight);
		startBut.addActionListener(this);
		
		mapEditorBut = new JButton("Create Map");
		mapEditorBut.setFocusable(false);
		mapEditorBut.setBounds(width-buttonWidth/2, height+90, buttonWidth, buttonHeight);
		mapEditorBut.addActionListener(this);
		
		quitBut = new JButton("Quit Game");
		quitBut.setFocusable(false);
		quitBut.setBounds(width-buttonWidth/2, height+150, buttonWidth, buttonHeight);
		quitBut.addActionListener(this);
		
		//Images
		try {
			background = ImageIO.read(new File("MovBlok/resources/yasuo.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected void Awake() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void Start() {
		this.setBackground(Color.black);
		this.add(startBut);
		this.add(mapEditorBut);
		this.add(quitBut);
	}

	@Override
	protected void Update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void LateUpdate() {
		// TODO Auto-generated method stub
		
	}
	
	//testing
	int WIDTH = 1, HEIGHT = 100;
	@Override
	protected void doDrawing(Graphics g) {
		g.drawImage(background, 0,0, width*2,height*2, this);
		g.setColor(Color.RED);
		g.setFont(mainFont);
		g.drawString("MovBlok", width/2+175, height-100);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startBut) {
			MovBlokApp.GetWindow().setCurrentState(EMovBlokScenes.Game);
		}
		if(e.getSource() == mapEditorBut) {
			MovBlokApp.GetWindow().setCurrentState(EMovBlokScenes.MapCreate);
		}
		if(e.getSource() == quitBut) {
			MovBlokApp.GetWindow().setCurrentState(EMovBlokScenes.Quit);
		}
		exitScene();
	}	
}
