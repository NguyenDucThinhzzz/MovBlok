package UnifyEngine;

public class Vector2 {
	public float x,y;
	
	public Vector2() {
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2(float _x, float _y) {
		this.x = _x;
		this.y = _y;
	}
	public Vector2(Vector2 _cpy) {
		this.x = _cpy.x;
		this.y = _cpy.y;
	}
	
	@Override
	public String toString() {
		return "( "+ x + ", " + y + " )";
	}
}
