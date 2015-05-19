package entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tests.xml.Entity;

public class Bomb extends Entity {
	private float x, y;
	private float width, height;

	private Image bombImg, bombExplode;

	private long timeToLive, dropTime, explodeTime, displayTime;
	private boolean isExp;

	public Bomb(float x, float y, float width, float height, long timeToLive) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		this.timeToLive = timeToLive;
		try {
			bombImg = new Image("data/bomb.png");
			bombExplode = new Image("data/fire.png");

		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dropTime = System.currentTimeMillis();
		displayTime = 4500;
	}

	public void draw() {
		explodeTime = System.currentTimeMillis() - dropTime;
		if (explodeTime > timeToLive) {
			bombExplode.draw(x - 136, y - 136);
			isExp = true;
		} else {
			bombImg.draw(x, y);
			isExp = false;
		}

		try {
			if (explodeTime > displayTime) {
				bombExplode = new Image("data/finished.png");
			}
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Pos getPos() {
		Pos pos = new Pos(getX(), getY());
		return pos;
	}

	public int getX() {
		return (int) x / 34;
	}

	public int getY() {
		return (int) y / 34;
	}

	public boolean isExploaded() {
		return isExp;
	}
}
