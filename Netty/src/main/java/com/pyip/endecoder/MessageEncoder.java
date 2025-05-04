package com.pyip.endecoder;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;
import java.util.List;

public class MessageEncoder extends MessageToMessageEncoder {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, List list) throws Exception {
        System.out.println("Message is encoded");
        String s = (String)o;
        list.add(Unpooled.copiedBuffer(s, CharsetUtil.UTF_8));
    }
}

