package lux2.app;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;

public class TCPClient {
    private static final String SERVER_IP = "127.0.0.1";
    public static final int SERVER_PORT = 9090;

    private static final int SERVER_READY = 0;
    private static final int CORRECT_GUESS = 1;
    private static final int INCORRECT_GUESS = 2;
    private static final int GAME_OVER = 9;
    private static int attemp = 0;


    public static void main(String args[]) throws UnknownHostException, IOException {
        Socket socket = new Socket(SERVER_IP, SERVER_PORT);

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        OutputStream out = new DataOutputStream(socket.getOutputStream());


        while (true) {
            System.out.println("> ");

            int command = Integer.parseInt(keyboard.readLine());
            if (command == 112) break;
            
  
                out.write(command);
                int serverResponse = input.read();
                System.out.println("ServerResponse: " + serverResponse);

                
                GAME:
                switch (serverResponse) {
                case SERVER_READY:
                    System.out.println("SERVER READY");
                    break;
                case CORRECT_GUESS:
                    System.out.println("You've won");
                    break GAME;
                case INCORRECT_GUESS:
                if (attemp <= 3){
                    System.out.println("Try again");
                    attemp++;
                    break;
                }
                case GAME_OVER:
                if (attemp == 3){
                    System.out.println("GAME_OVER");
                    break GAME;
                }
            }
        }
        System.out.println("Finished Game...");
        socket.close();

    }
}
