package server.local;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.List;

public interface LocalServerInterface extends Remote {
	void registerCar() throws RemoteException;
}