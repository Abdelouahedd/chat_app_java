package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {
    Socket socketClient;
    int numero;

    public Client(Socket socket, int numero) {
        this.socketClient = socket;
        this.numero = numero;
    }


    public void broadCastMessage(String msg, Socket socket) {
        try {
            for (Client client : Server.clients) {
                if (client.socketClient != socket) {
                    PrintWriter writer = new PrintWriter(client.socketClient.getOutputStream(), true);
                    writer.println(msg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void run() {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                PrintWriter writer = new PrintWriter(socketClient.getOutputStream(), true)) {

            writer.println("Bien venu vous etes le client " + numero + " IP =" + socketClient.getRemoteSocketAddress());
            while (true) {
                String req = br.readLine();
                broadCastMessage(req, socketClient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
