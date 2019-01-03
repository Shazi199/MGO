package mgo.server;

import com.jfinal.server.undertow.UndertowServer;

import mgo.config.MainConfig;

public class Server {
    public static void main(String[] args) {
        UndertowServer.start(MainConfig.class);
    }
}
