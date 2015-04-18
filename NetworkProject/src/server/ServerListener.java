package server;

import networkInfo.PlayerAction;
import networkInfo.SomeRequest;
import networkInfo.SomeResponse;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ServerListener extends Listener {
	// public void received(Connection connection, Object object){
	// PlayerAction request = (PlayerAction)object;
	// System.out.println(request.text);
	//
	// PlayerAction response = new PlayerAction();
	// response.text = "Thanks";
	// connection.sendTCP(response);
	// }
	public void received(Connection connection, Object object) {
		if (object instanceof SomeRequest) {
			SomeRequest request = (SomeRequest) object;
			System.out.println(request.text);

			SomeResponse response = new SomeResponse();
			response.text = "Thanks";
			connection.sendTCP(response);
			System.out.println("i send the response away");
		}
	}

}