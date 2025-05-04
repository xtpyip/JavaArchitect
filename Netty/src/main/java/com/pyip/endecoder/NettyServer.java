package com.pyip.endecoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 1. 创建bossGroup线程组: 处理网络事件--连接事件
 * 2. 创建workerGroup线程组: 处理网络事件--读写事件
 * 3. 创建服务端启动助手
 * 4. 设置bossGroup线程组和workerGroup线程组
 * 5. 设置服务端通道实现为NIO
 * 6. 参数设置
 * 7. 创建一个通道初始化对象
 * 8. 向pipeline中添加自定义业务处理handler
 * 9. 启动服务端并绑定端口,同时将异步改为同步
 * 10. 关闭通道和关闭连接池
 */
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(16);
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
//                        socketChannel.pipeline().addLast("message decoder",new MessageDecoder());
//                        socketChannel.pipeline().addLast("message encoder",new MessageEncoder());
                        socketChannel.pipeline().addLast("message encoder and decoder", new MessageCoder());
                        socketChannel.pipeline().addLast(new NettyServerHandler());
                    }
                });
//        ChannelFuture future = bootstrap.bind(9999).sync();// 同步
        ChannelFuture future = bootstrap.bind(9999);
        // 异步
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if(channelFuture.isSuccess()){
                    System.out.println("Server bind port success");
                }else{
                    System.out.println("Server bind port failed");
                }
            }
        });
        System.out.println("Server started");
        future.channel().closeFuture().sync();
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
