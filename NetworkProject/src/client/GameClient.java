package client;

import gameState.*;
import networkInfo.*;

import java.io.IOException;



import org.newdawn.slick.SlickException;

import com.esotericsoftware.kryonet.Client;

public class GameClient {
		public GameClient(){
			
		}
	public static void main(String[] args) throws IOException, InterruptedException, SlickException {
		Client client = new Client();
		PackageRegister.registerPackages(client.getKryo());
		client.addListener(new ClientListener());
		client.start();
	    client.connect(5000, "localhost", 54555, 54777);

	    
	    GameStart game = new GameStart(client);
	    SomeRequest request = new SomeRequest();
	    request.text = "Here is the request";
	    client.sendTCP(request);
	    Thread.sleep(1000);
	    }
}
