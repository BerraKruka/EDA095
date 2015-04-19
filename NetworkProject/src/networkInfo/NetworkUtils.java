package networkInfo;

import com.esotericsoftware.kryo.Kryo;

public class NetworkUtils {
	public static void registerPackages(Kryo kryo){
		kryo.register(JoinRequest.class);
		kryo.register(JoinResponse.class);
		kryo.register(PlayerAction.class);
	}
}
