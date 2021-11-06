package jsrv.app;

import java.io.IOException;
import java.net.ServerSocket;

public class TCPServer {

    public static final int PORT = 9090;
    public static void main(String args[]) {
        try (ServerSocket listener = new ServerSocket(PORT)) {

            System.out.println("[Server] Waiting for connection");
            while (true) {
                new TCPServerThread(listener.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
