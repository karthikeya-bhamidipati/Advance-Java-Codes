import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(6084)) {
            System.out.println("Server is waiting for two clients...");

            Socket client1 = serverSocket.accept();
            System.out.println("Client 1 connected!");

            Socket client2 = serverSocket.accept();
            System.out.println("Client 2 connected!");

            BufferedReader input1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
            PrintWriter output1 = new PrintWriter(client1.getOutputStream(), true);
            BufferedReader input2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
            PrintWriter output2 = new PrintWriter(client2.getOutputStream(), true);

            Thread client1ToClient2 = new Thread(() -> {
                try {
                    String message;
                    while ((message = input1.readLine()) != null) {
                        output2.println("Client 1: " + message);  // Forward message to Client 2
                    }
                } catch (IOException e) {
                    System.out.println("Client 1 disconnected.");
                }
            });

            Thread client2ToClient1 = new Thread(() -> {
                try {
                    String message;
                    while ((message = input2.readLine()) != null) {
                        output1.println("Client 2: " + message);  // Forward message to Client 1
                    }
                } catch (IOException e) {
                    System.out.println("Client 2 disconnected.");
                }
            });

            client1ToClient2.start();
            client2ToClient1.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
