import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerThread extends Thread {
    Socket clientSocket;
    public ServerThread (Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    @Override
    public void run() {
        int number = (int) (Math.random() * 10);
        System.out.println("Я загадала число от 0 до 9");
        try (OutputStream out = clientSocket.getOutputStream();
             InputStream in = clientSocket.getInputStream()) {
            int guess = in.read();  // получаем предположение от клиента
            while (guess != number) {  // если его предположение не совпало с загаданным числом, отправляем ему отрицательный ответ
                out.write(Gadalka.NO.getBytes());
                guess = in.read(); // получаем предположение от клиента
            }
            out.write(Gadalka.YES.getBytes());
            System.out.println("Угадал");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
