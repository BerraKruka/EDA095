package networkTest;

import networkInfo.JoinRequest;
import networkInfo.JoinResponse;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ServerListener extends Listener{
	public void received(Connection connection, Object object) {
		System.out.println("some thing still come here");
		if (object instanceof JoinRequest) {
			JoinRequest request = (JoinRequest) object;
			System.out.println(request.id);

			JoinResponse response = new JoinResponse();
//			response.id = "Thanks";
			connection.sendTCP(response);
		}
	}
}