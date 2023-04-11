package MovBlok.Scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
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
import MovBlok.Scripts.GameStates;
import MovBlok.Scripts.MovBlokApp;
import MovBlok.Scripts.MovBlokScenes;

public class MapCreateScene extends Scene {
	private int width;
	private int height;
	private Grid grd;
	private Player plr;
	private GameStates curGameState = GameStates.InGame;
	private BufferedImage winBackground;
	private DataHandler dataHandler;
	
	private int renderDist;
	private int boxSize;
	
	public MapCreateScene() {
		super();
	}
	
	@Override
	protected void Awake() {
		dataHandler = new DataHandler();
		dataHandler.setDataFile("Movblok/MapData/Input");
	}

	@Override
	protected void Start() {
		width = MovBlokApp.GetWindow().getWidth()/2;
		height = MovBlokApp.GetWindow().getHeight()/2;
		//Read map data
		dataHandler.openFileRead();
		grd = dataHandler.ReadGridData();
		plr = dataHandler.ReadPlayerData();
		dataHandler.closeFile();
		grd.addObj(plr);

		//Aspect ratio change in the future for now it's 1280x720 (16:9)
		boxSize=80;
		renderDist = width/boxSize+1;
		try {
			winBackground = ImageIO.read(new File("MovBlok/resources/vanheo.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MovBlokApp.GetWindow().addKeyListener(new ControlAdapter());
		this.revalidate();
		for(int i=0;i<grd.getBoundY();i++) {
			for(int j=0;j<grd.getBoundX();j++) {
				if(grd.getObj(j,i)==null){
					grd.addObj(new Ground(j,i));
				}
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
		dataHandler.setDataFile("MovBlok/MapData/Test");
		dataHandler.openFileWrite();
		dataHandler.WriteFileData(grd,plr);
		dataHandler.closeFile();
	}
	
	@Override
	protected void Update() {
		
	}

	@Override
	protected void LateUpdate() {
		
	}
	
	@Override
	protected void doDrawing(Graphics g) {
		g.drawImage((new ImageIcon("UnifyEngine/resources/UNiFY-Engine.png")).getImage(),0,0,this);
		switch(curGameState) {
		case InGame:
			
			drawGrid(g);
			break;
		case WinGame:
			g.drawImage(winBackground, 0,0, width*2,height*2, this);
			g.setColor(Color.green);
			g.setFont(new Font("TimesRoman",Font.BOLD,100));
			g.drawString("Victory!!!", width/2+100, height);
			break;
		case PauseGame:
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
	
	private void win() {
		curGameState = GameStates.WinGame;
	}
	
	public class ControlAdapter extends KeyAdapter {
		
	    @Override
	    public void keyPressed(KeyEvent e) {

	        int key = e.getKeyCode();
	        

	        if (key == KeyEvent.VK_ESCAPE) {
	        	Debug.Log("\t\tPause Menu");
	        	
	        	MovBlokApp.GetWindow().SetCurrentState(MovBlokScenes.Quit);
	        	exitScene();
	        	
	        	return;
	        }
	        
	        if(curGameState!=GameStates.InGame) return; 
	        
	        if (key == KeyEvent.VK_R) {
	        	dataHandler.setDataFile("Movblok/MapData/Input");
	        	grd = dataHandler.ReadGridData();
	        	plr = dataHandler.ReadPlayerData();
	    		grd.addObj(plr);
	    		return;
	        }
	        
	        if ((key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) && plr.getPos().x>0) {
	        	GameObject temp = grd.getObj(plr.getPos().x-1,plr.getPos().y);
	        	if( temp == null) return;
				plr.getPos().x--;
				
	        	return;
	        }

	        if ((key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) && plr.getPos().x<grd.getBoundX()-1) {
	        	GameObject temp = grd.getObj(plr.getPos().x+1,plr.getPos().y);
	        	if( temp == null) return;
				plr.getPos().x++;
				
	        	return;
	        }

	        if ((key == KeyEvent.VK_W || key == KeyEvent.VK_UP)&& plr.getPos().y>0) {
	        	GameObject temp = grd.getObj(plr.getPos().x,plr.getPos().y-1);
	        	if( temp == null) return;
				plr.getPos().y--;
				
				return;
	        }

	        if ((key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) && plr.getPos().y<grd.getBoundY()-1) {
	        	GameObject temp = grd.getObj(plr.getPos().x,plr.getPos().y+1);
	        	if( temp == null) return;
				plr.getPos().y++;
				
	        	return;
	        }
	    }
	}
	
}
