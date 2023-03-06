package MovBlok.Objects;

import UnifyEngine.GameObject;
import UnifyEngine.ObjectType;

public class Wall extends GameObject{
	public Wall(int _x, int _y) {
		super(_x,_y,"MovBlok/resources/wall_low_res.png", ObjectType.box);
	}
}
