package client;

import networkInfo.AckResponse;
import networkInfo.ActionMessage;
import networkInfo.BombMessage;
import networkInfo.GameStartMessage;
import networkInfo.JoinAckResponse;
import networkInfo.PlayerAction;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ClientGameListener extends Listener{
		private ClientMonitor monitor;
		
		/**
		 * This Listener is use only to join the game
		 * @param clientMonitor
		 */
        public ClientGameListener(ClientMonitor clientMonitor) {
        	this.monitor = clientMonitor;
        }

        
		public void received (Connection connection, Object object) {
        	   ActionMessage serverResp = (ActionMessage)object;
        	   monitor.setActionMessage();
        	   monitor.setPlayerID(serverResp.playerID);
        	   monitor.setAction(serverResp.action);
        }
     
}
