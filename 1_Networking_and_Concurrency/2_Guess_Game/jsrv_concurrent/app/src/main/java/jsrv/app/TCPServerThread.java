package jsrv.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

public class TCPServerThread extends Thread {

    private static final Random RANDOM = new Random();
    private static int MAX_ATTEMPTS = 3;
    private final Socket clientSocket;

    private static final int SERVER_READY = 0;
    private static final int START_GAME = 0;
    private static final int CORRECT_GUESS = 1;
    private static final int INCORRECT_GUESS = 2;
    private static final int GAME_OVER = 9;

    public TCPServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (clientSocket) {
            System.out.println("[Server] Connected to the Client with Port No.: "+ clientSocket.getPort());
            InputStream in = clientSocket.getInputStream();
            OutputStream out = clientSocket.getOutputStream();
            int recievedCode = in.read();

            if (recievedCode == START_GAME) {
                System.out.println("let's go mon soleil..");
                int attempts = 0;
                int generatedRandom = RANDOM.nextInt(10);
                System.out.println("Generated Random Number: " + generatedRandom);
                out.write(SERVER_READY);
                while (true) {
                    int request = in.read();
                    System.out.println("[Client with Port No. " +clientSocket.getPort()+ " sends]: " + request);
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
            clientSocket.close();

        } catch (IOException e) {
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
