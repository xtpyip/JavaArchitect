package com.pyip.sticking;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;



/**
 * 1. 创建线程组
 * 2. 创建客户端启动助手
 * 3. 设置线程组
 * 4. 设置客户端通道实现为NIO
 * 5. 创建一个通道初始化对象
 * 6. 向pipeline中添加自定义业务处理handler
 * 7. 启动客户端,等待连接服务端,同时将异步改为同步
 * 8. 关闭通道和关闭连接池
 */
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new NettyClientHandler());
                    }
                });
        ChannelFuture future = bootstrap.connect("127.0.0.1",9999).sync();
        future.channel().closeFuture().sync();
        group.shutdownGracefully();
    }
}
