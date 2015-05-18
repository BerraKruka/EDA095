package gameState;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import networkInfo.ActionMessage;
import networkInfo.NetworkUtils;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;

import server.JoinGameListener;
import server.ServerMonitor;
import client.ClientMonitor;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Server;

import entity.*;

public class GameStart extends BasicGameState {
	private TiledMap grassMap;

	public final static int ID = 9;

	private StateBasedGame game; // stored for later use
	private Player player0;
	private Player player1;
	private Player player2;
	private Player player3;
	private SteelBox box;
	private LinkedList<SteelBox> steelBoxes;
	private GameEntity[][] positions;

	private Server server;
	private Client client;

	private ServerMonitor serverMonitor;
	private ClientMonitor clientMonitor;


	public GameStart() throws SlickException {
		grassMap = new TiledMap("data/grassmap.tmx");
		positions = new GameEntity[grassMap.getWidth()][grassMap.getHeight()];
		steelBoxes = new LinkedList<SteelBox>();
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
		float size = GameEntity.SIZE;

		player0 = new Player(pos, size, size, positions);
		player0.initAnimation();

		Pos pos1 = new Pos(1, 8);

		player1 = new Player(pos1, size, size, positions);
		player1.initAnimation();

		Pos pos2 = new Pos(8, 1);
		player2 = new Player(pos2, size, size, positions);
		player2.initAnimation();

		Pos pos3 = new Pos(8, 8);

		player3 = new Player(pos3, size, size, positions);
		player3.initAnimation();

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		grassMap.render(0, 0);
		player0.draw();
		player1.draw();
		player2.draw();
		player3.draw();
		// box.draw();
	}

	private void readPacket(float delta) {
		if (clientMonitor.isAction()) {

			int playerId = clientMonitor.getPlayerID();
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
		clientMonitor.actionFinished();
	}

	private void readCommand(Player player, float delta) {
		String command = clientMonitor.getAction();
		switch (command) {
		case "UP":
			player.move(Player.UP, delta);
			break;
		case "DOWN":
			player.move(Player.DOWN, delta);
			break;
		case "LEFT":
			player.move(Player.LEFT, delta);
			break;
		case "RIGHT":
			player.move(Player.RIGHT, delta);
			break;
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int d)
			throws SlickException {
		Input input = container.getInput();
		float delta = d * 0.1f;
		// action for player in here

		if (input.isKeyDown(Input.KEY_UP)) {
			try {
				ActionMessage actionMsg = new ActionMessage();
				actionMsg.action = "UP";
				actionMsg.playerID = clientMonitor.getPlayerNumber();
				client.sendUDP(actionMsg);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (input.isKeyDown(Input.KEY_DOWN)) {
			try {
				ActionMessage actionMsg = new ActionMessage();
				actionMsg.action = "DOWN";
				actionMsg.playerID = clientMonitor.getPlayerNumber();
				client.sendUDP(actionMsg);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (input.isKeyDown(Input.KEY_LEFT)) {
			try {
				ActionMessage actionMsg = new ActionMessage();
				actionMsg.action = "LEFT";
				actionMsg.playerID = clientMonitor.getPlayerNumber();
				client.sendUDP(actionMsg);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (input.isKeyDown(Input.KEY_RIGHT)) {
			try {
				ActionMessage actionMsg = new ActionMessage();
				actionMsg.action = "RIGHT";
				actionMsg.playerID = clientMonitor.getPlayerNumber();
				client.sendUDP(actionMsg);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// action for bomb :)
		else if (input.isKeyDown(Input.KEY_SPACE)) {

		}

		readPacket(delta);

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