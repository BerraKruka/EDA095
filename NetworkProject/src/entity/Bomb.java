package entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tests.xml.Entity;

public class Bomb extends Entity {
	private float x,y;
	private float width, height;

	private Image bombImg, bombExplode;

	private long timeToLive, dropTime, explodeTime;

	public final static int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;

	public Bomb(float x, float y, float width, float height, long timeToLive) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		this.timeToLive = timeToLive;
		try {
			bombImg = new Image("data/bomb1.png");
			bombExplode = new Image("data/wood.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dropTime = System.currentTimeMillis();
	}

	public void draw() {
		explodeTime = System.currentTimeMillis() - dropTime;
		if(explodeTime > timeToLive) {
			bombExplode.draw(x,y);
		}else {
		bombImg.draw(x,y);
		}

	}
}
