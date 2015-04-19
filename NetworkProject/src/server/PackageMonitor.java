package server;

public class PackageMonitor {
	public final static int PLAYER1_ID = 1;
	public final static int PLAYER2_ID = 2;
	public final static int PLAYER3_ID = 3;
	public final static int PLAYER4_ID = 4;
	private int nPlayer;
	private String[] playerName;

	
	public PackageMonitor(){
		nPlayer = 1;
		playerName = new String[4];
	}
	public synchronized int assignID(String name){
		playerName[nPlayer] = name;
		return nPlayer++;
	}
	
}
