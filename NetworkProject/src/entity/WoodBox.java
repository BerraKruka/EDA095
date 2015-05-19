package entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class WoodBox extends GameEntity {
//	public final static float scale = 0.20f;
	public final static int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	private Image boxSprite;
	private Pos pos;

	public WoodBox(Pos pos, float width, float height, int direction) {
		super(pos, width, height);
		this.pos = pos;
		// TODO Auto-generated constructor stub
		try {
			boxSprite = getDirectionImage(direction);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void draw(){
		boxSprite.draw(	pos.x,
						pos.y);
		this.setPos(pos);
	}

	private static Image getDirectionImage(int direction) throws SlickException{
		switch(direction){
			case WoodBox.DOWN: 
				return new Image("data/wood.png");
			case WoodBox.UP:
				return new Image("data/wood.png");
			case WoodBox.LEFT:
				return new Image("data/wood.png");
			case WoodBox.RIGHT:
				return new Image("data/wood.png");
			default:
				return new Image("data/wood.png");
		}
	}

}
