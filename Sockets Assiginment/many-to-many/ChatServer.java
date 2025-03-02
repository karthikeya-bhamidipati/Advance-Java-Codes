import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 6084;
    private static Map<String, PrintWriter> clients = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Chat server is running...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter output;
        private BufferedReader input;
        private String clientName;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream(), true);

                output.println("Enter your username:");
                clientName = input.readLine();
                System.out.println(clientName + " has joined.");

                synchronized (clients) {
                    clients.put(clientName, output);
                }

                broadcast(clientName + " has joined the chat!", null);

                String message;
                while ((message = input.readLine()) != null) {
                    if (message.startsWith("@")) {
                        sendPrivateMessage(message);
                    } else {
                        broadcast(clientName + ": " + message, clientName);
                    }
                }
            } catch (IOException e) {
                System.out.println(clientName + " disconnected.");
            } finally {
                synchronized (clients) {
                    clients.remove(clientName);
                }
                broadcast(clientName + " has left the chat.", null);
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void broadcast(String message, String sender) {
            synchronized (clients) {
                for (Map.Entry<String, PrintWriter> entry : clients.entrySet()) {
                    if (!entry.getKey().equals(sender)) {
                        entry.getValue().println(message);
                    }
                }
            }
        }

        private void sendPrivateMessage(String message) {
            int spaceIndex = message.indexOf(' ');
            if (spaceIndex == -1) {
                output.println("Invalid message format. Use @username message.");
                return;
            }

            String recipient = message.substring(1, spaceIndex);
            String privateMessage = message.substring(spaceIndex + 1);

            synchronized (clients) {
                PrintWriter recipientOutput = clients.get(recipient);
                if (recipientOutput != null) {
                    recipientOutput.println("[Private] " + clientName + ": " + privateMessage);
                } else {
                    output.println("User not found: " + recipient);
                }
            }
        }
    }
}
