package server.local;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.List;

import models.Car;

public interface LocalServerInterface extends Remote {
	boolean registerCar(Car car) throws RemoteException;
}