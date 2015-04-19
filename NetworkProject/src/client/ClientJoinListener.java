package client;

import networkInfo.JoinResponse;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ClientJoinListener extends Listener{
		private ClientMonitor monitor;
        public ClientJoinListener(ClientMonitor clientMonitor) {
        	this.monitor = clientMonitor;
        }

		public void received (Connection connection, Object object) {
           if (object instanceof JoinResponse) {
              JoinResponse response = (JoinResponse)object;
              System.out.println(response.number);
              monitor.setNumber(response.number);
           }
        }
     
}
