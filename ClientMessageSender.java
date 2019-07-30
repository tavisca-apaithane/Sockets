import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientMessageSender extends  Thread{

    private Socket client = null;
    private DataOutputStream dataOut = null;
    private BufferedReader br = null;
    public ClientMessageSender(Socket client, DataOutputStream dataOut)
    {
        this.client = client;
        this.dataOut = dataOut;
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run()
    {
        while(true)
        {
            try {
                System.out.print("Client : ");
                String message = br.readLine();
                dataOut.writeUTF("Client : " + message);
                dataOut.flush();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
