package com.example.mtkdem2_targel3;

public class ServerSingelton {

    private static ServerSingelton instance;
  private  String server;

    private ServerSingelton() {
        server="http://10.0.2.2:5000/api/";
    }

    public static ServerSingelton getInstance() {
        if (instance == null) {
            instance = new ServerSingelton();
        }
        return instance;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
}
