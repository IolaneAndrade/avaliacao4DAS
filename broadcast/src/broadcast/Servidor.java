/*********************
 * Implementa o seridor rmi
 * *******************/

package broadcast;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.net.MalformedURLException;
import java.util.ArrayList;


public class Servidor extends UnicastRemoteObject implements IChat {
	    
	private static final long serialVersionUID = 1L;
	private String clientName [] = {"José","Maria","João","Madalena","Baruc"};
	private String clientPass [] = {"12345","123456","12345","123456","12345"};
	private ArrayList<IChat> clientList;
	 
	protected Servidor() throws RemoteException {
	    clientList = new ArrayList<IChat>();
	}

	public synchronized boolean checkClientCredintials(IChat chatinterface,String clientname,String password) throws RemoteException {
	    boolean chkLog = false;  
	    for(int i=0; i<clientName.length; i++) {
	        if(clientName[i].equals(clientname) && clientPass[i].equals(password)) {
	            chkLog = true;
	            this.clientList.add(chatinterface);
	        }
	    }
	    return chkLog;
	}
	 
	public synchronized void broadcastMessage(String clientname , String message) throws RemoteException {
	    for(int i=0; i<clientList.size(); i++) {
	        clientList.get(i).sendMessageToClient(clientname.toUpperCase() + " : "+ message);
	    }
	}
	 
	public synchronized void sendMessageToClient(String message) throws RemoteException{}

	public static void main(String[] arg) throws RemoteException, MalformedURLException {
	    Naming.rebind("RMIServer", new Servidor());
	}
	 
}
