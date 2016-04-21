package cn.amazing.server.server;

import cn.amazing.common.codec.InnerMessageCodec;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("codec", new InnerMessageCodec());
        pipeline.addLast(new NettyServerHandler());

    }
}
