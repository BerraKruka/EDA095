package entity;

public class Pos {
	public float x, y;
	public final static float POS_REL = 34f;

	public Pos(float x, float y){
		this.x = x * POS_REL;
		this.y = y * POS_REL;
	}
	public float moveX(float deltaX){
		return this.x+deltaX;
	}
	public float moveY(float deltaY){
		return this.y+deltaY;
	}
	public void updatePos(float xNew,float yNew){
		x = xNew ;
		y = yNew ;
	}
}
