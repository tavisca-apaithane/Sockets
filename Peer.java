import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.RunnableFuture;

public interface Peer  {
    public void sendMessage(DataOutputStream dataOut, String message);
    public String receiveMessage(DataInputStream dataIn);
}
