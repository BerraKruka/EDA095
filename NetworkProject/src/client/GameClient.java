package client;

import gameState.*;
import networkInfo.*;

import java.io.IOException;



import org.newdawn.slick.SlickException;

import com.esotericsoftware.kryonet.Client;

public class GameClient {
		private Client client;
		public GameClient() throws SlickException{
			client = new Client();
			NetworkUtils.registerPackages(client.getKryo());
			client.addListener(new ClientListener());
			client.start();
		}
		public void connect(int time, String address, int portTCP, int portUDP ) throws IOException{
			client.connect(time, address, portTCP,portUDP);
		}
}
