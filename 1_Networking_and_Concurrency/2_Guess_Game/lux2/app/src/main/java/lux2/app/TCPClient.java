package lux2.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    private static final String SERVER_IP = "127.0.0.1";
    public static final int SERVER_PORT = 9090;

    public static final int START_GAME = 0;
    public static final int GAME_STARTED = 0;
    private static final int CORRECT_GUESS = 1;
    private static final int INCORRECT_GUESS = 2;
    private static final int GAME_OVER = 9;

    public static void main(String args[]) {

        Scanner keyboard = new Scanner(System.in);

        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {

            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            out.write(START_GAME);
            int recievedCode = in.read();
            if (recievedCode == GAME_STARTED) {
                System.out.println("let's go mon soleil..");
            } else {
                throw new IllegalStateException("Not in sync..");
            }
            UPPER_OUTER: while (true) {
                System.out.println("Enter guess number btw 0 and 9");
                System.out.println("> ");
                String clientInput = keyboard.nextLine();
                out.write(Integer.parseInt(clientInput));
                int serverResponse = in.read();
                System.out.println("ServerResponse: " + serverResponse);
                switch (serverResponse) {
                case CORRECT_GUESS:
                    System.out.println("You've won");
                    break UPPER_OUTER;
                case INCORRECT_GUESS:
                    System.out.println("Try again");
                    break;
                case GAME_OVER:
                    System.out.println("You've lost!");
                    break UPPER_OUTER;
                }
            }
        } catch (IOException e) {
            System.err.println("Server stops");
            e.printStackTrace();
        }
        keyboard.close();
    }
}
