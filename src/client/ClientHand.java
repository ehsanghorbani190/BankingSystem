package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import util.DataDealer;

public class ClientHand {
    private int port = 9090;
    private Socket socket;
    ObjectInputStream in = null;
    ObjectOutputStream out = null;

    public ClientHand() {
        try {
            socket = new Socket("localhost", port);
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean send(Object obj) {
        try {
            if (out == null)
                return false;
            out.writeObject(obj);
            out.flush();
            return true;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public DataDealer recieve() {
        try {
            DataDealer d = null;
            if (in != null)
                d = (DataDealer) (in.readObject());
            return d;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void close() {
        try {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
