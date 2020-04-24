package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Client extends Thread {
    private Socket socketClient;
    private int numero;

    public Client(Socket socket, int numero) {
        this.socketClient = socket;
        this.numero = numero;

    }

    public int getNumero() {
        return numero;
    }

    public void sendToOneUser(String msg, Socket socket, int id) {
        try {
            for (Client client : Server.clients) {
                if (client.socketClient != socket) {
                    if (client.numero == id || id == - 1) {
                        PrintWriter writer = new PrintWriter(client.socketClient.getOutputStream(), true);
                        writer.println(msg);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void broadCastMessage(String msg, Socket socket) {
        try {
            ArrayList<Client> clients = Server.clients;
            for (Client client : clients)
                if (client.socketClient != socket) {
                    PrintWriter writer = new PrintWriter(client.socketClient.getOutputStream(), true);
                    writer.println(msg);
                }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
             PrintWriter writer = new PrintWriter(socketClient.getOutputStream(), true)) {
            writer.println("Bien venu vous etes le client " + numero + " IP =" + socketClient.getRemoteSocketAddress());
            while (true) {
                String req = br.readLine();
                sendMessage(req);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String req) {
        if (req.contains("->")) {
            String[] params = req.split("->");
            int id = Integer.parseInt(params[0]);
            String msg = params[1];
            sendToOneUser(msg, socketClient, id);
        } else {
            broadCastMessage(req, socketClient);
        }
    }
}
