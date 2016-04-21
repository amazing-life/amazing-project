package cn.amazing.client.app;

import cn.amazing.client.client.NettyClient;

public class Main {

    public static void main(String[] args) throws Exception {
        String host = "127.0.0.1";
        int port = 9080;
        if(args.length==2) {
            host = args[0];
            port = Integer.parseInt(args[1]);
        }
        new NettyClient(host, port).run();
    }

}
