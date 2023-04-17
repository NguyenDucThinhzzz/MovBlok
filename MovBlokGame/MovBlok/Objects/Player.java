package MovBlok.Objects;

import UnifyEngine.GameObject;
import UnifyEngine.Vector2;

public class Player extends GameObject{
	
	public Player() {
		super(0,0, 0, "MovBlok/resources/player_low_res.png");
	}
	public Player(Player _plr) {
		super(_plr.getPos().x,_plr.getPos().y, _plr.getID(), "MovBlok/resources/player_low_res.png");
	}
	
	public Player(int _x, int _y) {
		super(_x,_y, 0, "MovBlok/resources/player_low_res.png");
	}
	public Player(Vector2 _vec) {
		super(_vec.x,_vec.y, 0, "MovBlok/resources/player_low_res.png");
	}

	@Override
	public void update() {
	}
}