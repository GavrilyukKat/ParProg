import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Client implements Runnable {

    private String clientId;

    public Client(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public void run() {
        try {
            System.out.println("Starting client");
            Socket socket = new Socket("127.0.0.1", 59090);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(clientId);
            Scanner scanner = new Scanner(socket.getInputStream());
            while (true) {
                if (scanner.hasNext()) System.out.println("Server response: " + scanner.nextLine());
                sleep(1000L);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
