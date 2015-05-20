package gameState;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.newdawn.slick.Color;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
 
public class Menu extends BasicGameState{
	private StateBasedGame game; // stored for later use
	public final static int ID = 0;
	private InetAddress IP;


	public void init(GameContainer container, StateBasedGame game) throws SlickException {
	    this.game = game;
	}
	
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
    	
    	try {
			IP = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
        g.setColor(Color.white);
        g.drawString("Bomber Man", 50, 10);
        g.drawString("ESC",600,10);
        g.drawString("1. Join A Game", 50, 100);
        g.drawString("2. Host A Game", 50, 120);
        g.drawString("Your IP address: "+IP.getHostAddress(), 50, 140);
     
    }
 
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
 
    }
 
    @Override
    public int getID() {
        return ID;
    }
    public void keyReleased(int key, char c) {
        switch(key) {
        case Input.KEY_1:
            game.enterState(ClientScreen.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
            break;
        case Input.KEY_2:
            game.enterState(ServerScreen.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
            break;
        case Input.KEY_ESCAPE:
        	System.exit(0);
        default:
            break;
        }
    }
 
}