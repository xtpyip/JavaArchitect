package com.pyip.rmi.service.impl;

import com.pyip.rmi.pojo.User;
import com.pyip.rmi.service.IUserService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl extends UnicastRemoteObject implements IUserService {
    private Map<Integer,User> userMap = new HashMap<>();
    public UserServiceImpl() throws RemoteException {
        super();
        userMap.put(1,new User(1,"李四"));
        userMap.put(2,new User(2,"王二"));
    }

    @Override
    public User getUserById(int id) throws RemoteException {
        return userMap.get(id);
    }
}
