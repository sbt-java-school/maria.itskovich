import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;

public class Gadalka {
    static int numberOfClients = 10;
    static final String YES = "yes";
    static final String NO = "no";

    public static void main(String[] args) throws IOException {
        Socket clientSocket;
        try (ServerSocket serverSocket = new ServerSocket(1234)) {

            while (numberOfClients > 0) {
                try {
                    clientSocket = serverSocket.accept();
                    ServerThread thread = new ServerThread(clientSocket);  // для нового клиента запускается отдельный поток
                    thread.start();
                    numberOfClients--;
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
