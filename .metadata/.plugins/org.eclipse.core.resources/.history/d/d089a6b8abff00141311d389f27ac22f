package entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SteelBox extends GameEntity {
	public final static float scale = 0.25f;
	public final static int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	private Image boxSprite;
	private Pos pos;
	public SteelBox(Pos pos, float width, float height, int direction) {
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
		boxSprite.draw(	pos.x, pos.y,SteelBox.scale);
		this.setPos(pos);
	}

	private static Image getDirectionImage(int direction) throws SlickException{
		switch(direction){
			case SteelBox.DOWN: 
				return new Image("data/rocks.png");
			case SteelBox.UP:
				return new Image("data/rocks.png");
			case SteelBox.LEFT:
				return new Image("data/rocks.png");
			case SteelBox.RIGHT:
				return new Image("data/rocks.png");
			default:
				return new Image("data/rocks.png");
		}
	}

}
