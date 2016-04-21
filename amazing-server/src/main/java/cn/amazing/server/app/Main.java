package cn.amazing.server.app;

import cn.amazing.server.server.NettyServer;

public class Main {
    public static void main(String[] args) throws Exception {
        int port = 9080;
        if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        }
        new NettyServer(port).run();
    }
}
