package gameState;

import java.util.ArrayList;
import java.util.LinkedList;

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

import entity.*;

public class GameStart extends BasicGameState {
	private StateBasedGame game; // stored for later use
	private Player player;
	private SteelBox box;
	private LinkedList<SteelBox> steelBoxes;
	private GameEntity[][] positions;
	
	public GameStart() throws SlickException{
		grassMap = new TiledMap("data/grassmap.tmx");
		positions = new GameEntity[grassMap.getWidth()][grassMap.getHeight()];
		System.out.println(positions.length+" ::::::"+positions[0].length);
		steelBoxes = new LinkedList<SteelBox>();
		initTemp();
	}
	
	/**
	 * The collision map indicating which tiles block movement - generated based
	 * on tile properties
	 */
	private TiledMap grassMap;

	public final static int ID = 1;


	/** tempor�r funktion f�r att init n�gra 
	 * state f�r att testa 
	 *  */
	private void initTemp(){
		float size = GameEntity.SIZE ;
		Pos pos = new Pos(1,1);
		player = new Player(pos,size , size,positions);
		
		pos = new Pos(4,5);
		box = new SteelBox(pos,size,size,SteelBox.DOWN);
		positions[4][5] = box;
	}
	
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// begin temp code
		this.game = game;
		player.initAnimation();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// temporary code, give player a start pos;
	
		grassMap.render(0, 0);
		player.draw();
		box.draw();
	}
	

	@Override
	public void update(GameContainer container, StateBasedGame game, int d)
			throws SlickException {
		Input input = container.getInput();
		
		// action for player in here
		float delta = d*0.1f;
		if (input.isKeyDown(Input.KEY_UP)) {
			player.move(Player.UP, delta);
		} else if (input.isKeyDown(Input.KEY_DOWN)) {
			player.move(Player.DOWN, delta);
		} else if (input.isKeyDown(Input.KEY_LEFT)) {
			player.move(Player.LEFT,delta);
		} else if (input.isKeyDown(Input.KEY_RIGHT)) {
			player.move(Player.RIGHT, delta);
		}
		// action for bomb :)
		else if (input.isKeyDown(Input.KEY_SPACE)){
			
		}
		
	}


	@Override
	public int getID() {
		return ID;
	}

	public void keyReleased(int key, char c) {
		switch (key) {
		case Input.KEY_ESCAPE:
			game.enterState(Menu.ID, new FadeOutTransition(Color.black),
					new FadeInTransition(Color.black));
			break;
		default:
			break;
		}
	}

}