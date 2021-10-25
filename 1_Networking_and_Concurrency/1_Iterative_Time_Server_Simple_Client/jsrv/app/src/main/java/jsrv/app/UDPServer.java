package jsrv.app;

import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;

public class UDPServer {

    public static void main(String args[]) {
        DatagramSocket aSocket = null;
        try {
            System.out.println("Server is up...");
            aSocket = new DatagramSocket(6789);
            byte[] buffer = new byte[1000];
            
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);

                String sentence = new String(request.getData()).trim();
                System.out.println("Received: "+sentence);

                byte [] dataBytes = new byte[1000];
                int length = 0;
                if (sentence.equals("FULL")) {
                    // String pattern = "MM-dd-yyyy";
                    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
                    String dateString = dateFormat.format(new Date());
                    dataBytes = dateString.getBytes();
                    length= dateString.length();
                    request.setLength(length);
                } else if (sentence.equals("MEDIUM")){
                    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
                    String dateString = dateFormat.format(new Date());
                    dataBytes = dateString.getBytes();
                    length= dateString.length();
                    request.setLength(length);
                } else if (sentence.equals("SHORT")){
                    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
                    String dateString = dateFormat.format(new Date());
                    dataBytes = dateString.getBytes();
                    length= dateString.length();
                    request.setLength(length);
                } else{
                    System.out.println("Please provide a proper argument");
                }

                DatagramPacket reply = new DatagramPacket(dataBytes, length, request.getAddress(), request.getPort());
                    

                aSocket.send(reply);
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null)
                aSocket.close();
        }
    }
}