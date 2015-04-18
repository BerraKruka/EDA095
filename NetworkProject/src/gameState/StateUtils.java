package gameState;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;

public class StateUtils {
	public static final	Font font = new TrueTypeFont(new java.awt.Font(java.awt.Font.SERIF,java.awt.Font.BOLD , 12), false);

	public static void setTextFieldAttr(TextField tf, String text){
	     tf.setText(text);
		 tf.setAcceptingInput(true);
	     tf.setCursorVisible(true);
	     tf.setBackgroundColor(Color.lightGray);
	}
}
