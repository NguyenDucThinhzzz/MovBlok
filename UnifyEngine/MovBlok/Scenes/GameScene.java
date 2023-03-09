package MovBlok.Scenes;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

import UnifyEngine.Scene;
import UnifyEngine.Debug;
import UnifyEngine.GameObject;
import UnifyEngine.Grid;
import UnifyEngine.Vector2;
import MovBlok.Objects.*;
import MovBlok.Objects.Box;
import MovBlok.Scripts.GameStates;
import MovBlok.Scripts.MovBlokApp;

public class GameScene extends Scene {
	
	private Grid grd;
	private Player plr;
	
	public GameScene() {
		super();
		grd = new Grid(20, 50, (new ImageIcon("MovBlok/resources/test_box.png")).getImage());
		plr = new Player(5,5);
		grd.addObj(plr, plr.position);
	}

	@Override
	protected void Start() {
		MovBlokApp.GetWindow().addKeyListener(new ControlAdapter());
		this.revalidate();
		for(int i=0;i<grd.GetGridBoundY();i++) {
			for(int j=0;j<grd.GetGridBoundX();j++) {
				if(grd.getObj(j,i)==null){
					grd.addObj(new Ground(j,i),j,i);
				}
			}
		}
		//Testing
		grd.addObj(new Box(5,6),5,6);
		grd.addObj(new Box(6,6),6,6);
		grd.addObj(new Box(8,6),8,6);
		grd.addObj(new Wall(7,5),7,5);
	}

	@Override
	protected void Update() {
		plr.update();
	}

	@Override
	protected void LateUpdate() {
		
	}
	
	@Override
	protected void doDrawing(Graphics g) {
		g.drawImage((new ImageIcon("UnifyEngine/resources/UNiFY-Engine.png")).getImage(),0,0,this);
		grd.drawGrid(g, this, plr.position);

	}
	
	public class ControlAdapter extends KeyAdapter {
		
	    @Override
	    public void keyPressed(KeyEvent e) {

	        int key = e.getKeyCode();

	        if (key == KeyEvent.VK_ESCAPE) {
	        	Debug.Log("\t\tPause Menu");
	        	
	        	MovBlokApp.GetWindow().SetCurrentState(GameStates.Quit);
	        	exitScene();
	        	
	        	return;
	        }
	        
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
	        	return;
	        }
	    }
	}
	
}
