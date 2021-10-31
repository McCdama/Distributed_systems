package jsrv.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TCPServer {

    // private static final String SERVER_IP = "127.0.0.1";
    public static final int PORT = 9090;

    private static final int SERVER_READY = 0;
    private static final int CORRECT_GUESS = 1;
    private static final int INCORRECT_GUESS = 2;
    private static final int GAME_OVER = 9;

    static Random random;
    static int attemp = 0;
    static int randomNumber;

    public static void main(String args[]) throws IOException {
        ServerSocket listener = new ServerSocket(PORT);
        System.out.println("[Server] Waiting for connection");
        Socket client = listener.accept();
        System.out.println("[Server] Connected to the Client");

        OutputStream out = client.getOutputStream();
        InputStream in = client.getInputStream();
        // out.write(SERVER_READY);
        random = new Random();
        randomNumber = random.nextInt(9) + 1;
        System.out.println("Generated Random Number: " + randomNumber);
        try {
            UPPER_OUTER: while (true) {
                GAME: while (attemp <= 3) {
                    int request = in.read();
                    System.out.println("[Client sends]: " + request);
                    if (randomNumber != request) {
                        out.write(INCORRECT_GUESS);
                        attemp++;
                        break;
                    } else {
                        out.write(CORRECT_GUESS);
                        attemp = 3;
                        break GAME;
                    }
                }
                if (attemp == 3) {
                    System.out.println("---DONE---");
                    out.write(GAME_OVER);
                    break UPPER_OUTER;
                }
            }
            in.close();
            out.close();
            client.close();

        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
