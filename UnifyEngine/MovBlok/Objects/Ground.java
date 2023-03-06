package MovBlok.Objects;

import UnifyEngine.GameObject;
import UnifyEngine.ObjectType;

public class Ground extends GameObject{
	public Ground(int _x, int _y) {
		super(_x,_y,"MovBlok/resources/dirt_low_res.png",ObjectType.ground);
	}
}
