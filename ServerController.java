import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {

    private static ServerSocket connectionAccepter = null;
    private static Socket server = null;
    private static DataInputStream dataIn = null;
    private static DataOutputStream dataOut = null;
    public ServerController() {

    }


    public static void main(String[] args)
    {
        try {
            connectionAccepter = new ServerSocket(8888);
            System.out.println("server listening...");
            server = connectionAccepter.accept();
            System.out.println("client connected...");
            dataOut = new DataOutputStream(server.getOutputStream());
            dataIn = new DataInputStream(server.getInputStream());
            ServerMessageSender myServerMessageSender = new ServerMessageSender(server, dataOut);
            ServerMessageReceiver myServerMessageReceiver = new ServerMessageReceiver(server, dataIn);
            myServerMessageSender.start();
            myServerMessageReceiver.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
