import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {
	public static void main(String[] args) throws SlickException {
		// TODO Auto-generated method stub
		AppGameContainer game = initGame();
	}

	public static AppGameContainer initGame() throws SlickException {
		AppGameContainer game = new AppGameContainer(new BomberMan("Pew Pew "));
		game.setMaximumLogicUpdateInterval(60);
		game.setDisplayMode(640, 480, false);
		game.setTargetFrameRate(60);
		game.setAlwaysRender(true);
		game.setVSync(true);
		game.setShowFPS(false);
		game.start();
		return game;
	}

}
