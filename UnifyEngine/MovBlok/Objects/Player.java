package MovBlok.Objects;

import UnifyEngine.GameObject;

public class Player extends GameObject implements Runnable{
	
	Thread thread=null;
	
	public Player(int _x, int _y) {
		super(_x,_y,"MovBlok/resources/player_low_res.png");
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
}
