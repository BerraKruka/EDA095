import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			try {
				AppGameContainer game = new AppGameContainer(new BomberMan("Game "));
				game.setMaximumLogicUpdateInterval(60);			
				game.setDisplayMode(640, 480, false);
				game.setTargetFrameRate(60);
				game.setAlwaysRender(true);
				game.setVSync(true);
				game.setShowFPS(true);
				game.start();
			} catch (SlickException e) {
				e.printStackTrace();
			}
	}

}
