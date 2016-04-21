package cn.amazing.client.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    private String host;
    private int port;

    public NettyClient(String host, int port) {
        if(host==null) {
            throw new IllegalArgumentException("host is null");
        }
        if(port==0) {
            throw new IllegalArgumentException("port is 0");
        }
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception {
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new NettyClientInitializer())
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.SO_BACKLOG, 128);

            ChannelFuture future = bootstrap.connect(host, port).sync();
            Channel channel = future.channel();

            channel.closeFuture().sync();
        } finally {
            workGroup.shutdownGracefully();
        }

    }
}
