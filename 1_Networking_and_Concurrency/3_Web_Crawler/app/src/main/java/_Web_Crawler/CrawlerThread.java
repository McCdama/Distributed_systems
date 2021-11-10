package _Web_Crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CrawlerThread extends Thread {
    private Set<String> emails;
    private Queue<String> links = new LinkedList<>();
    private List<String> visited = new LinkedList<>();
    private String initialURL;
    public CrawlerThread(Set<String> emails, String initialURL){
        this.emails =  emails;
        this.initialURL =  initialURL;
        links.add(initialURL);
    }
    @Override
    public void run(){
        while (!links.isEmpty()) {
            String nextURL = links.remove().trim();
            visited.add(nextURL);
            try {
                
                System.out.println("Reading form URL "+nextURL);
                URL url = new URL(nextURL);
                System.out.println("URL " + url);
                
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); // casting is important
                System.out.println("Response Message is " + httpURLConnection.getResponseMessage());
                Map<String, List<String>> hdrs = httpURLConnection.getHeaderFields();
                Set<String> hdrKeys = hdrs.keySet();
                for (String k : hdrKeys)
                  System.out.println("Key: " + k + "  Value: " + hdrs.get(k));
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                
            } catch (IOException e) {
                System.err.println("Can not read the URL: "+nextURL);
            }
        }
    }

}
