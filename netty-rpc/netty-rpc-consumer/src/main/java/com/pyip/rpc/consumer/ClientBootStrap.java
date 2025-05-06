package com.pyip.rpc.consumer;

import com.pyip.rpc.api.IUserService;
import com.pyip.rpc.consumer.proxy.RpcClientProxy;
import com.pyip.rpc.pojo.User;

public class ClientBootStrap {
    public static void main(String[] args) throws Exception {
        IUserService userService = (IUserService) RpcClientProxy.createProxy(IUserService.class);
        User user = userService.getById(1);
        System.out.println(user);
    }

}
