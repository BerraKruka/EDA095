package gameState;

import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class ServerScreen extends BasicGameState{
	public static final int ID = 21;
	private StateBasedGame game;
	private TextField playerID;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
	    this.game = game;
	    boolean input = true;
	    playerID = new TextField(container,StateUtils.font,200,60,200,20);
		StateUtils.setTextFieldAttr(playerID,"Noway",input);
		playerID.setCursorPos(200);

		
	}
	

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
        g.setColor(Color.white);
        g.drawString("Host Game", 50, 10);
        g.drawString("Player Name", 50, 60);
        playerID.render(container, g);
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
	
	private void transitionHandler(){
    	ServerWaitScreen serverWait = (ServerWaitScreen) game.getState(ServerWaitScreen.ID);
    	try {
			serverWait.startServer();
			serverWait.startClient(playerID.getText());
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	 public void keyReleased(int key, char c) {
	        switch(key) {
	        case Input.KEY_BACK:
	        	if(playerID.hasFocus()){
	        		playerID.setText("");
	        	}
	        	break;
	        case Input.KEY_TAB:
	        		playerID.setFocus(true);
	            break;
	        case Input.KEY_ENTER:
	        	transitionHandler();
	        	game.enterState(ServerWaitScreen.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
	        	break;
	        case Input.KEY_ESCAPE:
	        	game.enterState(Menu.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
	            break;
	        default:
	            break;
	        }
	    }
}
