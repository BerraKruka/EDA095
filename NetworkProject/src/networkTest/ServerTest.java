package networkTest;

import java.io.IOException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class ServerTest {
	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.start();

		
		Kryo kryo = server.getKryo();
		kryo.register(SomeRequest.class);
		kryo.register(SomeResponse.class);
		
		server.bind(20000, 20001);
		server.addListener(new Listener() {
			public void received(Connection connection, Object object) {
				if (object instanceof SomeRequest) {
					SomeRequest request = (SomeRequest) object;
					System.out.println(request.text);

					SomeResponse response = new SomeResponse();
					response.text = "Thanks";
					connection.sendTCP(response);
				}
			}
		});
		System.out.println("everything is started");
	}

}