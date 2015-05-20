package server;

import java.util.LinkedList;

import networkInfo.ActionMessage;
import networkInfo.GameStartMessage;
import networkInfo.JoinAckResponse;

public class ServerMonitor {
	private JoinAckResponse response;
	private GameStartMessage start;
	private LinkedList<ActionMessage> actions;
	public ServerMonitor(){
		start = new GameStartMessage();
		response = new JoinAckResponse();
		actions = new LinkedList();
		response.currentPlayers = new String[4];
		response.number = 0;
	}
	
	public synchronized GameStartMessage startGame() {
		return start;		
	}
	
	public synchronized JoinAckResponse assignID(String ID){
		response.currentPlayers[response.number++]= ID;
		return response;
	}
	public synchronized boolean duplicatePlayerID(String ID){
		for(int i = 0; i < response.number;i++){
			if (response.currentPlayers[i].equals(ID)){
				return true;
			}
		}
		return false;
	}

	public synchronized void queueAction(ActionMessage playerAction) {
			actions.add(playerAction);
			notifyAll();
	}

	public synchronized Object getActionMessage() throws InterruptedException {
		while(actions.isEmpty()) wait();
		return actions.pop();
	}
}
