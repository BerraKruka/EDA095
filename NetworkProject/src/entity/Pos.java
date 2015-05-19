package entity;

public class Pos {
	public float x, y;
	public final static float POS_REL = 34f;

	public Pos(float x, float y) {
		this.x = x * POS_REL;
		this.y = y * POS_REL;
	}

	public void move(float deltaX, float deltaY) {
		x += deltaX;
		y += deltaY;
	}

	public void updatePos(float xNew, float yNew) {
		x = xNew;
		y = yNew;
	}

	public Pos getFuturePos(float deltaX, float deltaY) {
		return new Pos(x + deltaX, y + deltaY);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
}
