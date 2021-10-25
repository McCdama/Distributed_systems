package lux2.app;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;

public class TCPClient {
    public static void main(String args[]) throws UnknownHostException, IOException {
        // arguments supply message and hostname of destination
        int number, sonuc;
        boolean correct = true;
        Scanner sc = new Scanner(System.in);
        Socket s = new Socket("127.0.0.1", 8080);
        Scanner sc1 = new Scanner(s.getInputStream());
        while (correct) {

            System.out.println("Enter a number between 1-10");
            number = sc.nextInt();

            PrintWriter pout = new PrintWriter(s.getOutputStream(), true);
            pout.println(number);

            InputStream in = s.getInputStream();
            BufferedReader bin = new BufferedReader(new InputStreamReader(in));
            sonuc = Integer.parseInt(bin.readLine());
            if (sonuc == 1) {
                System.out.println("You win!");
                correct = false;
            } else if (sonuc == 2) {
                System.out.println("Increase!");

            } else if (sonuc == 3) {
                System.out.println("Decrease!");
            }

            if (sonuc == 4) {
                System.out.println("You Lost!");
                correct = false;
            }
        }
        s.close();

    }
}
