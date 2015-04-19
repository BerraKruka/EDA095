package client;

public class ClientMonitor {
	private String name;
	
	private String[] playerName;
	private boolean notYetAssigned;

	// this will get determine where and who the player will appear
	private int number;
	
	public ClientMonitor(){
		notYetAssigned = true;
	}
	public synchronized void setName(String name){
		this.name = name;
	}
	
	
	
	
	public synchronized void setNumber(int number){
		this.number = number;
		notYetAssigned = false;
		notifyAll();
	}
	public synchronized int getNumber() throws InterruptedException{
		while(notYetAssigned) wait();
		return number;
	}
	
}
