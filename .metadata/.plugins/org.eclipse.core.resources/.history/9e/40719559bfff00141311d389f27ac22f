import gameState.*;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeOutTransition;

import Game.GameStart;
 
public class BomberMan extends StateBasedGame{

    public BomberMan(String name) {
        super(name);
    }
 
    @Override
    public void initStatesList(GameContainer container) throws SlickException {
    	// here start the game.
    	Menu menu = new Menu();
    	ClientScreen joinGame = new ClientScreen();
    	ServerScreen hostGame = new ServerScreen();
    	ServerWaitScreen serverWait = new ServerWaitScreen();
    	ClientWaitScreen clientWait = new ClientWaitScreen();
    	GameStart game = new GameStart();
    	// all the screen is to be register.
    	addState(menu);
    	addState(joinGame);
    	addState(hostGame);
    	addState(serverWait);
    	addState(clientWait);
    	addState(game);

 
    }
   
 
}