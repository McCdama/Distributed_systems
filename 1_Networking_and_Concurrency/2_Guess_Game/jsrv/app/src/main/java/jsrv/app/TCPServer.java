package jsrv.app;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class TCPServer {

    private static final String SERVER_IP = "127.0.0.1";
    public static final int PORT = 9090;

    private static final int SERVER_READY = 0;
    private static final int CORRECT_GUESS = 1;
    private static final int INCORRECT_GUESS = 2;
    private static final int GAME_OVER = 9;

    public static void main(String args[]) throws IOException {
        ServerSocket listener = new ServerSocket(PORT);
        System.out.println("[Server] Waiting for connection");
        Socket client = listener.accept();
        System.out.println("[Server] Connected to the Client");
        System.out.println("[Server] SERVER_READY");

        OutputStream out = client.getOutputStream();
        InputStream in = client.getInputStream();

        try {
            while (true) {
                Random random = new Random();
                int randomNumber = random.nextInt(9);
                int attemp = 0;

                while (attemp != 3) {
                    attemp++;
                    out.write(SERVER_READY);

                    int request = in.read();

                    if (randomNumber != request) {
                        switch (request) {
                        case INCORRECT_GUESS:
                            out.write(INCORRECT_GUESS);
                            break;
                        case CORRECT_GUESS:
                        out.write(CORRECT_GUESS);
                        }
                    } else {
                        out.write(CORRECT_GUESS);
                    }
                } 
            }
        } finally {
            out.close();
            in.close();
        }
    }

}
