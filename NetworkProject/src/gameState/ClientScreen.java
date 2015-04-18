package gameState;

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

public class ClientScreen extends BasicGameState{
	public static final int ID = 1;
	private StateBasedGame game;
	private TextField playerID, hostIP;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
	    this.game = game;
	
	    playerID = new TextField(container,StateUtils.font,200,60,200,20);
        playerID.setFocus(true);
		StateUtils.setTextFieldAttr(playerID,"Noway");
		playerID.setCursorPos(200);
		hostIP = new TextField(container,StateUtils.font,200,80,200,20);
		StateUtils.setTextFieldAttr(hostIP,"localhost");

	}
	

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
        g.setColor(Color.white);
        g.drawString("Join Game", 50, 10);
        g.drawString("Player Name", 50, 60);
        playerID.render(container, g);

        g.drawString("Host IP: ",50,80);
        hostIP.render(container, g);	}

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
	        case Input.KEY_BACK:
	        	if(playerID.hasFocus()){
	        		playerID.setText("");
	        	}else if(hostIP.hasFocus()){
	        		hostIP.setText("");
	        	}
	        	break;
	        case Input.KEY_TAB:
	        	if(playerID.hasFocus()){
	        		hostIP.setFocus(true);
	        	}else{
	        		playerID.setFocus(true);
	        	}
	            break;
	        case Input.KEY_ENTER:
	        	game.enterState(Menu.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));

	        case Input.KEY_ESCAPE:
	        	game.enterState(Menu.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));

	            break;
	        default:
	            break;
	        }
	    }
}
