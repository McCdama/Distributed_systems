package jsrv.app;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class TCPServer {
    public static void main(String args[]) {
        try {
            boolean correcto = true;
            Random rand = new Random();
            int number, sonuc, number2, counter = 0;
            number2 = rand.nextInt(10) + 1;
            ServerSocket s1 = new ServerSocket(8080);
            Socket ss = s1.accept();
            Scanner sc = new Scanner(ss.getInputStream());
            while (correcto) {
                InputStream in = ss.getInputStream();
                BufferedReader bin = new BufferedReader(new InputStreamReader(in));

                number = Integer.parseInt(bin.readLine());

                if (counter == 2 && number != number2) {
                    sonuc = 4;

                    PrintWriter pout = new PrintWriter(ss.getOutputStream(), true);
                    pout.println(sonuc);
                    correcto = false;
                }

                if (number == number2) {
                    sonuc = 1;

                    PrintWriter pout = new PrintWriter(ss.getOutputStream(), true);
                    pout.println(sonuc);
                    correcto = false;
                }

                else if (number > number2) {
                    sonuc = 3;
                    counter++;
                    PrintWriter pout = new PrintWriter(ss.getOutputStream(), true);
                    pout.println(sonuc);
                }

                else if (number < number2) {
                    sonuc = 2;
                    counter++;
                    PrintWriter pout = new PrintWriter(ss.getOutputStream(), true);
                    pout.println(sonuc);
                }
            }
            ss.close();
        } catch (Exception e) {
            System.out.println("Listen :" + e.getMessage());
        }
    }
}
