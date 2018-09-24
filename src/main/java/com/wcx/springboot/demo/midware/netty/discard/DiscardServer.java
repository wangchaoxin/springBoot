package com.wcx.springboot.demo.midware.netty.discard;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Discards any incoming data.
 */
public class DiscardServer {

    private int port;

    public DiscardServer(int port) {
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
                    .channel(NioServerSocketChannel.class) // we specify to use the NioServerSocketChannel class which is used to instantiate a new Channel to accept incoming connections.
                    .childHandler(new ChannelInitializer<SocketChannel>() { // ChannelInitializer初始化channel
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new DiscardServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // option() is for the NioServerSocketChannel that accepts incoming connections. childOption() is for the Channels accepted by the parent ServerChannel, which is NioServerSocketChannel in this case.

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
        new DiscardServer(port).run();
    }
}