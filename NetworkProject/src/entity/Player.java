package entity;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.tests.xml.Entity;

public class Player extends GameEntity {
	public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	private Animation playerSprite;
	private Animation up, down, left, right;
	private Pos pos;
	GameEntity[][] positions;
	private int playerNumber;
	private Image[] movementUp;
	private Image[] movementDown;
	private Image[] movementLeft;
	private Image[] movementRight;
	private int relX,relY;
	private boolean dead;
	
	public Player(Pos pos, float width, float height, GameEntity[][] positions,
			int playerNumber) {
		super(pos, width, height);
		this.pos = pos;
		this.positions = positions;
		this.playerNumber = playerNumber;
		dead = false;
	}

	public void initAnimation() throws SlickException {
		int[] duration = {};
		switch (playerNumber) {
		case 0:
			movementUp = new Image[] { new Image("data/fisher/fisher_up_1.png"),new Image("data/fisher/fisher_up_2.png"),
					new Image("data/fisher/fisher_up_3.png")};
			movementDown 	= new Image[]{ new Image("data/fisher/fisher_down_1.png"),new Image("data/fisher/fisher_down_2.png"),
					new Image("data/fisher/fisher_down_3.png")};
			movementLeft 	= new Image[] { new Image("data/fisher/fisher_left_1.png"),new Image("data/fisher/fisher_left_2.png"),
					new Image("data/fisher/fisher_left_3.png")};
			movementRight 	= new Image[]{ new Image("data/fisher/fisher_right_1.png"),new Image("data/fisher/fisher_right_2.png"),
					new Image("data/fisher/fisher_right_3.png")};
			duration = new int[]{100,100,100};
			break;
		case 1:
			movementUp = new Image[] { new Image("data/back.png"),
					new Image("data/back1.png") };
			movementDown = new Image[] { new Image("data/front.png"),
					new Image("data/front1.png") };
			movementLeft = new Image[] { new Image("data/left.png"),
					new Image("data/left1.png") };
			movementRight = new Image[] { new Image("data/right.png"),
					new Image("data/right1.png") };
			duration = new int[]{100,100};
			break;
		case 2:
			movementUp = new Image[] { new Image("data/back.png"),
					new Image("data/back1.png") };
			movementDown = new Image[] { new Image("data/front.png"),
					new Image("data/front1.png") };
			movementLeft = new Image[] { new Image("data/left.png"),
					new Image("data/left1.png") };
			movementRight = new Image[] { new Image("data/right.png"),
					new Image("data/right1.png") };
			duration = new int[]{100,100};
			break;
		case 3:
			movementUp = new Image[] { new Image("data/back.png"),
					new Image("data/back1.png") };
			movementDown = new Image[] { new Image("data/front.png"),
					new Image("data/front1.png") };
			movementLeft = new Image[] { new Image("data/left.png"),
					new Image("data/left1.png") };
			movementRight = new Image[] { new Image("data/right.png"),
					new Image("data/right1.png") };
			duration = new int[]{100,100};
			break;
		}


		/*
		 * false variable means do not auto update the animation. By setting it
		 * to false animation will update only when the user presses a key.
		 */
		up = new Animation(movementUp, duration, false);
		down = new Animation(movementDown, duration, false);
		left = new Animation(movementLeft, duration, false);
		right = new Animation(movementRight, duration, false);

		// this will depend on the player later on.
		playerSprite = right;

	}

	public void draw() {
		playerSprite.draw(pos.x, pos.y);
	}

	public boolean isDead() {
		return dead;
	}
	
	public void setDead() {
		dead = true;
	}
	private Animation getDirection(int direction) {
		switch (direction) {
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

	private void moveInDirection(int direction, float delta) {
		switch (direction) {
		case (Player.DOWN):
			pos.move(0, delta);
			break;
		case (Player.UP):
			pos.move(0, -delta);
			break;
		case (Player.LEFT):
			pos.move(-delta, 0);
			break;
		case (Player.RIGHT):
			pos.move(delta, 0);
			break;
		}
	}

	public void move(int direction, float delta, boolean[][] blocked) {
		// set the direction of player after movement
		playerSprite = getDirection(direction);
		moveInDirection(direction, delta);
		this.setPos(pos);
		if (isBlocked(direction, pos.x, pos.y, blocked)) {
			moveInDirection(direction, -delta);
		} else {
			long timeUpdate = (long) (delta * 5);
			playerSprite.update(timeUpdate);
		}
		this.setPos(pos);
	}

	private boolean isBlocked(int direction, float x, float y, boolean[][]  blocked) {
		relX = (int) x / 34;
		relY = (int) y / 34;
		switch (direction) {
		case (Player.RIGHT):
			return blocked[relX + 1][relY] || blocked[relX + 1][relY + 1];
		case (Player.LEFT):
			return blocked[relX][relY] || blocked[relX][relY + 1];
		case (Player.DOWN):
			return blocked[relX][relY + 1] || blocked[relX + 1][relY + 1];
		case (Player.UP):
			return blocked[relX][relY] || blocked[relX + 1][relY];
		}

		return false;
	}

	public Pos getPos() {
		Pos pos = new Pos(getX(),getY());
		return pos;
	}
	
	public int getX() {
		return (int) pos.getX() / 34;
	}
	
	public int getY() {
		return (int) pos.getY() / 34;
		
	}

}
