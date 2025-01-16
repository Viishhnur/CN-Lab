package TCP_reverse;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 65432;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                // Create input and output streams for communication
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String text; // this is what we are getting from client 
                while (true) {
                    text = reader.readLine();
                    if(text == null) break;
                    System.out.println("Received: " + text);
                    // Reverse the string
                    String reversedText = new StringBuilder(text).reverse().toString();
                    System.out.println("Sending: " + reversedText);// Send the reversed string back to the client
                    writer.println(reversedText);
                }
                socket.close();
                System.out.println("Client disconnected");
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
        }
    }
}
