import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ServerReceiver implements Runnable {

    public List<String> clientIdsToSendDate = new ArrayList<>();
    public List<Socket> clientsSockets = new ArrayList<>();

    @Override
    public void run() {
        try {
            ServerSocket listener = new ServerSocket(59090);
            System.out.println("The date server is running...");
            while (true) {
                Socket socket = listener.accept();
                Scanner in = new Scanner(socket.getInputStream());
                String clientId = in.nextLine();
                System.out.println("Client id connected: " + clientId);
                if (clientIdsToSendDate.contains(clientId)) {
                    clientsSockets.add(socket);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
