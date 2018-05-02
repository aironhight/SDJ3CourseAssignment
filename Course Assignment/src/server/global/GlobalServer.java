package server.global;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.List;

public interface GlobalServer extends Remote{
		String trackPart(String carVin) throws RemoteException;
}
