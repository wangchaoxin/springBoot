package com.wcx.springboot.demo.midware.netty.example.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * This method is called with the received message, whenever new data is received from a client
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //1.write的时候自动release
        //2.ctx.write(Object) does not make the message written out to the wire. It is buffered internally,
        // and then flushed out to the wire by ctx.flush(). Alternatively, you could call ctx.writeAndFlush(msg)
        // for brevity
        ctx.writeAndFlush(msg);
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
