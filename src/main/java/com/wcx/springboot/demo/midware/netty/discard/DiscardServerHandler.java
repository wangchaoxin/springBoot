package com.wcx.springboot.demo.midware.netty.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * This method is called with the received message, whenever new data is received from a client
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // Discard the received data silently.
        //bytebuf引用计数减一，ByteBuf is a reference-counted object which has to be released explicitly via the release() method.
        // Please keep in mind that it is the handler's responsibility to release any reference-counted object passed to the handler.
        ((ByteBuf) msg).release();


        //另一种写法
//        ReferenceCountUtil.release(msg);
    }

    /**
     * 异常发送时代码
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
