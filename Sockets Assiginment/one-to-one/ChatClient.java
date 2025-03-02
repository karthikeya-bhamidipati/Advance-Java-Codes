import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 6084)) {
            System.out.println("Connected to the chat server!");

            BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader inputFromConsole = new BufferedReader(new InputStreamReader(System.in));

            Thread receiveThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = fromServer.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    System.out.println("Connection closed.");
                }
            });
            receiveThread.start();

            String toOtherClient;
            while ((toOtherClient = inputFromConsole.readLine()) != null) {
                toServer.println(toOtherClient);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
