package networkInfo;

import com.esotericsoftware.kryo.Kryo;

public class NetworkUtils {
	public static void registerPackages(Kryo kryo){
		kryo.register(PlayerAction.class);
		kryo.register(SomeRequest.class);
		kryo.register(SomeResponse.class);
	}
}
