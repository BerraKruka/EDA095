package networkInfo;

import com.esotericsoftware.kryo.Kryo;

public class PackageRegister {
	public static void registerPackages(Kryo kryo){
		kryo.register(PlayerAction.class);
		kryo.register(SomeRequest.class);
		kryo.register(SomeResponse.class);
	}
}
