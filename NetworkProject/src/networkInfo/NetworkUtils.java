package networkInfo;

import com.esotericsoftware.kryo.Kryo;

public class NetworkUtils {
	public final static int PLAYER1_ID = 0;
	public final static int PLAYER2_ID = 1;
	public final static int PLAYER3_ID = 2;
	public final static int PLAYER4_ID = 3;
	
	public final static int TCPport= 20000;
	public final static int UDPport= 20001;
	
	public static void registerPackages(Kryo kryo){
		kryo.register(JoinRequest.class);
		kryo.register(JoinResponse.class);
		kryo.register(PlayerAction.class);
	}
}
