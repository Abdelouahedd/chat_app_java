package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {
    private Socket socket;
    private int numero;

    public Client(Socket socket, int numero) {
        this.socket = socket;
        this.numero = numero;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)
        ) {
            writer.println("Bien venu vous etes le client " + numero + " IP =" + socket.getRemoteSocketAddress());
            while (true) {
                String req = br.readLine();
                String res = "Lenght " + req.length();
                writer.println(res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
