package gameState;

import java.io.IOException;

import networkInfo.GameStartMessage;
import networkInfo.JoinRequest;
import networkInfo.NetworkUtils;

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

import server.GamePlayListener;
import server.JoinGameListener;
import server.ServerMonitor;
import server.ServerSender;
import Game.GameStart;
import client.ClientGameListener;
import client.ClientJoinListener;
import client.ClientMonitor;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Server;

public class ServerWaitScreen extends BasicGameState {
	public final static int ID = 22;

	private StateBasedGame game;

	private Server server;
	private JoinGameListener joinListener;
	private ServerMonitor serverMonitor;

	private TextField[] allPlayer;
	private int nPlayers;
	private String currentPlayerID;
	private String[] currentPlayers;
	

	private Client client;
	private ClientMonitor clientMonitor;
	private ClientJoinListener clientJoinListener;
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
		joinListener = new JoinGameListener(serverMonitor);
		server.addListener(joinListener);
		server.start();

	}

	public Client startClient(String name, String ip) throws IOException {
		currentPlayerID = name;
		clientMonitor = new ClientMonitor(currentPlayerID);
		client = NetworkUtils.setupClient(clientMonitor, ip);
		
		clientJoinListener = new ClientJoinListener(clientMonitor);
		client.addListener(clientJoinListener);

		return client;
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
//				currentPlayerNumber = clientMonitor.getPlayerNumber();
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

	private void transitionHandler() {
		GameStart start = (GameStart) game.getState(GameStart.ID);
		client.removeListener(clientJoinListener);
		client.addListener(new ClientGameListener(clientMonitor) );
		//anslutningen e redan fixad ---- bara skicka med server / client till n�sta 	
		
		try {
		//	start.setServer(server, serverMonitor);
			start.setClient(client, clientMonitor);
			server.sendToAllTCP(new GameStartMessage());
			
			server.removeListener(joinListener);
			server.addListener(new GamePlayListener(serverMonitor));
			ServerSender ss = new ServerSender(serverMonitor,server);
			ss.start();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			System.exit(1);
		} 
	
			

		
	}

	public void keyReleased(int key, char c) {
		switch (key) {
		case Input.KEY_ENTER:
			transitionHandler();
			game.enterState(GameStart.ID, new FadeOutTransition(Color.black),
					new FadeInTransition(Color.black));
			break;
		case Input.KEY_ESCAPE:
			game.enterState(Menu.ID, new FadeOutTransition(Color.black),
					new FadeInTransition(Color.black));
			break;
		default:
			break;
		}
	}
	

	public ClientMonitor getMonitor() {
		// TODO Auto-generated method stub
		return clientMonitor;
	}

}
