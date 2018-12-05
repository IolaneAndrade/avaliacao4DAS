/**********************
*Uma interface comum para cliente e servidor.
* **********************/

package broadcast;

import java.rmi.RemoteException;

public interface IChat extends java.rmi.Remote {
	    boolean checkClientCredintials(IChat ci,String name, String pass) throws RemoteException;
	    void broadcastMessage(String name,String message) throws RemoteException;
	    void sendMessageToClient(String message) throws RemoteException;
}
