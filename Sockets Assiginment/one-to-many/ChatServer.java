import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static Set<PrintWriter> clientWriters = new HashSet<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(6084)) {
            System.out.println("Chat Server started...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected!");

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                clientWriters.add(out);

                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler extends Thread {
        private Socket socket;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // Sender's writer

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Received: " + message);
                    broadcastMessage(message, out); // Pass sender's writer
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void broadcastMessage(String message, PrintWriter sender) {
            for (PrintWriter writer : clientWriters) {
                if (writer != sender) { // Send to all clients except the sender
                    writer.println(message);
                }
            }
        }

    }
}