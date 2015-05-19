package client;

import networkInfo.AckResponse;
import networkInfo.ActionMessage;
import networkInfo.BombMessage;
import networkInfo.GameStartMessage;
import networkInfo.JoinAckResponse;
import networkInfo.PlayerAction;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ClientJoinListener extends Listener{
		private ClientMonitor monitor;
		
		/**
		 * This Listener is use only to join the game
		 * @param clientMonitor
		 */
        public ClientJoinListener(ClientMonitor clientMonitor) {
        	this.monitor = clientMonitor;
        }

        
		public void received (Connection connection, Object object) {
           if (object instanceof JoinAckResponse) {
              JoinAckResponse response = (JoinAckResponse)object;              
              monitor.setJoinAckRespons(response);
           }else if(object instanceof AckResponse){
        	   AckResponse response = (AckResponse)object;              
        	   monitor.setAckRespons(response);
           }else if(object instanceof GameStartMessage) {
        	   monitor.setGameStartMessage();
           }else if(object instanceof ActionMessage) {
        	   ActionMessage serverResp = (ActionMessage)object;
        	   monitor.setActionMessage();
        	   monitor.setPlayerID(serverResp.playerID);
        	   monitor.setAction(serverResp.action);
           }else if(object instanceof BombMessage) {
        	   BombMessage bm = (BombMessage) object;
        	   monitor.setBomb();
        	   monitor.setPlayerID(bm.playerID);
           }
        }
     
}
