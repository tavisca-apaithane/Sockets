import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientController {

    private static Socket client = null;
    private static DataInputStream dataIn = null;
    private static DataOutputStream dataOut = null;

    public ClientController() {

    }

    public static void main(String[] args)
    {
        try {
            client = new Socket("localhost", 8888);
            System.out.println("connection established...");
            dataIn = new DataInputStream(client.getInputStream());
            dataOut = new DataOutputStream(client.getOutputStream());
            ClientMessageSender clientMessageSender = new ClientMessageSender(client, dataOut);
            ClientMessageReceiver clientMessageReceiver = new ClientMessageReceiver(client, dataIn);
            clientMessageSender.start();
            clientMessageReceiver.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
