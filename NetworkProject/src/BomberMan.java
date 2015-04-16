import gameState.*;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeOutTransition;
 
public class BomberMan extends StateBasedGame{

    public BomberMan(String name) {
        super(name);
        // TODO Auto-generated constructor stub
    }
 
    @Override
    public void initStatesList(GameContainer container) throws SlickException {
    	
    	Menu menu = new Menu();
    	GameStart gameStart = new GameStart();
    	
    	addState(menu);
    	addState(gameStart);
 
    }
   
 
}