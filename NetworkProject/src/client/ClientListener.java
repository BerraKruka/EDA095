package client;

import networkInfo.SomeResponse;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class ClientListener extends Listener{
        public void received (Connection connection, Object object) {
        	System.out.println("i receive something");
           if (object instanceof SomeResponse) {
              SomeResponse response = (SomeResponse)object;
              System.out.println(response.text);
           }
        }
     
}
