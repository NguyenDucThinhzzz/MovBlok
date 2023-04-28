package MovBlok.Scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.*;

import UnifyEngine.Scene;
import UnifyEngine.Debug;
import UnifyEngine.GameObject;
import UnifyEngine.Grid;
import UnifyEngine.Vector2;
import MovBlok.Objects.*;
import MovBlok.Objects.Box;
import MovBlok.Scripts.DataHandler;
import MovBlok.Scripts.EGameStates;
import MovBlok.Scripts.MovBlokApp;
import MovBlok.Scripts.EMovBlokScenes;
import MovBlok.Scripts.LoadingBar;

public class MapCreateScene extends Scene implements ActionListener{
	//Game settings
	private int width;
	private int height;
	private int renderDist;
	private int boxSize;
	//Game data
	private DataHandler dataHandler;
	private EGameStates curGameState = EGameStates.Creation;
	private EGameStates lastGameState = curGameState;
	private Grid grd;
	private Player editor;
	private Player plrSpawn;
	private GameObject selector = new Box();
	
	//Pause Screen Variables
	private JButton resumeBut;
	private JButton saveMapBut;
	private JButton newMapBut;
	private JButton menuBut;
	private JTextField fileNameField;
	
	//Creation Screen Variables
	private JTextField gridX;
	private JTextField gridY;
	private JButton submitBut;
	private boolean finishInput = false;
	
	private LoadingBar loadBar = new LoadingBar(this);
	
	public MapCreateScene() {
		super();
	}
	

	@Override
	protected void loadAssets() {
		//Buttons
		int buttonWidth= 150;
		int buttonHeight= 30;
		
		resumeBut = new JButton("Resume");
		resumeBut.setFocusable(false);
		resumeBut.setBounds(width-buttonWidth/2, height+30, buttonWidth, buttonHeight);
		resumeBut.addActionListener(this);
		
		saveMapBut = new JButton("Save Map As");
		saveMapBut.setFocusable(false);
		saveMapBut.setBounds(width-buttonWidth/2, height+90, buttonWidth, buttonHeight);
		saveMapBut.addActionListener(this);
		
		newMapBut = new JButton("Create New Map");
		newMapBut.setFocusable(false);
		newMapBut.setBounds(width-buttonWidth/2, height+150, buttonWidth, buttonHeight);
		newMapBut.addActionListener(this);
		
		menuBut = new JButton("Return to Menu");
		menuBut.setFocusable(false);
		menuBut.setBounds(width-buttonWidth/2, height+210, buttonWidth, buttonHeight);
		menuBut.addActionListener(this);
		
		submitBut = new JButton("Confirm");
		submitBut.setFocusable(false);
		submitBut.setBounds(width-buttonWidth/2, height+210, buttonWidth, 40);
		submitBut.addActionListener(this);
		
		//Text Fields
		gridX = new JTextField("Grid X");
		gridX.setBounds(width-buttonWidth/2, height+30, buttonWidth, 40);
		
		gridY = new JTextField("Grid Y");
		gridY.setBounds(width-buttonWidth/2, height+90, buttonWidth, 40);
		
		fileNameField = new JTextField("Save File Name");
		fileNameField.setBounds(width+buttonWidth/2+20, height+90, buttonWidth, buttonHeight);
	}
	
	@Override
	protected void Awake() {
		width = MovBlokApp.GetWindow().getWidth()/2;
		height = MovBlokApp.GetWindow().getHeight()/2;
		MovBlokApp.GetWindow().addKeyListener(new ControlAdapter());

		dataHandler = new DataHandler();
	}

	@Override
	protected void Start() {
		boxSize=80;
		renderDist = width/boxSize+1;

		//Read map data
		inputMapInfo();
		loadBar.addProgress(0.1f);

		//Aspect ratio change in the future for now it's 1280x720 (16:9)
		for(int i=0;i<grd.getBoundY();i++) {
			for(int j=0;j<grd.getBoundX();j++) {
				if(grd.getObj(j,i)==null){
					grd.addObj(null);
				}
				loadBar.addProgress(1.0f/(grd.getBoundX()*grd.getBoundY()));
			}
		}
		
//		//Testing
//		grd.addObj(new Box(3,1));
//		for(int i=0;i<grd.getBoundX();i++) {
//			grd.addObj(new Wall(i,0));
//			grd.addObj(new Wall(i,grd.getBoundY()-1));
//		}
//		for(int i=0;i<grd.getBoundY();i++) {
//			grd.addObj(new Wall(0,i));
//			grd.addObj(new Wall(grd.getBoundX()-1,i));
//		}
//		for(int i=1;i<7;i++) {
//			if(i==5) continue;
//			grd.addObj(new Wall(i,2));
//		}
//		grd.addObj(new Portal(6,6));
		loadBar.addProgress(0.1f);

		loadBar.addProgress(1);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		game();
	}
	
	@Override
	protected void Update() {
		
	}
	@Override
	protected void LateUpdate() {
		
	}
	//Draw
	@Override
	protected void doDrawing(Graphics g) {
		switch(curGameState) {
		case Loading:
			g.setColor(Color.gray);
			g.setFont(new Font("TimesRoman",Font.BOLD,100));
			g.drawString("LOADING", width/2+220, 300);
			loadBar.draw(g, width-250, height+10, 500, 20);
			break;
		case Creation:
			drawCreationUI(g);
			break;
		case InGame:
			drawGrid(g);
			selector.draw(g, boxSize, height*2-boxSize*2, boxSize, boxSize, this);
			break;
		case PauseGame:
			g.setColor(Color.gray);
			g.setFont(new Font("TimesRoman",Font.BOLD,100));
			g.drawString("PAUSE", width/2+220, 300);
			break;
		default:
			break;
		}

	}
	public void drawGrid(Graphics g) {
		try {
			int top = (int)editor.getPos().y + renderDist;
			int left = (int)editor.getPos().x - renderDist;
			int bot = (int)editor.getPos().y - renderDist;
			int right = (int)editor.getPos().x + renderDist;
			
			g.setColor(Color.green);
			for(int i = bot; i <=top ; i++) {
				for(int j = left; j <= right; j++) {
					if(i<0 || j<0 || j>=grd.getBoundX() || i>=grd.getBoundY()) continue;
					if(grd.getObj(j,i) != null) {
						grd.getObj(j,i).draw(g,(j-left-renderDist)*boxSize+width-boxSize/2, (i-bot-renderDist)*boxSize+height-boxSize/2, boxSize, boxSize, this);
					}
					if(Debug.enable) {
						g.drawRect((j-left-renderDist)*boxSize+width-boxSize/2, (i-bot-renderDist)*boxSize+height-boxSize/2, boxSize, boxSize);
						g.drawString("( "+ i +", "+j+")",(j-left-renderDist)*boxSize+boxSize/4+width-boxSize/2, (i-bot-renderDist)*boxSize+boxSize/2+height-boxSize/2);
					}
				}
			}
			if(Debug.enable) {
				g.setColor(Color.pink);
				g.drawRect(width-boxSize/2, height-boxSize/2, boxSize, boxSize);
			}
		}
		catch(NullPointerException ex) {
			
		}		
		finally{
			g.setColor(Color.green);
			g.drawLine(width-10, height, width+10, height);
			g.drawLine(width, height-10, width, height+10);
		}
	}
	private void drawCreationUI(Graphics g) {
		g.setColor(Color.gray);
		g.setFont(new Font("TimesRoman",Font.BOLD,100));
		g.drawString("Input Map Info", width/2, 200);
	}
	
	//Functions
	private void setLastState() {
		lastGameState = curGameState;
	}

	private void inputMapInfo() {
		setLastState();
		curGameState = EGameStates.Creation;
		plrSpawn = new Player(0,0);

		this.add(gridX);
		this.add(gridY);
		this.add(submitBut);
		while(!finishInput) {
			//Do random things
			setLastState();
		}
		MovBlokApp.GetWindow().requestFocus();
		this.remove(gridX);
		this.remove(gridY);
		this.remove(submitBut);
		
		finishInput = false;
	}
	
	private void load() {
		setLastState();
		curGameState = EGameStates.Loading;
	}
	private void pause() {
		if(curGameState==EGameStates.PauseGame) {
			curGameState = lastGameState;
			MovBlokApp.GetWindow().requestFocus();
			this.remove(resumeBut);
			this.remove(saveMapBut);
			this.remove(newMapBut);
			this.remove(menuBut);
			this.remove(fileNameField);
		}
		else {
			setLastState();
			curGameState = EGameStates.PauseGame;
			this.add(resumeBut);
			this.add(saveMapBut);
			this.add(newMapBut);
			this.add(menuBut);
			this.add(fileNameField);
		}
	}
	private void game() {
		setLastState();
		curGameState = EGameStates.InGame;
	}
	//Buttons On Click
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == resumeBut) {
			pause();
		}
		if(e.getSource() == saveMapBut) {
    		dataHandler.setDataFile("MovBlok/MapData/"+fileNameField.getText());
    		dataHandler.openFileWrite();
    		dataHandler.WriteFileData(grd,plrSpawn);
    		dataHandler.closeFile();
        	lastGameState = EGameStates.InGame;
    		pause();
		}
		if(e.getSource() == newMapBut) {
			
		}
		if(e.getSource() == menuBut) {
        	MovBlokApp.GetWindow().setCurrentState(EMovBlokScenes.Menu);
        	exitScene();
		}
		if(e.getSource() == submitBut) {
			try {
				int x = Integer.parseInt(gridX.getText());
				int y = Integer.parseInt(gridY.getText());
				load();
				grd = new Grid(x,y);
				editor = new Player(0,0);
				loadBar.addProgress(0.01f);
				finishInput = true;
			}
			catch(NumberFormatException ex) {
				Debug.LogError("You can only pass in Integer value!");
			}
		}
	}
	
	public class ControlAdapter extends KeyAdapter {
		
	    @Override
	    public void keyPressed(KeyEvent e) {
	    	
	        int key = e.getKeyCode();
	        
	        if(curGameState==EGameStates.Loading) return; 

	        if (key == KeyEvent.VK_ESCAPE) {
	        	pause();
	        	return;
	        }
	        
	        if(curGameState!=EGameStates.InGame) return; 
	        
	        if(key == KeyEvent.VK_1) {
	        	selector = new Box();
	        	return;
	        }
	        if(key == KeyEvent.VK_2) {
	        	selector = new Ground();
	        	return;
	        }
	        if(key == KeyEvent.VK_3) {
	        	selector = new Player();
	        	return;
	        }
	        if(key == KeyEvent.VK_4) {
	        	selector = new Portal();
	        	return;
	        }
	        if(key == KeyEvent.VK_5) {
	        	selector = new Wall();
	        	return;
	        }
	        if(key == KeyEvent.VK_6) {
	        	selector = null;
	        	return;
	        }
	        if(key == KeyEvent.VK_SPACE) {
	        	if(selector == null)
	        		grd.setNull((int)editor.getPos().x,(int)editor.getPos().y);
	        	if(selector instanceof Box)
	        		grd.addObj(new Box((int)editor.getPos().x,(int)editor.getPos().y));
	        	if(selector instanceof Ground)
	        		grd.addObj(new Ground((int)editor.getPos().x,(int)editor.getPos().y));
	        	if(selector instanceof Player) {
	        		grd.addObj(new Ground((int)plrSpawn.getPos().x,(int)plrSpawn.getPos().y));
	        		plrSpawn = new Player(editor.getPos());
	        		grd.addObj(new Player((int)editor.getPos().x,(int)editor.getPos().y));  		
	        	}
	        	if(selector instanceof Portal)
	        		grd.addObj(new Portal((int)editor.getPos().x,(int)editor.getPos().y));
	        	if(selector instanceof Wall)
	        		grd.addObj(new Wall((int)editor.getPos().x,(int)editor.getPos().y));
	        	return;
	        }
	        
	        if ((key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) && editor.getPos().x>0) {
				editor.getPos().x--;
				
	        	return;
	        }

	        if ((key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) && editor.getPos().x<grd.getBoundX()-1) {
				editor.getPos().x++;
				
	        	return;
	        }

	        if ((key == KeyEvent.VK_W || key == KeyEvent.VK_UP)&& editor.getPos().y>0) {
				editor.getPos().y--;
				
				return;
	        }

	        if ((key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) && editor.getPos().y<grd.getBoundY()-1) {
				editor.getPos().y++;
				
	        	return;
	        }
	    }
	}
	
}
