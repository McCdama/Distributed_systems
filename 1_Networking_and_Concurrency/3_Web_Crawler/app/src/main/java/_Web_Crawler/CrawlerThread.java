package _Web_Crawler;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
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
                
                System.out.println("Reading form URL"+nextURL);
                URL myUrl = new URL(nextURL);
                
            } catch (IOException e) {
                System.err.println("Can not read the URL: "+nextURL);
            }
        }
    }


}
