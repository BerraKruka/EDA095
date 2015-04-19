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

import client.ClientJoinListener;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Server;

import entity.Player;

public class ClientWaitScreen extends BasicGameState{
	public final static int ID = 22;
	private StateBasedGame game;
	private TextField player1,player2,player3,player4;
	
	private Client client;
	private String hostIP ;
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
			
			this.game = game;
			player1 = new TextField(container,StateUtils.font,200,60,200,20);
			player2 = new TextField(container,StateUtils.font,200,80,200,20);
			player3 = new TextField(container,StateUtils.font,200,100,200,20);
			player4 = new TextField(container,StateUtils.font,200,120,200,20);

			Boolean input = false;
			StateUtils.setTextFieldAttr(player1,"waiting...",input);
			StateUtils.setTextFieldAttr(player2,"waiting...",input);
			StateUtils.setTextFieldAttr(player3,"waiting...",input);
			StateUtils.setTextFieldAttr(player4,"waiting...",input);
	}
	public void startClient(String name,String ip) throws IOException{
		hostIP = ip;
		client = new Client();
		client.addListener(new ClientJoinListener());
		NetworkUtils.registerPackages(client.getKryo());
		client.start();
		client.connect(5000, ip, 54555,54777);

		JoinRequest request = new JoinRequest(name);
		client.sendTCP(request);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		player1.render(container,g);
		player2.render(container, g);
		player3.render(container,g);
		player4.render(container, g);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}
	

}
