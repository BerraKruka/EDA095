package gameState;

import java.io.IOException;

import networkInfo.JoinRequest;
import networkInfo.NetworkUtils;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import server.JoinGameListener;
import server.ServerMonitor;
import client.ClientJoinListener;
import client.ClientMonitor;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Server;

public class ServerWaitScreen extends BasicGameState {
	public final static int ID = 22;

	private StateBasedGame game;

	private Server server;
	private ServerMonitor serverMonitor;

	private TextField[] allPlayer;
	private int nPlayers;
	private String currentPlayerID;
	private int currentPlayerNumber;
	private String[] currentPlayers;

	private Client client;
	private ClientMonitor clientMonitor;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.game = game;

		allPlayer = new TextField[4];
		StateUtils.initPlayersTextField(container, allPlayer);
	}

	// now start the server.
	public void startServer() throws IOException {
		serverMonitor = new ServerMonitor();
		server = NetworkUtils.setupServer();
		server.addListener(new JoinGameListener(serverMonitor));
		server.start();

	}

	public void startClient(String name) throws IOException {
		currentPlayerID = name;
		clientMonitor = new ClientMonitor(currentPlayerID);
		client = NetworkUtils.setupClient(clientMonitor,"localhost");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		for (TextField tf : allPlayer) {
			tf.render(container, g);
		}
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		try {
			if (clientMonitor.newInfo()) {
				nPlayers = clientMonitor.getNPlayers();
				currentPlayerNumber = clientMonitor.getPlayerNumber();
				currentPlayers = clientMonitor.getCurrentPlayers();
				for (int i = 0; i < nPlayers; i++) {
					allPlayer[i].setText(currentPlayers[i]);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

}
