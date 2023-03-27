package MovBlok.Scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import UnifyEngine.Scene;
import UnifyEngine.Debug;
import UnifyEngine.GameObject;
import UnifyEngine.Vector2;
import MovBlok.Objects.*;
import MovBlok.Objects.Box;
import MovBlok.Scripts.ApplicationStates;
import MovBlok.Scripts.GameStates;
import MovBlok.Scripts.Grid;
import MovBlok.Scripts.MovBlokApp;
import MovBlok.Scripts.SaveManager.DataHandler;

public class GameScene extends Scene {
	public static Player plr;
	protected int width = MovBlokApp.GetWindow().getWidth()/2;
	protected int height = MovBlokApp.GetWindow().getHeight()/2;
	private Grid grd;
	private GameStates curGameState = GameStates.InGame;
	
	public GameScene() {
		super();
		grd = new Grid(8, 8, width, height, (new ImageIcon("MovBlok/resources/test_box.png")).getImage());
		plr = new Player(grd.ReadFileData("Movblok/MapData/Input"));
		grd.addObj(plr);
	}

	@Override
	protected void Start() {
		MovBlokApp.GetWindow().addKeyListener(new ControlAdapter());
		this.revalidate();
		for(int i=0;i<grd.GetGridBoundY();i++) {
			for(int j=0;j<grd.GetGridBoundX();j++) {
				if(grd.getObj(j,i)==null){
					grd.addObj(new Ground(j,i));
				}
			}
		}
//		//Testing
//		grd.addObj(new Box(3,1));
//		for(int i=0;i<grd.GetGridBoundX();i++) {
//			grd.addObj(new Wall(i,0));
//			grd.addObj(new Wall(i,grd.GetGridBoundY()-1));
//		}
//		for(int i=0;i<grd.GetGridBoundY();i++) {
//			grd.addObj(new Wall(0,i));
//			grd.addObj(new Wall(grd.GetGridBoundX()-1,i));
//		}
//		for(int i=1;i<7;i++) {
//			if(i==5) continue;
//			grd.addObj(new Wall(i,2));
//		}
//		grd.addObj(new Portal(6,6));
		grd.WriteFileData("MovBlok/MapData/Test");
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
			grd.drawGrid(g, this, plr.position);
			break;
		case WinGame:
			g.setColor(Color.green);
			g.drawString("YOU WIN!!!", width, height);
			break;
		case PauseGame:
			break;
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
	        	
	        	MovBlokApp.GetWindow().SetCurrentState(ApplicationStates.Quit);
	        	exitScene();
	        	
	        	return;
	        }
	        
	        if(curGameState!=GameStates.InGame) return; 
	        
	        if ((key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) && plr.position.x>0) {
	        	Debug.Log("L");
	        	GameObject temp = grd.getObj(plr.position.x-1,plr.position.y);
	        	if( temp == null) return;
				if( temp instanceof Wall) {
					return;
				}
				if( temp instanceof Ground) {
					grd.switchObj(new Vector2(plr.position.x-1,plr.position.y), plr.position);
					plr.position.x--;
					return;
				}

				if( temp instanceof Box) {
					GameObject nextObj = grd.getObj(plr.position.x-2,plr.position.y);
					if(nextObj==null) return;
					if(nextObj instanceof Ground) {
						grd.switchObj(new Vector2(plr.position.x-2,plr.position.y), new Vector2(plr.position.x-1,plr.position.y));
						grd.switchObj(new Vector2(plr.position.x-1,plr.position.y), plr.position);
						plr.position.x--;
					}
					return;
				}
				if( temp instanceof Portal) {
					win();
					return;
				}
	        	return;
	        }

	        if ((key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) && plr.position.x<grd.GetGridBoundX()-1) {
	        	Debug.Log("R");
	        	GameObject temp = grd.getObj(plr.position.x+1,plr.position.y);
	        	if( temp == null) return;
				if( temp instanceof Wall) {
					return;
				}
				if( temp instanceof Ground) {
					grd.switchObj(new Vector2(plr.position.x+1,plr.position.y), plr.position);
					plr.position.x++;
					return;
				}

				if( temp instanceof Box) {
					GameObject nextObj = grd.getObj(plr.position.x+2,plr.position.y);
					if(nextObj==null) return;
					if(nextObj instanceof Ground) {
						grd.switchObj(new Vector2(plr.position.x+2,plr.position.y), new Vector2(plr.position.x+1,plr.position.y));
						grd.switchObj(new Vector2(plr.position.x+1,plr.position.y), plr.position);
						plr.position.x++;
					}
					return;
				}
				if( temp instanceof Portal) {
					win();
					return;
				}
	        	return;
	        }

	        if ((key == KeyEvent.VK_W || key == KeyEvent.VK_UP)&& plr.position.y>0) {
	        	Debug.Log("U");
	        	GameObject temp = grd.getObj(plr.position.x,plr.position.y-1);
	        	if( temp == null) return;
				if( temp instanceof Wall) {
					return;
				}
				if( temp instanceof Ground) {
					grd.switchObj(new Vector2(plr.position.x,plr.position.y-1), plr.position);
					plr.position.y--;
					return;
				}

				if( temp instanceof Box) {
					GameObject nextObj = grd.getObj(plr.position.x,plr.position.y-2);
					if(nextObj==null) return;
					if(nextObj instanceof Ground) {
						grd.switchObj(new Vector2(plr.position.x,plr.position.y-2), new Vector2(plr.position.x,plr.position.y-1));
						grd.switchObj(new Vector2(plr.position.x,plr.position.y-1), plr.position);
						plr.position.y--;
					}
					return;
				}
				if( temp instanceof Portal) {
					win();
					return;
				}
				return;
	        }

	        if ((key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) && plr.position.y<grd.GetGridBoundY()-1) {
	        	Debug.Log("D");
	        	GameObject temp = grd.getObj(plr.position.x,plr.position.y+1);
	        	if( temp == null) return;
				if( temp instanceof Wall) {
					return;
				}
				if( temp instanceof Ground) {
					grd.switchObj(new Vector2(plr.position.x,plr.position.y+1), plr.position);
					plr.position.y++;
					return;
				}

				if( temp instanceof Box) {
					GameObject nextObj = grd.getObj(plr.position.x,plr.position.y+2);
					if(nextObj==null) return;
					if(nextObj instanceof Ground) {
						grd.switchObj(new Vector2(plr.position.x,plr.position.y+2), new Vector2(plr.position.x,plr.position.y+1));
						grd.switchObj(new Vector2(plr.position.x,plr.position.y+1), plr.position);
						plr.position.y++;
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
