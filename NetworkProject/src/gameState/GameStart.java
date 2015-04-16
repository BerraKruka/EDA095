package gameState;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;

public class GameStart extends BasicGameState {
	private StateBasedGame game; // stored for later use

    /** The collision map indicating which tiles block movement - generated based on tile properties */
	private TiledMap grassMap;
    private boolean[][] blocked;
    
    private static final int SIZE = 34;

	// animation for movement
	private Animation sprite, up, down, left, right;
    private float x = 34f, y = 34f;




	public final static int ID = 1;


	private void initAnimation() throws SlickException {
		Image [] movementUp = {new Image("data/back.png"), new Image("data/back1.png")};
        Image [] movementDown = {new Image("data/front.png"), new Image("data/front1.png")};
        Image [] movementLeft = {new Image("data/left.png"), new Image("data/left1.png")};
        Image [] movementRight = {new Image("data/right.png"), new Image("data/right1.png")};
        int [] duration = {300, 300};   

		/*
		 * false variable means do not auto update the animation. By setting it
		 * to false animation will update only when the user presses a key.
		 */
		up = new Animation(movementUp, duration, false);
		down = new Animation(movementDown, duration, false);
		left = new Animation(movementLeft, duration, false);
		right = new Animation(movementRight, duration, false);

		// Original orientation of the sprite. It will look right.
		sprite = right;
	}

	private void initMap() throws SlickException {
		grassMap = new TiledMap("data/grassmap.tmx");
		// build a collision map based on tile properties in the TileD map
		blocked = new boolean[grassMap.getWidth()][grassMap.getHeight()];

		for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {
			for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {
				int tileID = grassMap.getTileId(xAxis, yAxis, 0);
				String value = grassMap.getTileProperty(tileID, "blocked",
						"false");
				if ("true".equals(value)) {
					blocked[xAxis][yAxis] = true;
				}
			}
		}
	}

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.game = game;
		initAnimation();
		initMap();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
	      grassMap.render(0, 0);
	      sprite.draw((int)x, (int)y);
	}

	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		 Input input = container.getInput();
	        if (input.isKeyDown(Input.KEY_UP))
	        {
	            sprite = up;
	            if (!isBlocked(x, y - delta * 0.1f))
	            {
	                sprite.update(delta);
	                // The lower the delta the slowest the sprite will animate.
	                y -= delta * 0.1f;
	            }
	        }
	        else if (input.isKeyDown(Input.KEY_DOWN))
	        {
	            sprite = down;
	            if (!isBlocked(x, y + SIZE + delta * 0.1f))
	            {
	                sprite.update(delta);
	                y += delta * 0.1f;
	            }
	        }
	        else if (input.isKeyDown(Input.KEY_LEFT))
	        {
	            sprite = left;
	            if (!isBlocked(x - delta * 0.1f, y))
	            {
	                sprite.update(delta);
	                x -= delta * 0.1f;
	            }
	        }
	        else if (input.isKeyDown(Input.KEY_RIGHT))
	        {
	            sprite = right;
	            if (!isBlocked(x + SIZE + delta * 0.1f, y))
	            {
	                sprite.update(delta);
	                x += delta * 0.1f;
	            }
	        }
		
	}

	private boolean isBlocked(float f, float y2) {
	     int xBlock = (int)x / SIZE;
	        int yBlock = (int)y / SIZE;
	        return blocked[xBlock][yBlock];		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}
	  public void keyReleased(int key, char c) {
	        switch(key) {
	        case Input.KEY_EQUALS:
	            game.enterState(Menu.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
	            break;
	        case Input.KEY_2:
	            // TODO: Implement later
	            break;
	        case Input.KEY_3:
	            // TODO: Implement later
	            break;
	        default:
	            break;
	        }
	    }
	

}