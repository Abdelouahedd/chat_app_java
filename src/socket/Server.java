package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {
    static ArrayList<Client> clients = new ArrayList<>();
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
                Client client = new Client(socket, nbrClient);
                clients.add(client);
                System.out.println(client.numero + " added to list");
                client.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
