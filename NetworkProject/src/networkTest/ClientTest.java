package networkTest;

import java.io.IOException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ClientTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		Client client = new Client();
		new Thread(client).start();

		Kryo kryo = client.getKryo();
		kryo.register(SomeRequest.class);
		kryo.register(SomeResponse.class);

		client.connect(5000, "localhost", 20000, 20001);

		SomeRequest request = new SomeRequest();
		request.text = "Here is the request";
		client.sendTCP(request);

		client.addListener(new Listener() {
			public void received(Connection connection, Object object) {
				if (object instanceof SomeResponse) {
					SomeResponse response = (SomeResponse) object;
					System.out.println(response.text);
				}
			}
		});
		Thread.sleep(3000);
	}
}
