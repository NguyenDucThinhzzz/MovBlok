package MovBlok.Objects;

import UnifyEngine.GameObject;

public class Portal extends GameObject{
	
	public Portal() {
		super(0,0,4,"MovBlok/resources/portal_low_res.png");
	}
	public Portal(int _x, int _y) {
		super(_x,_y,4,"MovBlok/resources/portal_low_res.png");
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
