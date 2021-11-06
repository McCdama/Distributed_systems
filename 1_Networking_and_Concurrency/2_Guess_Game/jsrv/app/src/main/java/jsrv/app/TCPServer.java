package jsrv.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TCPServer {

    public static final int PORT = 9090;
    private static final int SERVER_READY = 0;
    private static final int START_GAME = 0;
    private static final int CORRECT_GUESS = 1;
    private static final int INCORRECT_GUESS = 2;
    private static final int GAME_OVER = 9;
    private static final Random RANDOM = new Random();
    static int MAX_ATTEMPTS = 3;

    public static void main(String args[]) {
        try (ServerSocket listener = new ServerSocket(PORT)) {

            System.out.println("[Server] Waiting for connection");
            while (true) {
                Socket client = listener.accept();
                System.out.println("[Server] Connected to the Client");

                InputStream in = client.getInputStream();
                OutputStream out = client.getOutputStream();

                int recievedCode = in.read();
                if (recievedCode == START_GAME) {
                    System.out.println("let's go mon soleil..");
                    int attempts = 0;
                    int generatedRandom = RANDOM.nextInt(10);
                    System.out.println("Generated Random Number: " + generatedRandom);
                    out.write(SERVER_READY);
                    while (true) {
                        int request = in.read();
                        System.out.println("[Client sends]: " + request);
                        if (generatedRandom == request) {
                            out.write(CORRECT_GUESS);
                            break;
                        } else {
                            attempts++;
                            if (attempts < MAX_ATTEMPTS) {
                                out.write(INCORRECT_GUESS);
                            } else {
                                out.write(GAME_OVER);
                                break;
                            }
                        }
                    }
                    System.out.println("---DONE---");
                    attempts = 0;
                } else {
                    System.err.println("Closing Connenction! .. unresolved code");
                }
                client.close();
            }
        } catch (IOException e) {
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
