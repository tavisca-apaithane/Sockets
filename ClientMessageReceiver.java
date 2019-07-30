import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientMessageReceiver extends Thread {

    private Socket client = null;
    private DataInputStream dataIn = null;
    private BufferedReader br = null;
    public ClientMessageReceiver(Socket client, DataInputStream dataIn)
    {
        this.client = client;
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
