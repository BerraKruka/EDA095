package networkTest;

import java.io.IOException;

import networkInfo.JoinRequest;
import networkInfo.NetworkUtils;
import server.JoinGameListener;
import server.ServerMonitor;
import client.ClientJoinListener;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Server;

public class TestBothServerAndClient {
	public static void startServer() throws IOException{	
		ServerMonitor monitor = new ServerMonitor();
		Server server = new Server();
		server.start();
	
		NetworkUtils.registerPackages(server.getKryo());
		
		server.bind(NetworkUtils.TCPport,NetworkUtils.UDPport);
		server.addListener(new JoinGameListener(monitor));
	}
	
	
	
	public static void startClient(String name) throws IOException{
		Client client = new Client();
		new Thread(client).start();
		
		NetworkUtils.registerPackages(client.getKryo());
		
		client.connect(5000, "localhost", NetworkUtils.TCPport,NetworkUtils.UDPport);
		
		JoinRequest request = new JoinRequest();
		request.id = name;
		client.sendTCP(request);
		client.addListener(new ClientJoinListener(null));
	}
	public static void main(String[] args) throws IOException{
		startServer();
		startClient("Tuan");
	}
}
