package MovBlok.Objects;


import UnifyEngine.GameObject;

public class Wall extends GameObject{
	public Wall() {
		super(0,0,3,"MovBlok/resources/wall_low_res.png");
	}
	public Wall(int _x, int _y) {
		super(_x,_y,3,"MovBlok/resources/wall_low_res.png");
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
