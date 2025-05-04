package com.pyip.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyChatServer {
    private int port;
    public NettyChatServer(int port) {
        this.port = port;
    }
    public void run(){
        EventLoopGroup bossGroup = null;
        EventLoopGroup workerGroup = null;
        try{
            bossGroup = new NioEventLoopGroup(1);
            workerGroup = new NioEventLoopGroup();
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new StringDecoder());
                            socketChannel.pipeline().addLast(new StringEncoder());
                            socketChannel.pipeline().addLast(new NettyChatServerHandler());
                        }
                    });
            ChannelFuture future = bootstrap.bind(port);
            future.addListener(new ChannelFutureListener() {
               @Override
               public void operationComplete(ChannelFuture channelFuture) throws Exception {
                   if (channelFuture.isSuccess()){
                       System.out.println("Server started on port " + port);
                   }else{
                       System.out.println("Server failed to start on port " + port);
                   }
               }
            });
            System.out.println("Chat Server started " + port);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
    public static void main(String[] args) {
        new NettyChatServer(9998).run();
    }
}
