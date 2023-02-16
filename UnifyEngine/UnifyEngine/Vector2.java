package UnifyEngine;

public class Vector2 {
	public int x,y;
	
	public Vector2() {
		this.x=0;
		this.y=0;
	}
	
	public Vector2(int _x, int _y) {
		this.x = _x;
		this.y = _y;
	}
	
	@Override
	public String toString() {
		return "( "+ x + ", " + y + " )";
	}
}
