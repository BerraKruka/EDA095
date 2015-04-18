package server;
import java.io.IOException;

import networkInfo.NetworkUtils;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;


public class GameServer{
	public static void main(String[] args) throws IOException{
		Server server = new Server();
		server.addListener(new ServerListener());
		NetworkUtils.registerPackages(server.getKryo());
		server.start();
		//        tcp port udp port
		server.bind(54555,54777);
		
		}

	}
