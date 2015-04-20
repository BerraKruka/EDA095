package networkTest;

import java.io.IOException;

import networkInfo.JoinRequest;
import networkInfo.JoinAckResponse;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class TestOriginalServerAndClient {
	public static void kryoConfig(Kryo k) {
		k.register(JoinRequest.class);
		k.register(JoinAckResponse.class);
	}
	

	public static void startServer() throws IOException {
		Server server = new Server();
		new Thread(server).start();

		kryoConfig(server.getKryo());

		server.bind(20000, 20001);
		server.addListener(new ServerListener());
		System.out.println("server is running");

	}

	public static void startClient(String name) throws IOException {
		Client client = new Client();
		new Thread(client).start();

		kryoConfig(client.getKryo());

		client.connect(5000, "localhost", 20000, 20001);

		JoinRequest request = new JoinRequest();
		request.id = "tuan";
		client.sendTCP(request);

		client.addListener(new Listener() {
			public void received(Connection connection, Object object) {
				System.out.println(object.toString());
				if (object instanceof JoinAckResponse) {
					JoinAckResponse response = (JoinAckResponse) object;
					System.out.println(response.number);
				}
			}
		});
	}

	public static void main(String[] args) throws IOException,
			InterruptedException {
		startServer();
		startClient("tuan");

	}

}

