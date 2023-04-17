package MovBlok.Objects;

import UnifyEngine.GameObject;

public class Box extends GameObject {
	public Box() {
		super(0,0, 1,"MovBlok/resources/box_low_res.png");
	}
	public Box(int _x, int _y) {
		super(_x,_y, 1,"MovBlok/resources/box_low_res.png");
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
