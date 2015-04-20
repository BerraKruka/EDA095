package server;

import networkInfo.AckResponse;
import networkInfo.JoinAckResponse;
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
				AckResponse response = new AckResponse();
				response.message = false;;
			}else{
			JoinAckResponse response = monitor.assignID(clientID);
			System.out.println(clientID+" request to join game");
			Server server = (Server) connection.getEndPoint();
			server.sendToAllTCP(response);
			}
		}
	}
	

}