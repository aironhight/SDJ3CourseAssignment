package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.List;

import models.Car;

public interface Server extends Remote {
	String trackPart(String carVin) throws RemoteException;
	boolean registerCar(Car car) throws RemoteException;
}
