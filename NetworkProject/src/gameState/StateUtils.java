package gameState;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;

public class StateUtils {
	public static final	Font font = new TrueTypeFont(new java.awt.Font(java.awt.Font.SERIF,java.awt.Font.BOLD , 12), false);

	public static void setTextFieldAttr(TextField tf, String text, Boolean input){
	     tf.setText(text);
		 tf.setAcceptingInput(input);
	     tf.setCursorVisible(true);
	     tf.setBackgroundColor(Color.lightGray);
	     tf.setCursorPos(100);
	}
	
	public static void initPlayersTextField(GameContainer container,TextField[] allPlayer){
		for(int i = 0; i < 4; i++){
			allPlayer[i] =  new TextField(container,StateUtils.font,200,60+20*i,200,20);
		    StateUtils.setTextFieldAttr(allPlayer[i],"waiting...",false);
		}
	}
}
