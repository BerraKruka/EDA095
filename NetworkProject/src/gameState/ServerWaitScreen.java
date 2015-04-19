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

public class ServerWaitScreen extends BasicGameState{
	public final static int ID = 22;
	private int playerNumber;
	
	private StateBasedGame game;
	
	private TextField[] allPlayer;
	private Server server;
	private ServerMonitor serverMonitor;
	private Client client;
	private ClientMonitor clientMonitor;
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
			this.game = game;
			Boolean input = false;
			allPlayer = new TextField[4];
			for(int i = 0; i < 4; i++){
				allPlayer[i] =  new TextField(container,StateUtils.font,200,60+20*i,200,20);
			    StateUtils.setTextFieldAttr(allPlayer[i],"waiting...",input);
			}
	}
	
	// now start the server.
	public void startServer() throws IOException{	
		serverMonitor = new ServerMonitor();
		server = new Server();
		server.start();
		NetworkUtils.registerPackages(server.getKryo());
		
		server.bind(NetworkUtils.TCPport,NetworkUtils.UDPport);
		server.addListener(new JoinGameListener(serverMonitor));
	}
	public void startClient(String name) throws IOException{
		clientMonitor = new ClientMonitor();
		client = new Client();
		new Thread(client).start();
		NetworkUtils.registerPackages(client.getKryo());
		
		client.connect(5000, "localhost", NetworkUtils.TCPport,NetworkUtils.UDPport);
		client.addListener(new ClientJoinListener(clientMonitor));
		
		// this send one time only, to join the game.
		JoinRequest request = new JoinRequest();
		request.id = name;
		client.sendTCP(request);
		
		// we now get his stuff from the listener
		try {
			playerNumber = clientMonitor.getNumber();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		allPlayer[playerNumber].setText(name);
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		for(TextField tf : allPlayer){
			tf.render(container, g);
		}
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}
	

}
