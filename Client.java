import java.io.*;
import java.net.Socket;

public class Client implements Peer {
    private Socket client = null;
    private DataInputStream dataIn = null;
    private DataOutputStream dataOut = null;
    private String message="";
    public Client()
    {
        try {
            client = new Socket("localhost", 8888);
            System.out.println("client connected...");
            dataIn = new DataInputStream(client.getInputStream());
            dataOut = new DataOutputStream(client.getOutputStream());
            startConversation();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void startConversation() {
        while(true) {
            System.out.print("Client : ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String message = null;
            try {
                message = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            sendMessage(dataOut, message);
            System.out.println("Server : "+receiveMessage(dataIn));
        }
    }
    @Override
    public void sendMessage(DataOutputStream dataOut, String message){
        try {
            dataOut.writeUTF(message);
            dataOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String receiveMessage(DataInputStream dataIn){

        String message = null;
        try {
            message = dataIn.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }
}

