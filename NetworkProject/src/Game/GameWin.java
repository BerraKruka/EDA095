package Game;

import gameState.Menu;

import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import client.ClientMonitor;

import com.esotericsoftware.kryonet.Client;

public class GameWin extends BasicGameState{
	public static final int ID = 1000;
	private StateBasedGame game;
	private String playerID;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
	    this.game = game;		
	}
	
	public void setWinner(String winner){
		playerID = winner;
	}
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
        g.setColor(Color.white);
        
        g.drawString(" *_____    " + playerID+" Win    _____*", 50, 60);
     
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}
	
	 public void keyReleased(int key, char c) {
	        switch(key) {
	        case Input.KEY_ENTER:
	        	game.enterState(GameStart.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
	        	break;
	        case Input.KEY_ESCAPE:
	        	game.enterState(Menu.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
	            break;
	        default:
	            break;
	        }
	    }
}
