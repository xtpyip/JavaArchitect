package com.pyip;

import com.pyip.netty.NettyWebsocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NettySpringbootApplication implements CommandLineRunner {

    @Autowired
    NettyWebsocketServer nettyWebsocketServer;

    public static void main(String[] args) {
        SpringApplication.run(NettySpringbootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        new Thread(nettyWebsocketServer).start();
    }
}
