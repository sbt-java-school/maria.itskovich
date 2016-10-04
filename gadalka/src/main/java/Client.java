import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        byte[] buffer = new byte[100];
        String answer = "no";
        try (Socket socket = new Socket("localhost", 1234);
             InputStream in = socket.getInputStream();
             OutputStream out = socket.getOutputStream()) {
            System.out.println("Connection");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (answer.equals("no")) {    // до тех пор, пока клиент не угадает, его просят ввести число
                System.out.println("Введи число");
                String guess = reader.readLine();
                int guessNumber = Integer.parseInt(guess);
                out.write(guessNumber);    // отправляет свое предположение гадалке
                int readBytes = in.read(buffer);
                answer = new String(buffer, 0, readBytes);   // получает ответ от гадалки
                System.out.println(answer);
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
