package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private int nbrClient;

    public Server() {
        System.out.println("Server started");
    }

    public static void main(String[] args) {
        new Server().start();
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            while (true) {
                Socket socket = serverSocket.accept();
                ++ nbrClient;
                new Client(socket, nbrClient).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
