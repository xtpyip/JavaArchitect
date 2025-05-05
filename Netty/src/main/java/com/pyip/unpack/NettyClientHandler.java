package com.pyip.unpack;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class NettyClientHandler implements ChannelInboundHandler {
    /**
     * 通道就绪事件
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello, I am Netty Client",CharsetUtil.UTF_8));// 同步
//        // 异步
//        ChannelFuture channelFuture = ctx.writeAndFlush(Unpooled.copiedBuffer("Hello, I am Netty Client", CharsetUtil.UTF_8));
//        channelFuture.addListener(new ChannelFutureListener() {
//            @Override
//            public void operationComplete(ChannelFuture channelFuture) throws Exception {
//                if(channelFuture.isSuccess()){
//                    System.out.println("data is ok");
//                }else{
//                    System.out.println("data is failed");
//                }
//            }
//        });
        for (int i = 0; i < 10; i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer("Hello, I am Netty Client "+i+"$", CharsetUtil.UTF_8));
        }
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws
            Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("Server send message: " +
                byteBuf.toString(CharsetUtil.UTF_8));
    }


    @Override
    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void channelUnregistered(ChannelHandlerContext channelHandlerContext) throws Exception {

    }


    @Override
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {

    }



    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {

    }

    @Override
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {

    }
}
