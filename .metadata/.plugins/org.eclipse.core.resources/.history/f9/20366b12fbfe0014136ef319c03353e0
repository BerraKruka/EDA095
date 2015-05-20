package server;

import networkInfo.AckResponse;
import networkInfo.ActionMessage;
import networkInfo.BombMessage;
import networkInfo.JoinAckResponse;
import networkInfo.JoinRequest;
import networkInfo.PlayerAction;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class GamePlayListener extends Listener {
	private ServerMonitor monitor;
	public  GamePlayListener(ServerMonitor monitor){
		this.monitor = monitor;
	}
	
	public void received(Connection connection, Object object) {
			ActionMessage playerAction = (ActionMessage) object;
			monitor.queueAction(playerAction);
	}
	

}