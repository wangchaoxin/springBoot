package com.wcx.springboot.demo.midware.netty.example;

import com.wcx.springboot.demo.midware.netty.example.handler.TimeServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Discards any incoming data.
 */
public class CommonServer {

    private int port;

    public CommonServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        //NioEventLoopGroup is a multithreaded event loop that handles I/O operation
        EventLoopGroup bossGroup = new NioEventLoopGroup();   //The first one, often called 'boss', accepts an incoming connection

        //The second one, often called 'worker', handles the traffic of the accepted connection once the boss accepts
        // the connection and registers the accepted connection to the worker.
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap(); //
            b.group(bossGroup, workerGroup)
                    // we specify to use the NioServerSocketChannel class which is used to instantiate a
                    // new Channel to accept incoming connections.
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() { // ChannelInitializer初始化channel
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
//                            pipeline.addLast(new DiscardServerHandler());
//                            pipeline.addLast(new EchoServerHandler());
                            pipeline.addLast(new TimeServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)          //设置tcp的一些属性
                    // option() is for the NioServerSocketChannel that accepts incoming connections.
                    // childOption() is for the Channels accepted by the parent ServerChannel,
                    // which is NioServerSocketChannel in this case.
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync(); // (7)

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }
        new CommonServer(port).run();
    }
}
