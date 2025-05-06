package com.pyip.rpc.provider;

import com.pyip.rpc.provider.server.RpcServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerBootstrapApplication implements CommandLineRunner{
    @Autowired
    RpcServer rpcServer;
    public static void main(String[] args) {
        SpringApplication.run(ServerBootstrapApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                rpcServer.startServer("127.0.0.1", 8899);
            }
        }).start();
    }

}
