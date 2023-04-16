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

public class GameScene extends Scene implements ActionListener{
	//Game settings
	private int width;
	private int height;
	private int renderDist;
	private int boxSize;
	//Game data
	private DataHandler dataHandler;
	private EGameStates curGameState = EGameStates.Loading;
	private EGameStates lastGameState = curGameState;
	private Grid grd;
	private Player plr;
	private BufferedImage winBackground;
	
	//Pause Screen Variables
	private JButton resumeBut;
	private JButton restartBut;
	private JButton settingsBut;
	private JButton menuBut;
	
	private LoadingBar loadBar = new LoadingBar(this);
	
	public GameScene() {
		super();
	}
	

	@Override
	protected void loadAssets() {
		//Buttons
		int buttonWidth= 150;
		int buttonHeight= 30;
		
		resumeBut = new JButton("Resume Game");
		resumeBut.setFocusable(false);
		resumeBut.setBounds(width-buttonWidth/2, height+30, buttonWidth, buttonHeight);
		resumeBut.addActionListener(this);
		
		restartBut = new JButton("Restart Level");
		restartBut.setFocusable(false);
		restartBut.setBounds(width-buttonWidth/2, height+90, buttonWidth, buttonHeight);
		restartBut.addActionListener(this);
		
		settingsBut = new JButton("Settings");
		settingsBut.setFocusable(false);
		settingsBut.setBounds(width-buttonWidth/2, height+150, buttonWidth, buttonHeight);
		settingsBut.addActionListener(this);
		
		menuBut = new JButton("Return to Menu");
		menuBut.setFocusable(false);
		menuBut.setBounds(width-buttonWidth/2, height+210, buttonWidth, buttonHeight);
		menuBut.addActionListener(this);
		
		//Images
		try {
			winBackground = ImageIO.read(new File("MovBlok/resources/vanheo.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void Awake() {
		width = MovBlokApp.GetWindow().getWidth()/2;
		height = MovBlokApp.GetWindow().getHeight()/2;
		MovBlokApp.GetWindow().addKeyListener(new ControlAdapter());

		dataHandler = new DataHandler();
		dataHandler.setDataFile("Movblok/MapData/Input");
	}

	@Override
	protected void Start() {
		//Read map data
		loadBar.addProgress(0.1f);
		dataHandler.openFileRead();
		grd = dataHandler.ReadGridData();
		plr = dataHandler.ReadPlayerData();
		dataHandler.closeFile();
		grd.addObj(plr);
		loadBar.addProgress(0.1f);

		//Aspect ratio change in the future for now it's 1280x720 (16:9)
		boxSize=80;
		renderDist = width/boxSize+1;
		
		for(int i=0;i<grd.getBoundY();i++) {
			for(int j=0;j<grd.getBoundX();j++) {
				if(grd.getObj(j,i)==null){
					grd.addObj(new Ground(j,i));
				}
			}
		}
		loadBar.addProgress(0.5f);
		
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
		loadBar.addProgress(1);
		loadBar.addProgress(1);
		game();
		
	}
	
	@Override
	protected void Update() {

	}
	@Override
	protected void LateUpdate() {
		
	}
	
	@Override
	protected void doDrawing(Graphics g) {
		switch(curGameState) {
		case Loading:
			g.setColor(Color.gray);
			g.setFont(new Font("TimesRoman",Font.BOLD,100));
			g.drawString("LOADING", width/2+220, 300);
			loadBar.draw(g, width-250, height+10, 500, 20);
			break;
		case InGame:
			drawGrid(g);
			break;
		case WinGame:
			g.drawImage(winBackground, 0,0, width*2,height*2, this);
			g.setColor(Color.green);
			g.setFont(new Font("TimesRoman",Font.BOLD,100));
			g.drawString("Victory!!!", width/2+175, height);
			break;
		case PauseGame:
			g.setColor(Color.gray);
			g.setFont(new Font("TimesRoman",Font.BOLD,100));
			g.drawString("PAUSE", width/2+220, 300);
			break;
		}

	}
	public void drawGrid(Graphics g) {
		try {
			int top = plr.getPos().y + renderDist;
			int left = plr.getPos().x - renderDist;
			int bot = plr.getPos().y - renderDist;
			int right = plr.getPos().x + renderDist;
			
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == resumeBut) {
			pause();
		}
		if(e.getSource() == restartBut) {
        	dataHandler.setDataFile("Movblok/MapData/Input");
        	dataHandler.openFileRead();
        	grd = dataHandler.ReadGridData();
        	plr = dataHandler.ReadPlayerData();
    		grd.addObj(plr);
    		dataHandler.closeFile();
        	lastGameState = EGameStates.InGame;
    		pause();
		}
		if(e.getSource() == settingsBut) {
			
		}
		if(e.getSource() == menuBut) {
        	MovBlokApp.GetWindow().setCurrentState(EMovBlokScenes.Menu);
        	exitScene();
		}
	}
	
	private void setLastState() {
		lastGameState = curGameState;
	}
	
	private void load() {
		curGameState = EGameStates.Loading;
	}
	private void win() {
		setLastState();
		curGameState = EGameStates.WinGame;
	}
	private void pause() {
		if(curGameState==EGameStates.PauseGame) {
			curGameState = lastGameState;
			this.remove(resumeBut);
			this.remove(restartBut);
			this.remove(settingsBut);
			this.remove(menuBut);
		}
		else {
			setLastState();
			curGameState = EGameStates.PauseGame;
			this.add(resumeBut);
			this.add(restartBut);
			this.add(settingsBut);
			this.add(menuBut);
		}
	}
	private void game() {
		setLastState();
		curGameState = EGameStates.InGame;
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
	        
	        if (key == KeyEvent.VK_R) {

	    		return;
	        }
	        
	        if ((key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) && plr.getPos().x>0) {
	        	GameObject temp = grd.getObj(plr.getPos().x-1,plr.getPos().y);
	        	if( temp == null) return;
				if( temp instanceof Wall) {
					return;
				}
				if( temp instanceof Ground) {
					grd.switchObj(new Vector2(plr.getPos().x-1,plr.getPos().y), plr.getPos());
					plr.getPos().x--;
					return;
				}

				if( temp instanceof Box) {
					GameObject nextObj = grd.getObj(plr.getPos().x-2,plr.getPos().y);
					if(nextObj==null) return;
					if(nextObj instanceof Ground) {
						grd.switchObj(new Vector2(plr.getPos().x-2,plr.getPos().y), new Vector2(plr.getPos().x-1,plr.getPos().y));
						grd.switchObj(new Vector2(plr.getPos().x-1,plr.getPos().y), plr.getPos());
						plr.getPos().x--;
					}
					return;
				}
				if( temp instanceof Portal) {
					win();
					return;
				}
	        	return;
	        }

	        if ((key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) && plr.getPos().x<grd.getBoundX()-1) {
	        	GameObject temp = grd.getObj(plr.getPos().x+1,plr.getPos().y);
	        	if( temp == null) return;
				if( temp instanceof Wall) {
					return;
				}
				if( temp instanceof Ground) {
					grd.switchObj(new Vector2(plr.getPos().x+1,plr.getPos().y), plr.getPos());
					plr.getPos().x++;
					return;
				}

				if( temp instanceof Box) {
					GameObject nextObj = grd.getObj(plr.getPos().x+2,plr.getPos().y);
					if(nextObj==null) return;
					if(nextObj instanceof Ground) {
						grd.switchObj(new Vector2(plr.getPos().x+2,plr.getPos().y), new Vector2(plr.getPos().x+1,plr.getPos().y));
						grd.switchObj(new Vector2(plr.getPos().x+1,plr.getPos().y), plr.getPos());
						plr.getPos().x++;
					}
					return;
				}
				if( temp instanceof Portal) {
					win();
					return;
				}
	        	return;
	        }

	        if ((key == KeyEvent.VK_W || key == KeyEvent.VK_UP)&& plr.getPos().y>0) {
	        	GameObject temp = grd.getObj(plr.getPos().x,plr.getPos().y-1);
	        	if( temp == null) return;
				if( temp instanceof Wall) {
					return;
				}
				if( temp instanceof Ground) {
					grd.switchObj(new Vector2(plr.getPos().x,plr.getPos().y-1), plr.getPos());
					plr.getPos().y--;
					return;
				}

				if( temp instanceof Box) {
					GameObject nextObj = grd.getObj(plr.getPos().x,plr.getPos().y-2);
					if(nextObj==null) return;
					if(nextObj instanceof Ground) {
						grd.switchObj(new Vector2(plr.getPos().x,plr.getPos().y-2), new Vector2(plr.getPos().x,plr.getPos().y-1));
						grd.switchObj(new Vector2(plr.getPos().x,plr.getPos().y-1), plr.getPos());
						plr.getPos().y--;
					}
					return;
				}
				if( temp instanceof Portal) {
					win();
					return;
				}
				return;
	        }

	        if ((key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) && plr.getPos().y<grd.getBoundY()-1) {
	        	GameObject temp = grd.getObj(plr.getPos().x,plr.getPos().y+1);
	        	if( temp == null) return;
				if( temp instanceof Wall) {
					return;
				}
				if( temp instanceof Ground) {
					grd.switchObj(new Vector2(plr.getPos().x,plr.getPos().y+1), plr.getPos());
					plr.getPos().y++;
					return;
				}

				if( temp instanceof Box) {
					GameObject nextObj = grd.getObj(plr.getPos().x,plr.getPos().y+2);
					if(nextObj==null) return;
					if(nextObj instanceof Ground) {
						grd.switchObj(new Vector2(plr.getPos().x,plr.getPos().y+2), new Vector2(plr.getPos().x,plr.getPos().y+1));
						grd.switchObj(new Vector2(plr.getPos().x,plr.getPos().y+1), plr.getPos());
						plr.getPos().y++;
					}
					return;
				}
				if( temp instanceof Portal) {
					win();
					return;
				}
	        	return;
	        }
	    }
	}
	
}
