package entity;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.tests.xml.Entity;

public class Player extends GameEntity{
	public static final int UP 		= 0, 
							DOWN 	= 1, 
							LEFT 	= 2, 
							RIGHT	= 3;
	private Animation playerSprite;
	private Animation up, down, left, right;
	private Pos pos;
	GameEntity[][] positions;
	
	public Player(Pos pos, float width, float height, GameEntity[][] positions){
		super(pos,width,height);
		this.pos = pos;
		this.positions = positions;
	}
	
	public void initAnimation() throws SlickException{
		Image[] movementUp 		= { new Image("data/back.png"),new Image("data/back1.png") };
		Image[] movementDown 	= { new Image("data/front.png"),new Image("data/front1.png") };
		Image[] movementLeft 	= { new Image("data/left.png"),new Image("data/left1.png") };
		Image[] movementRight 	= { new Image("data/right.png"),new Image("data/right1.png") };

		int[] duration = { 100, 100 };

		/*
		 * false variable means do not auto update the animation. By setting it
		 * to false animation will update only when the user presses a key.
		 */
		up 		= new Animation(movementUp, duration, false);
		down 	= new Animation(movementDown, duration, false);
		left	= new Animation(movementLeft, duration, false);
		right 	= new Animation(movementRight, duration, false);

		// this will depend on the player later on.
		playerSprite = right;
		
	}
	public void draw(){
		playerSprite.draw(pos.x, pos.y);
	}
	
	private Animation getDirection(int direction){
		switch(direction){
			case Player.DOWN: 
				return down;
			case Player.UP:
				return up;
			case Player.LEFT:
				return left;
			case Player.RIGHT:
				return right;
			default:
				return right;
		}
	}
	
private void moveInDirection(int direction, float delta){
		switch(direction){
			case(Player.DOWN):
				pos.move(0,delta);
				break;
			case(Player.UP):
				pos.move(0,-delta);
				break;
			case(Player.LEFT):
				pos.move(-delta,0);
				break;
			case(Player.RIGHT):
				pos.move(delta,0);
				break;
		}
	}
	public void move(int direction, float delta) {
		// set the direction of player after movement
		playerSprite = getDirection(direction);
		moveInDirection(direction,delta);
		if (isBlocked(direction,pos.x, pos.y)) {
			moveInDirection(direction,-delta-1f);
		}else{
			long timeUpdate = (long) (delta*5);
			playerSprite.update(timeUpdate);
		}
		this.setPos(pos);
	}
	private boolean isBlocked(int direction,float x, float y){
		int relX = (int) x/34;
		int relY = (int) y/34;
		boolean option ;
		System.out.println(relX+" - "+relY );
		switch(direction){
			case(Player.RIGHT):
					option =  this.intersects(positions[relX+1][relY])
						    || this.intersects(positions[relX+1][relY+1]);
					return option;
			case(Player.LEFT):
					return this.intersects(positions[relX][relY])
						 ||this.intersects(positions[relX][relY+1]);
			case(Player.DOWN):
					return this.intersects(positions[relX][relY+1])
						|| this.intersects(positions[relX+1][relY+1]);
			case(Player.UP):
					return this.intersects(positions[relX][relY])
							||this.intersects(positions[relX+1][relY]);
		}
		
		return false;
	}
	
}
