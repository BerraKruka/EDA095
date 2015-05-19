package gameState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import networkInfo.ActionMessage;
import networkInfo.BombMessage;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;

import server.ServerMonitor;
import client.ClientMonitor;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Server;

import entity.Bomb;
import entity.GameEntity;
import entity.Player;
import entity.Pos;
import entity.WoodBox;

public class GameStart extends BasicGameState {
	private TiledMap grassMap;

	public final static int ID = 9;

	private StateBasedGame game; // stored for later use
	private Player player0;
	private Player player1;
	private Player player2;
	private Player player3;
	private LinkedList<WoodBox> boxes;
	private LinkedList<Bomb> bombs;
	private GameEntity[][] positions;
	private WoodBox box;
	private Bomb bomb;

	private boolean[][] blocked;
	private static final int SIZE = 34;
	
	private ArrayList<Player> players;

	private Server server;
	private ServerMonitor serverMonitor;
	private Client client;
	private ClientMonitor clientMonitor;
	private Shape shape;

	public GameStart() throws SlickException {
		grassMap = new TiledMap("data/test.tmx");
		positions = new GameEntity[grassMap.getWidth()][grassMap.getHeight()];
		boxes = new LinkedList<WoodBox>();
		bombs = new LinkedList<Bomb>();
		players = new ArrayList<Player>();
		
	}

	public void setServer(Server serverName, ServerMonitor monitor)
			throws IOException {
		serverMonitor = monitor;
		server = serverName;

	}

	public void setClient(Client clientName, ClientMonitor monitor)
			throws IOException {
		clientMonitor = monitor;
		client = clientName;
	}

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

		// begin temp code
		this.game = game;

		Pos pos = new Pos(1, 1);
		float width = 20;
		float height = 10;

		player0 = new Player(pos, width, height, positions, 0);
		player0.initAnimation();

		Pos pos1 = new Pos(1, 18);

		player1 = new Player(pos1, width, height, positions, 1);
		player1.initAnimation();

		Pos pos2 = new Pos(18, 1);
		player2 = new Player(pos2, width, height, positions, 2);
		player2.initAnimation();

		Pos pos3 = new Pos(18, 18);

		player3 = new Player(pos3, width, height, positions, 3);
		player3.initAnimation();
		
		players.add(player0);
		players.add(player1);
		players.add(player2);
		players.add(player3);

		blocked = new boolean[grassMap.getWidth()][grassMap.getHeight()];
		for (int xAxis = 0; xAxis < grassMap.getWidth(); xAxis++) {

			for (int yAxis = 0; yAxis < grassMap.getHeight(); yAxis++) {

				int tileID = grassMap.getTileId(xAxis, yAxis, 1);
				String value = grassMap.getTileProperty(tileID, "blocked",
						"false");
				if (value.equals("true")) {
					blocked[xAxis][yAxis] = true;
					positions[xAxis][yAxis] = new WoodBox(new Pos(xAxis, yAxis), 34,34, 1);
				}
			}
		}
	//	placeBoxes();

	}

//	private void placeBoxes() {
//		Pos pos;
//
//		pos = new Pos(6, 1);
//		box = new WoodBox(pos, (float) SIZE, (float) SIZE, WoodBox.DOWN);
//		blocked[6][1] = true;
//		boxes.add(box);
//
//		pos = new Pos(6, 2);
//		box = new WoodBox(pos, (float) SIZE, (float) SIZE, WoodBox.DOWN);
//		blocked[6][2] = true;
//		boxes.add(box);
//
//		pos = new Pos(6, 3);
//		box = new WoodBox(pos, (float) SIZE, (float) SIZE, WoodBox.DOWN);
//		blocked[6][3] = true;
//		boxes.add(box);
//
//		pos = new Pos(6, 4);
//		box = new WoodBox(pos, (float) SIZE, (float) SIZE, WoodBox.DOWN);
//		blocked[6][4] = true;
//		boxes.add(box);
//
//		pos = new Pos(13, 1);
//		box = new WoodBox(pos, (float) SIZE, (float) SIZE, WoodBox.DOWN);
//		blocked[13][1] = true;
//		boxes.add(box);
//
//		pos = new Pos(13, 2);
//		box = new WoodBox(pos, (float) SIZE, (float) SIZE, WoodBox.DOWN);
//		blocked[13][2] = true;
//		boxes.add(box);
//
//		pos = new Pos(13, 3);
//		box = new WoodBox(pos, (float) SIZE, (float) SIZE, WoodBox.DOWN);
//		blocked[13][3] = true;
//		boxes.add(box);
//
//		pos = new Pos(13, 4);
//		box = new WoodBox(pos, (float) SIZE, (float) SIZE, WoodBox.DOWN);
//		blocked[13][4] = true;
//		boxes.add(box);
//
//		pos = new Pos(6, 15);
//		box = new WoodBox(pos, (float) SIZE, (float) SIZE, WoodBox.DOWN);
//		blocked[6][15] = true;
//		boxes.add(box);
//
//		pos = new Pos(6, 16);
//		box = new WoodBox(pos, (float) SIZE, (float) SIZE, WoodBox.DOWN);
//		blocked[6][16] = true;
//		boxes.add(box);
//
//		pos = new Pos(6, 17);
//		box = new WoodBox(pos, (float) SIZE, (float) SIZE, WoodBox.DOWN);
//		blocked[6][17] = true;
//		boxes.add(box);
//
//		pos = new Pos(6, 18);
//		box = new WoodBox(pos, (float) SIZE, (float) SIZE, WoodBox.DOWN);
//		blocked[6][18] = true;
//		boxes.add(box);
//
//		pos = new Pos(13, 15);
//		box = new WoodBox(pos, (float) SIZE, (float) SIZE, WoodBox.DOWN);
//		blocked[13][15] = true;
//		boxes.add(box);
//
//		pos = new Pos(13, 16);
//		box = new WoodBox(pos, (float) SIZE, (float) SIZE, WoodBox.DOWN);
//		blocked[13][16] = true;
//		boxes.add(box);
//
//		pos = new Pos(13, 17);
//		box = new WoodBox(pos, (float) SIZE, (float) SIZE, WoodBox.DOWN);
//		blocked[13][17] = true;
//		boxes.add(box);
//
//		pos = new Pos(13, 18);
//		box = new WoodBox(pos, (float) SIZE, (float) SIZE, WoodBox.DOWN);
//		blocked[13][18] = true;
//		boxes.add(box);
//
//		pos = new Pos(1, 10);
//		box = new WoodBox(pos, (float) SIZE, (float) SIZE, WoodBox.DOWN);
//		blocked[1][10] = true;
//		boxes.add(box);
//
//		pos = new Pos(2, 10);
//		box = new WoodBox(pos, (float) SIZE, (float) SIZE, WoodBox.DOWN);
//		blocked[2][10] = true;
//		boxes.add(box);
//
//		pos = new Pos(17, 9);
//		box = new WoodBox(pos, (float) SIZE, (float) SIZE, WoodBox.DOWN);
//		blocked[17][9] = true;
//		boxes.add(box);
//
//		pos = new Pos(18, 9);
//		box = new WoodBox(pos, (float) SIZE, (float) SIZE, WoodBox.DOWN);
//		blocked[18][9] = true;
//		boxes.add(box);
//
//		pos = new Pos(10, 10);
//		box = new WoodBox(pos, (float) SIZE, (float) SIZE, WoodBox.DOWN);
//		blocked[10][10] = true;
//		boxes.add(box);
//
//		pos = new Pos(9, 10);
//		box = new WoodBox(pos, (float) SIZE, (float) SIZE, WoodBox.DOWN);
//		blocked[9][10] = true;
//		boxes.add(box);
//
//		pos = new Pos(9, 9);
//		box = new WoodBox(pos, (float) SIZE, (float) SIZE, WoodBox.DOWN);
//		blocked[9][9] = true;
//		boxes.add(box);
//
//		pos = new Pos(10, 9);
//		box = new WoodBox(pos, (float) SIZE, (float) SIZE, WoodBox.DOWN);
//		blocked[10][9] = true;
//		boxes.add(box);
//
//	}

	private void die(Bomb bomb, Player player) {
		if (bomb.isExploaded()) {

			ArrayList<Pos> deadList = new ArrayList<Pos>();
			
			int x = bomb.getX();
			int y = bomb.getY();
		

			deadList.add(new Pos(x, y));
			deadList.add(new Pos(x + 1, y));
			deadList.add(new Pos(x + 2, y));
			deadList.add(new Pos(x + 3, y));
			deadList.add(new Pos(x - 1, y));
			deadList.add(new Pos(x - 2, y));
			deadList.add(new Pos(x - 3, y));
			deadList.add(new Pos(x - 4, y));
			deadList.add(new Pos(x + 4, y));

			deadList.add(new Pos(x, y + 1));
			deadList.add(new Pos(x, y + 2));
			deadList.add(new Pos(x, y + 3));
			deadList.add(new Pos(x, y + 4));
			deadList.add(new Pos(x, y - 1));
			deadList.add(new Pos(x, y - 2));
			deadList.add(new Pos(x, y - 3));
			deadList.add(new Pos(x, y - 4));



			for (int i = 0; i < deadList.size(); i++) {
				Pos dead = deadList.get(i);
				if ((dead.getX() / 34 )== (player.getX())
						&& (dead.getY() / 34) == player.getY()) {
					player.setDead();
				}
			}

		}

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		grassMap.render(0, 0, 0);
		grassMap.render(0, 0, 1);
		for(int i = 0; i < players.size(); i++) {
			if(players.get(i).isDead()) {
				players.remove(players.get(i));
			}else {
				
				players.get(i).draw();
			}
		}
		
		
	
		for (WoodBox bx : boxes) {
			bx.draw();
		}

		if (!bombs.isEmpty()) {
			for (Bomb bomb : bombs) {
				bomb.draw();
				for(Player player : players) {
					die(bomb,player);
				}
				if(bomb.isExploaded()){
				//	bombs.remove(bomb);
				}
			}
		}
		grassMap.render(0, 0, 2);

	}

	private void readAction(float delta) throws InterruptedException {
		int playerId = clientMonitor.getPlayerID();
		if (clientMonitor.isAction()) {
			switch (playerId) {
			case 0:
				readCommand(player0, delta);
				break;
			case 1:
				readCommand(player1, delta);
				break;
			case 2:
				readCommand(player2, delta);
				break;
			case 3:
				readCommand(player3, delta);
				break;
			}
		}

	}

	private void readBomb() throws InterruptedException {
		int playerId = clientMonitor.getPlayerID();
		if (clientMonitor.isBomb()) {
			switch (playerId) {
			case 0:
				System.out.println("Bomb player0");
				dropBomb(player0);
				break;
			case 1:
				System.out.println("Bomb player1");
				dropBomb(player1);
				break;
			case 2:
				System.out.println("Bomb player2");
				dropBomb(player2);
				break;
			case 3:
				System.out.println("Bomb player3");
				dropBomb(player3);
				break;
			}

		}

	}

	private void dropBomb(Player player) {
		if(!player.isDead()) {
		float x = player.getPos().getX();
		float y = player.getPos().getY();
		Bomb bomb = new Bomb(x, y, GameEntity.SIZE, GameEntity.SIZE, 3000);
		bombs.add(bomb);
		System.out.println("bomb added");
		clientMonitor.bombFinsihed();
		}
	}

	private void readCommand(Player player, float delta) {
		if(!player.isDead()) {
		String command = clientMonitor.getAction();
		switch (command) {
		case "UP":
			player.move(Player.UP, delta, blocked);
			break;
		case "DOWN":
			player.move(Player.DOWN, delta, blocked);
			break;
		case "LEFT":
			player.move(Player.LEFT, delta, blocked);
			break;
		case "RIGHT":
			player.move(Player.RIGHT, delta, blocked);
			break;
		}
		clientMonitor.actionFinished();
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int d)
			throws SlickException {
		Input input = container.getInput();
		float delta = d * 0.2f;
		try {
			readAction(delta);
			readBomb();
		} catch (InterruptedException e1) {
			System.out.println("Interrupted");
			e1.printStackTrace();
		}

		if (input.isKeyDown(Input.KEY_UP)) {
			try {
				ActionMessage actionMsg = new ActionMessage();
				actionMsg.action = "UP";
				actionMsg.playerID = clientMonitor.getPlayerNumber();
				client.sendUDP(actionMsg);
			} catch (InterruptedException e) {
				System.out.println("Interrupted");
				e.printStackTrace();
			}

		} else if (input.isKeyDown(Input.KEY_DOWN)) {
			try {
				ActionMessage actionMsg = new ActionMessage();
				actionMsg.action = "DOWN";
				actionMsg.playerID = clientMonitor.getPlayerNumber();
				client.sendUDP(actionMsg);
			} catch (InterruptedException e) {
				System.out.println("Interrupted");
				e.printStackTrace();
			}
		} else if (input.isKeyDown(Input.KEY_LEFT)) {
			try {
				ActionMessage actionMsg = new ActionMessage();
				actionMsg.action = "LEFT";
				actionMsg.playerID = clientMonitor.getPlayerNumber();
				client.sendUDP(actionMsg);
			} catch (InterruptedException e) {
				System.out.println("Interrupted");
				e.printStackTrace();
			}
		} else if (input.isKeyDown(Input.KEY_RIGHT)) {
			try {
				ActionMessage actionMsg = new ActionMessage();
				actionMsg.action = "RIGHT";
				actionMsg.playerID = clientMonitor.getPlayerNumber();
				client.sendUDP(actionMsg);
			} catch (InterruptedException e) {
				System.out.println("Interrupted");
				e.printStackTrace();
			}
		} else if (input.isKeyDown(Input.KEY_SPACE)) {
			try {
				BombMessage bm = new BombMessage();
				bm.playerID = clientMonitor.getPlayerNumber();
				client.sendUDP(bm);
			} catch (InterruptedException e) {
				System.out.println("Interrupted");
				e.printStackTrace();
			}

		}

	}

	@Override
	public int getID() {
		return ID;
	}

	public void keyReleased(int key, char c) {
		switch (key) {
		case Input.KEY_ESCAPE:
			game.enterState(Menu.ID, new FadeOutTransition(Color.black),
					new FadeInTransition(Color.black));
			break;
		default:
			break;
		}
	}

}