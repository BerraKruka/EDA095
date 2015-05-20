package server;

import com.esotericsoftware.kryonet.Server;

public class ServerSender extends Thread{
	private ServerMonitor monitor;
	private Server server;
	
	public ServerSender(ServerMonitor monitor, Server server) {
		this.monitor = monitor;
		this.server = server;
	}
	
	
	public void run(){
		while(!Thread.interrupted()){
			try {
				server.sendToAllTCP(monitor.getActionMessage());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
