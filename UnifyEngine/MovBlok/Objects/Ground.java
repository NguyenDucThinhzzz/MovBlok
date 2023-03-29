package MovBlok.Objects;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

import UnifyEngine.GameObject;

public class Ground extends GameObject{
	public Ground(int _x, int _y) {
		super(_x,_y, 2, "MovBlok/resources/dirt_low_res.png");
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
