package jsrv.app;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TCPServer {
    public static void main(String args[]) {
        try {
            int serverPort = 8080;
            
            boolean correct = true;
            Random random = new Random();
            int number, number2, counter=0;

            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true) {
                Socket clientSocket = listenSocket.accept();
                System.out.println("Server is up...");
                // instead of a Scanner --> DataInputStream
                DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                
                DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
                out.writeUTF("START_GAME"); // TO_DO encode with 0
                String data = in.readUTF();
                out.writeUTF(data);
                // System.out.println(data); // the args parameter in the Client' Command
            }
        } catch (Exception e) {
            System.out.println("Listen :" + e.getMessage());
        }
    }
}
