package server;

import networkInfo.PlayerAction;
import networkInfo.JoinRequest;
import networkInfo.JoinResponse;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class JoinGameListener extends Listener {
	private PackageMonitor monitor;
	public  JoinGameListener(PackageMonitor monitor){
		this.monitor = monitor;
		System.out.println("listener create");
	}
	
	@Override
	public void received(Connection connection, Object object) {
		System.out.println("server received some packange ?");
		if (object instanceof JoinRequest) {
			JoinRequest request = (JoinRequest) object;
			System.out.println(request.id);
			
			JoinResponse response = new JoinResponse();
			response.text = "Thanks";
			connection.sendTCP(response);
		}
	}
	

}