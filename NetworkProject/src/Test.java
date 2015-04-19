import java.io.IOException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;


public class Test {
	public static void main(String[] args) throws IOException{
		Server server = new Server();
		Kryo kryo = server.getKryo();
		kryo.register(float[].class);
		server.start();
		server.bind(2300, 2301);
		server.addListener(new Listener() {
		    public void received(Connection connection, Object object)
		    {
		        if(object instanceof float[])
		        {
		            float[] array = (float[])object;
		        } 
		    }
		});

		Client client = new Client();
		kryo = client.getKryo();
		kryo.register(float[].class);
		client.addListener(new Listener() {
		    public void connected(Connection connection)
		    {
		        connection.sendTCP(new float[] {5.0f, 6.0f, 7.0f, 8.0f});
		    }
		});
		client.start();
		client.connect(5000, "localhost", 2300, 2301);

	}
}
