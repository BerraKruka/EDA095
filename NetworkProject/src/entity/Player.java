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
	
	public Player(Pos pos, int width, int height, GameEntity[][] positions){
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
	
	public void move(int direction, float delta) {
		// set the direction of player after movement
		playerSprite = getDirection(direction);
		float xNew = pos.x;
		float yNew = pos.y;
		switch(direction){
			case(Player.DOWN):
				yNew = pos.moveY(delta);
				break;
			case(Player.UP):
				yNew = pos.moveY(-delta);
				break;
			case(Player.LEFT):
				xNew = pos.moveX(-delta);
				break;
			case(Player.RIGHT):
				xNew = pos.moveX(delta);
				break;
		}
		if (!isBlocked(direction,xNew, yNew)) {
			long timeUpdate = (long) (delta*5);
			playerSprite.update(timeUpdate);
			pos.updatePos(xNew, yNew);
			this.setPos(pos);
		}else{
			pos.updatePos(pos.x - delta, pos.y - delta);
			this.setPos(pos);
		}
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
