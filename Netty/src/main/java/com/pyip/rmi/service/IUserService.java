package com.pyip.rmi.service;

import com.pyip.rmi.pojo.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IUserService extends Remote {
    public User getUserById(int id) throws RemoteException;
}
