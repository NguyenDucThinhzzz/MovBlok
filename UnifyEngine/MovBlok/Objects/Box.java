package MovBlok.Objects;

import UnifyEngine.GameObject;
import UnifyEngine.ObjectType;

public class Box extends GameObject {
	public Box(int _x, int _y) {
		super(_x,_y,"MovBlok/resources/box_low_res.png", ObjectType.box);
	}
}
