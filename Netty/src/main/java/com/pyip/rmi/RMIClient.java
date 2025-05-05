package com.pyip.rmi;

import com.pyip.rmi.pojo.User;
import com.pyip.rmi.service.IUserService;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
    public static void main(String[] args) {
        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry("127.0.0.1", 9990);
            IUserService userService = (IUserService) registry.lookup("userService");
            User user = userService.getUserById(1);
            System.out.println(user);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        }

    }
}
