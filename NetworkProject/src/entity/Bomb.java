package entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Bomb extends GameEntity{
	private float xPos, yPos;
	private float range;
	private long timeToLive;
	private Animation bombSprite, bombLive, bombExploded;
	
	private static Pos pos;
	private float width, height;
	
	public Bomb(Pos pos, float width, float height, long timeToLive){
		super(pos, width, height);
		
		
		this.pos = pos;
		this.width = width;
		this.height = height;
		
		
		this.timeToLive = timeToLive;
	}
	public void initAnimation() throws SlickException {
		Image[] live = { new Image("data/bomb1.png"),new Image("data/bomb.png") };
		Image[] exploded = { new Image("data/explode.png"),new Image("data/explode.png") };
		
		int[] duration = { 300, 300 };
		/*
		 * false variable means do not auto update the animation. By setting it
		 * to false animation will update only when the user presses a key.
		 */
		bombLive = new Animation(live, duration, true);
		bombExploded = new Animation(exploded, duration, true);
		
		bombSprite = bombLive;
		

		
			//bombSprite = bombExploded;
	
	
	}
	public void draw() {
		
		bombSprite = bombLive;
		bombSprite.draw(10, 10);
	}
	


}
