package networkInfo;

import com.esotericsoftware.kryo.Kryo;

public class NetworkUtils {
	public final static int TCPport= 20000;
	public final static int UDPport= 20001;
	public static void registerPackages(Kryo kryo){
		kryo.register(JoinRequest.class);
		kryo.register(JoinResponse.class);
		kryo.register(PlayerAction.class);
	}
}
