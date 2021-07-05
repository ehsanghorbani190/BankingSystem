package server;

import java.net.ServerSocket;
import java.net.Socket;

import server.util.ServerHand;

public class Server {
    private ServerSocket server;
    private int port = 9090;

    Server() {
        try {
            server = new ServerSocket(port);
            System.out.println("Socket created. accepting clients now...");
            while (true) {
                Socket client = server.accept();
                System.out.println(
                        "New client connected: " + client.getInetAddress().getHostAddress() + ", handeling...");
                new Thread(new ServerHand(client)).start();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        new Server();
    }
}
