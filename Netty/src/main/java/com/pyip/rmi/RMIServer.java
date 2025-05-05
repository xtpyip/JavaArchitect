package com.pyip.rmi;

import com.pyip.rmi.service.IUserService;
import com.pyip.rmi.service.impl.UserServiceImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;

public class RMIServer {
    public static void main(String[] args) {
        Registry registry = null;
        try {
            registry = LocateRegistry.createRegistry(9990);
            IUserService userService = new UserServiceImpl();
            registry.rebind("userService",userService);
            System.out.println("Server started");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

    }
}
