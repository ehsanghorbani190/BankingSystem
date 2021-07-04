package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerHand implements Runnable {
    private Socket socket;

    public ServerHand(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            //TODO add logic

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
                if (in != null) {
                    in.close();
                    socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
