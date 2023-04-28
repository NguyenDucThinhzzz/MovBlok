package UnifyEngine;

public class Grid {
	private int bound_x=0;
	private int bound_y=0;
	private GameObject[][] arr;
	
	public Grid(int _x,int _y) {
		this.bound_x = _x;
		this.bound_y = _y;
		arr = new GameObject[_y][_x];
	}
	
	public Grid(int _x, int _y, GameObject[][] _arr) {
		this.bound_x = _x;
		this.bound_y = _y;
		arr = new GameObject[_y][_x];
		this.arr = _arr;
	}
	
	
	//GridFuctions
	public void addObj(GameObject _obj) {
		if(_obj == null) return;
		if(_obj.getPos().x<0 || _obj.getPos().y<0 || _obj.getPos().x>=bound_x || _obj.getPos().y>=bound_y) return;
		 arr[(int)_obj.getPos().y][(int)_obj.getPos().x] = _obj;
	}
	public void setNull(int _x, int _y) {
		arr[_y][_x] = null;
	}
	
	public void switchObj(Vector2 _pos1,Vector2 _pos2) {
		if(_pos1.x<0 || _pos1.y<0 || _pos1.x>=bound_x || _pos1.y>=bound_y || _pos2.x<0 || _pos2.y<0 || _pos2.x>=bound_x || _pos2.y>=bound_y) return;
		GameObject _temp = arr[(int)_pos1.y][(int) _pos1.x];
		arr[(int) _pos1.y][(int) _pos1.x] = arr[(int)_pos2.y][(int) _pos2.x];
		arr[(int)_pos2.y][(int) _pos2.x] = _temp;
	}
	
	public GameObject getObj(Vector2 _pos) { 
		if(_pos.x<0 || _pos.y<0 || _pos.x>=bound_x || _pos.y>=bound_y) return null;
		return arr[(int)_pos.y][(int)_pos.x];
	}
	public GameObject getObj(int _x, int _y) {
		if(_x<0 || _y<0 || _x>=bound_x || _y>=bound_y) return null;
		return arr[_y][_x];
	}
	public GameObject[][] getObjArr(){
		return arr;
	}
	public int getBoundX() {
		return bound_x;
	}
	public int getBoundY() {
		return bound_y;
	}
	
}
