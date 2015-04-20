package client;

import networkInfo.AckResponse;
import networkInfo.JoinAckResponse;

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
           }
        }
     
}
