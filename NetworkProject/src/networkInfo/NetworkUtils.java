package networkInfo;

import java.io.IOException;

import server.JoinGameListener;
import server.ServerMonitor;
import client.ClientJoinListener;
import client.ClientMonitor;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Server;

public class NetworkUtils {
	public final static int PLAYER1_ID = 0;
	public final static int PLAYER2_ID = 1;
	public final static int PLAYER3_ID = 2;
	public final static int PLAYER4_ID = 3;
	
	public final static int TCPport= 20000;
	public final static int UDPport= 20001;
	
	public final static String HOST_IP="localhost";
	
	public static void registerPackages(Kryo kryo){
		kryo.register(JoinRequest.class);
		kryo.register(JoinAckResponse.class);
		kryo.register(GameStartMessage.class);
		kryo.register(PlayerAction.class);
		kryo.register(ActionMessage.class);
		kryo.register(String[].class);
		kryo.register(BombMessage.class);
	}
	
	public static Server setupServer() throws IOException{
		Server server = new Server();
		registerPackages(server.getKryo());
		server.bind(TCPport);
		return server;
	}
	
	public static Client setupClient(ClientMonitor monitor, String hostIP) throws IOException{
		Client client = new Client();
		
		registerPackages(client.getKryo());
		
		new Thread(client).start();
		client.connect(5000, hostIP, TCPport);
		
		JoinRequest request = new JoinRequest();
		request.id = monitor.getCurrentPlayerID();
		client.sendTCP(request);

		return client;
	}
}
