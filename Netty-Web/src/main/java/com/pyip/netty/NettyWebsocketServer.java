package com.pyip.netty;

import com.pyip.config.NettyConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServer;
import org.springframework.stereotype.Component;


@Component
public class NettyWebsocketServer implements Runnable {
    @Autowired
    NettyConfig nettyConfig;
    @Autowired
    WebSocketChannelInit webSocketChannelInit;
    private EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    @PreDestroy
    public void shutdown() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    @Override
    public void run() {
        try {
            //1.创建服务端启动助手
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //2.设置线程组
            serverBootstrap.group(bossGroup, workerGroup);
            //3.设置参数
            serverBootstrap.channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(webSocketChannelInit);
            //4.启动
            ChannelFuture channelFuture =
                    serverBootstrap.bind(nettyConfig.getPort()).sync();
            System.out.println("--Netty服务端启动成功---");
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
