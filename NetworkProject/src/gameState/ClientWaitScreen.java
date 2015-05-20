package gameState;

import java.io.IOException;

import networkInfo.JoinRequest;
import networkInfo.NetworkUtils;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import Game.GameStart;
import client.ClientGameListener;
import client.ClientJoinListener;
import client.ClientMonitor;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Server;

import entity.Player;

public class ClientWaitScreen extends BasicGameState {
	public final static int ID = 23;
	private StateBasedGame game;

	private TextField[] allPlayer;
	private int nPlayers;
	private String currentPlayerID;
	private String[] currentPlayers;

	private Client client;
	private ClientMonitor clientMonitor;
	private ClientJoinListener joinListener;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.game = game;
		allPlayer = new TextField[4];
		StateUtils.initPlayersTextField(container, allPlayer);
	}

	public Client startClient(String name, String ip) throws IOException {
		currentPlayerID = name;
		clientMonitor = new ClientMonitor(currentPlayerID);
		
		client = NetworkUtils.setupClient(clientMonitor, ip);
		
		joinListener = new ClientJoinListener(clientMonitor);
		client.addListener(joinListener);

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
				currentPlayers = clientMonitor.getCurrentPlayers();
				for (int i = 0; i < nPlayers; i++) {
					allPlayer[i].setText(currentPlayers[i]);
				}
				if (clientMonitor.isStart()) {
					GameStart start = (GameStart) game.getState(GameStart.ID);

					// skicka med clienten och starta spelet
					try {
						start.setClient(client, clientMonitor);
						client.removeListener(joinListener);
						client.addListener(new ClientGameListener(clientMonitor) );
						game.enterState(GameStart.ID, new FadeOutTransition(
								Color.black), new FadeInTransition(Color.black));
					} catch (IOException e) {
						e.printStackTrace();
						System.exit(1);
					}
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

	public ClientMonitor getMonitor() {
		// TODO Auto-generated method stub
		return clientMonitor;
	}

}
