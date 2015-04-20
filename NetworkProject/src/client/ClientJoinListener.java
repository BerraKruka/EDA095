package client;

import networkInfo.AckResponse;
import networkInfo.JoinAckResponse;
import networkInfo.NackResponse;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ClientJoinListener extends Listener{
		private ClientMonitor monitor;
        public ClientJoinListener(ClientMonitor clientMonitor) {
        	this.monitor = clientMonitor;
        }

		public void received (Connection connection, Object object) {
           if (object instanceof JoinAckResponse) {
              JoinAckResponse response = (JoinAckResponse)object;              
              monitor.setJoinAckRespons(response);
           }else if(object instanceof AckResponse){
        	   AckResponse response = (AckResponse)object;              
        	   

        	   monitor.setAckResponse(response);
           }
        }
     
}
