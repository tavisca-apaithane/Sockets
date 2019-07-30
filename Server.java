import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Peer {
    private ServerSocket connectionAccepter = null;
    private Socket server = null;
    private DataInputStream dataIn = null;
    private DataOutputStream dataOut = null;
    private String message="";
    public Server()
    {
        try {
            connectionAccepter = new ServerSocket(8888);
            System.out.println("server listening...");
            server = connectionAccepter.accept();
            System.out.println("client connected...");
            dataIn = new DataInputStream(server.getInputStream());
            dataOut = new DataOutputStream(server.getOutputStream());
            startConversation();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void startConversation() {
        while(true) {
            System.out.println("Client : " + receiveMessage(dataIn));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String message = null;
            try {
                System.out.print("Server : ");
                message = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            sendMessage(dataOut, message);
        }
    }


    @Override
    public void sendMessage(DataOutputStream dataOut, String message){
        try {
            dataOut.writeUTF(message);
            dataOut.flush();
        } catch (IOException e) {
            System.out.println("error here ********************");
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
