package com.pyip.chat;
import java.util.ArrayList;
import java.util.List;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyChatServerHandler extends SimpleChannelInboundHandler<String> {
    public static List<Channel> channelList = new ArrayList<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelList.add(channel);
        System.out.println("[Server]:" +
                channel.remoteAddress().toString().substring(1) + "在线.");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelList.remove(channel);
        System.out.println("[Server]:" +
                channel.remoteAddress().toString().substring(1) + "下线.");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        Channel channel = channelHandlerContext.channel();
        for(Channel channel1 : channelList){
            if(channel1 != channel){
                channel1.writeAndFlush("[" +
                        channel.remoteAddress().toString().substring(1)
                        + "] say :" + s);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        Channel channel = ctx.channel();
//移除集合
        channelList.remove(channel);
        System.out.println("[Server]:" +
                channel.remoteAddress().toString().substring(1) + "has something went wrong!.");
    }
}
