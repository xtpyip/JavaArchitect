package com.pyip.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

public class NettyChatClient {
    private String ip;
    private int port;
    public NettyChatClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
    public void run() {
        EventLoopGroup group = null;
        try {
            group = new NioEventLoopGroup();
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new StringDecoder());
                            socketChannel.pipeline().addLast(new StringEncoder());
                            socketChannel.pipeline().addLast(new NettyChatClientHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect(ip, port).sync();
            Channel channel = future.channel();
            System.out.println("-------" +
                    channel.localAddress().toString().substring(1) + "--------");
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String msg = scanner.nextLine();
//向服务端发送消息
                channel.writeAndFlush(msg);
            }
//8. 关闭通道和关闭连接池
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            group.shutdownGracefully();
        }
    }
    public static void main(String[] args) {
        new NettyChatClient("127.0.0.1", 9998).run();
    }
}
