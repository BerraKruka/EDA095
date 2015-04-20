package server;

import networkInfo.JoinAckResponse;
import networkInfo.NackResponse;
import networkInfo.JoinRequest;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class JoinGameListener extends Listener {
	private ServerMonitor monitor;
	public  JoinGameListener(ServerMonitor monitor){
		this.monitor = monitor;
	}
	
	public void received(Connection connection, Object object) {
		if (object instanceof JoinRequest) {
			JoinRequest request = (JoinRequest) object;
			String clientID = request.id;
			
			if(monitor.duplicatePlayerID(clientID)){
			NackResponse response = new NackResponse();
			response.message = "Your name is not unique.";
			}else{
			JoinAckResponse response = monitor.assignID(clientID);
			System.out.println(clientID+" request to join game");
			Server server = (Server) connection.getEndPoint();
			server.sendToAllTCP(response);
			}
		}
	}
	

}