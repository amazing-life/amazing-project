package cn.amazing.common.codec;

import cn.amazing.common.dump.DumpObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class InnerMessage extends DumpObject {
    private static final String UTF8 = "UTF-8";

    private int textLength;
    private String text;
    private byte[] textBytes;
    private int binaryLength;
    private byte[] binary;

    public InnerMessage(String text, byte[] binary) throws Exception {
        this.text = text == null ? "" : text;
        this.textBytes = this.text.getBytes(UTF8);
        this.textLength = this.textBytes.length;

        this.binary = binary == null ? new byte[0] : binary;
        this.binaryLength = this.binary.length;
    }

    public InnerMessage(byte[] textBytes, byte[] binary) throws Exception {
        this.textBytes = textBytes == null ? new byte[0] : textBytes;
        this.text = new String(this.textBytes, UTF8);
        this.textLength = this.textBytes.length;

        this.binary = binary == null ? new byte[0] : binary;
        this.binaryLength = this.binary.length;
    }

    public ByteBuf encode() {
        int totalLength = getTotalLength();
        ByteBuf buf = Unpooled.buffer(totalLength);
        buf.writeInt(totalLength);
        buf.writeInt(this.textLength);
        buf.writeBytes(this.textBytes);
        buf.writeInt(this.binaryLength);
        buf.writeBytes(this.binary);
        return buf;
    }

    public int getTotalLength() {
        return textLength + binaryLength + 4 + 4 + 4;
    }

    public int getTextLength() {
        return textLength;
    }

    public String getText() {
        return text;
    }

    public int getBinaryLength() {
        return binaryLength;
    }

    public byte[] getBinary() {
        return binary;
    }

    public byte[] getTextBytes() {
        return textBytes;
    }
}
