package _Web_Crawler;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class WebCrawler {
    
    public static void main(String[] args) {
        //String initialURL = args[0];
        String initialURL = "https://pdos.csail.mit.edu/";
        Set<String> emails = new HashSet<>();

        Thread sThread = new CrawlerThread(emails, initialURL);
        //sThread.setDaemon(true);
        sThread.start();

        /* System.out.println("Press <Enter> to stop crawling..");
        Scanner keyboard = new Scanner(System.in);
        keyboard.nextLine();

        System.out.println("Emails: " + emails);
        keyboard.close(); */
    }
}
