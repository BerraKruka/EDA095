package client;

import networkInfo.AckResponse;
import networkInfo.JoinAckResponse;

public class ClientMonitor {
	// if acknowledge joining 
	private String currentPlayerID;
	private int currentPlayerNumber;
	private int nPlayers;
	private String[] currentPlayers;

	private boolean newInfo;
	// if same name
	private boolean message;

	// this will get determine where and who the player will appear

	
	/**
	 * Should not be any wait in here.
	 * @param name
	 */
	public ClientMonitor(String name) {
		currentPlayerID = name;
		currentPlayerNumber = -1;
		newInfo = false;
	}

	private void extractCurrentPlayerNumber() {
		if (currentPlayerNumber < 0) {
			for (int i = 0; i < nPlayers; i++) {
				if (currentPlayers[i].equals(currentPlayerID)) {
					currentPlayerNumber = i;
					return;
				}
			}
		}
	}
	
	public  synchronized void setJoinAckRespons(JoinAckResponse response) {
		currentPlayers = response.currentPlayers;
		nPlayers = response.number;
		extractCurrentPlayerNumber();
		newInfo = true;
	}
	
	public  synchronized void setAckRespons(AckResponse response) {
		message = response.message;
		newInfo = true;
	}
	public synchronized boolean getAckRespons(){
		return message;
	}
	
	
	public synchronized boolean newInfo(){
		return newInfo;
	}
	
	
	public synchronized String getCurrentPlayerID(){
		return currentPlayerID;
	}
	
	// these three should always be call in this order.
	public synchronized int getNPlayers() throws InterruptedException{
		return nPlayers;
	}
	public synchronized int getPlayerNumber() throws InterruptedException{
		return currentPlayerNumber;
	}
	public synchronized String[] getCurrentPlayers() throws InterruptedException{
		newInfo = false;
		return currentPlayers;
	}
	
}
