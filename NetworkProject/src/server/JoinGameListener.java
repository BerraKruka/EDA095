package server;

import networkInfo.JoinRequest;
import networkInfo.JoinResponse;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class JoinGameListener extends Listener {
	private ServerMonitor monitor;
	private JoinResponse response;
	public  JoinGameListener(ServerMonitor monitor){
		this.monitor = monitor;
		response = new JoinResponse();
	}
	
	public void received(Connection connection, Object object) {
		if (object instanceof JoinRequest) {
			JoinRequest request = (JoinRequest) object;
			String clientName = request.id;
			
			int playerNumber = monitor.assignID(request.id);
			response = new JoinResponse();
			response.number = playerNumber;
			System.out.println("Connection to client"+clientName+"has been made"
							+ "	\n  player number is :"+ playerNumber);
			connection.sendTCP(response);
		}
	}
	

}