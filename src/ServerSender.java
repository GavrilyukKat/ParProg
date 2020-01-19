import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Thread.sleep;

public class ServerSender implements Runnable {

    public ServerReceiver serverReceiver;

    public ServerSender(ServerReceiver serverReceiver) {
        this.serverReceiver = serverReceiver;
    }

    @Override
    public void run() {
        while(true) {
            List<Socket> sockets = new ArrayList<>();
            sockets.addAll(serverReceiver.clientsSockets);
            for (int i = 0; i < sockets.size(); i++) {
                try {
                    PrintWriter out = new PrintWriter(sockets.get(i).getOutputStream(), true);
                    out.println(new Date().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //sending time every 30 seconds
            try {
                sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
