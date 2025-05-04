package com.pyip.endecoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.util.CharsetUtil;

import java.util.List;

// MessageEncoder与MessageDecoder的合体
public class MessageCoder extends MessageToMessageCodec{
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, List list) throws Exception {
        System.out.println("----Message is encoding.----");
        String str = o.toString();
        list.add(Unpooled.copiedBuffer(str, CharsetUtil.UTF_8));
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, Object o, List list) throws Exception {
        System.out.println("----Message is decoding.----");
        ByteBuf buf = (ByteBuf) o;
        list.add(buf.toString(CharsetUtil.UTF_8));
    }
}
