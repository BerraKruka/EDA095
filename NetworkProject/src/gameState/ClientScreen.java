package gameState;

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

public class ClientScreen extends BasicGameState{
	public static final int ID = 1;
	private StateBasedGame game;
	private TextField playerID, hostIP, message;
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
	    this.game = game;
	    Boolean input = true;
	    playerID = new TextField(container,StateUtils.font,200,60,200,20);
		StateUtils.setTextFieldAttr(playerID,"Noway",input);
		playerID.setCursorPos(200);
		
		
//		message = new TextField(container,StateUtils.font,200,80,200,20);
//		StateUtils.setTextFieldAttr(message,"localhost",input);
		
		hostIP = new TextField(container,StateUtils.font,200,80,200,20);
		StateUtils.setTextFieldAttr(hostIP,"...",input);

	}
	

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
        g.setColor(Color.white);
        g.drawString("Join Game", 50, 10);
        
        g.drawString("Player Name", 50, 60);
        playerID.render(container, g);

        g.drawString("Host IP: ",50,80); 
        hostIP.render(container, g);
        
      //  g.drawString("Message: ",50,100);
        
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
    	ClientWaitScreen clientWait = (ClientWaitScreen) game.getState(ClientWaitScreen.ID);
    	try {
    		String name = playerID.getText();
    		String ip = hostIP.getText();
			Client client = clientWait.startClient(name, ip);
			ClientMonitor monitor = clientWait.getMonitor();
			// this is to wait for a response after enter
			//message.setText("Attempt to Join Game");
			Thread.sleep(3000);
			
			// depends on the answer, we will do some different stuff
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
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
	        	transitionHandler();
	        	game.enterState(ClientWaitScreen.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
	        	break;
	        case Input.KEY_ESCAPE:
	        	game.enterState(Menu.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));

	            break;
	        default:
	            break;
	        }
	    }
}
