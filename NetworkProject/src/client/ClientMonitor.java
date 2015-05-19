package client;

import entity.GameEntity;
import entity.Player;
import entity.Pos;
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
	
	private boolean start;
	
	private boolean actionMsg;
	
	private String action;
	private int playerID;
	
	private boolean bomb;
	
	private Pos bombPos;
	

	// this will get determine where and who the player will appear

	
	/**
	 * Should not be any wait in here.
	 * @param name
	 */
	public ClientMonitor(String name) {
		currentPlayerID = name;
		currentPlayerNumber = -1;
		newInfo = false;
		start = false;
		actionMsg = false;
		bomb = false;
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
	
	public synchronized void setGameStartMessage() {
		start = true;
		newInfo = true;	
	}
	
	public synchronized void setActionMessage() {
		actionMsg = true;
		newInfo = true;		
	}
	
	public synchronized boolean isAction() {
		return actionMsg;
	}
	
	public synchronized boolean isStart() {
		return start;
	}
	
	public synchronized void actionFinished() {
		actionMsg = false;
		bomb = false;
	}
	
	
	public synchronized void setAction(String action) {
		this.action = action;
	}
	

	public synchronized String getAction() {
		return action;
	}
	
	public synchronized int getPlayerID(){
		return playerID;
	}
	
	
	public synchronized void setPlayerID(int id) {
		this.playerID = id;
	}
	
	public synchronized void setBomb() {
		bomb = true;
	}
	
	public synchronized boolean isBomb() {
		return bomb;
	}
	
	public synchronized void setBombPos(Pos pos) {
		bombPos = pos;
	}
	
	public synchronized Pos getBombPos() {
		return bombPos;
	}
	
}
