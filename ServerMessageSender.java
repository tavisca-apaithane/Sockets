import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

public class ServerMessageSender extends Thread {

    private Socket server = null;
    private DataOutputStream dataOut = null;
    private BufferedReader br = null;
    public ServerMessageSender(Socket server, DataOutputStream dataOut)
    {
       this.server = server;
       this.dataOut = dataOut;
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run()
    {
        while(true)
        {
            try {
                System.out.print("Server : ");
                String message = br.readLine();
                dataOut.writeUTF("Server : " + message);
                dataOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
