package cn.amazing.common.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

public class InnerMessageCodec extends ByteToMessageCodec<InnerMessage> {
    @Override
    protected void encode(ChannelHandlerContext ctx, InnerMessage msg, ByteBuf out) throws Exception {
        out.writeBytes(msg.encode());
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes() < 4) {
            return;
        }
        in.markReaderIndex();
        int totalLength = in.readInt();
        if(in.readableBytes() < totalLength - 4) {
            in.resetReaderIndex();
            return;
        }
        int textLength = in.readInt();
        byte[] textBytes = new byte[textLength];
        in.readBytes(textBytes, 0, textLength);
        int binaryLength = in.readInt();
        byte[] binary = new byte[binaryLength];
        in.readBytes(binary, 0, binaryLength);

        out.add(new InnerMessage(textBytes, binary));
    }
}
