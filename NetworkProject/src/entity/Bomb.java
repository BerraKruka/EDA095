package entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tests.xml.Entity;

public class Bomb extends Entity{
	private float xPos, yPos;
	private float range;
	private long timeToLive;
	private Animation bombSprite, bombLive, bombExploded;
	
	public Bomb(int xPos, int yPos, int range, long timeToLive){
		this.xPos = xPos;
		this.yPos = yPos;
		this.range = range;
		this.timeToLive = timeToLive;
	}
	private void initAnimation() throws SlickException {
		Image[] live = { new Image("data/back.png"),new Image("data/back1.png") };
		Image[] exploded = { new Image("data/front.png"),new Image("data/front1.png") };
		
		int[] duration = { 300, 300 };
		/*
		 * false variable means do not auto update the animation. By setting it
		 * to false animation will update only when the user presses a key.
		 */
		bombLive = new Animation(live, duration, true);
		bombExploded = new Animation(exploded, duration, true);

		// this will depend on the player later on.
		bombSprite = bombLive;
	}
	
	
}
