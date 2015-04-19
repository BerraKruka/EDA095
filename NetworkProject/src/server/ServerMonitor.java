package server;

public class ServerMonitor {
	private int nPlayer;
	
	public ServerMonitor(){
		nPlayer = 0;
	}
	public synchronized int assignID(String name){
		return nPlayer++;
	}
}
