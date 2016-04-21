package cn.amazing.client.client;

import cn.amazing.common.codec.InnerMessageCodec;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

public class NettyClientInitializer extends ChannelInitializer {

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("codec", new InnerMessageCodec());
        pipeline.addLast(new NettyClientHandler());
    }
}
