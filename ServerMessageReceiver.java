import java.io.*;
import java.net.Socket;

public class ServerMessageReceiver extends Thread {

    private Socket server = null;
    private DataInputStream dataIn = null;
    private BufferedReader br = null;
    public ServerMessageReceiver(Socket server, DataInputStream dataIn)
    {
        this.server = server;
        this.dataIn = dataIn;
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run()
    {
        while(true) {
            try {
                String message = dataIn.readUTF();
                System.out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
