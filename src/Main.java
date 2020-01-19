public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        ServerReceiver serverReceiver = new ServerReceiver();
        serverReceiver.clientIdsToSendDate.add("abc");
        ServerSender serverSender = new ServerSender(serverReceiver);
        Client client = new Client("abc");
        new Thread(serverReceiver).start();
        new Thread(serverSender).start();
        new Thread(client).start();
    }
}
