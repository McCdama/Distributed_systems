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
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class CrawlerThread extends Thread {
    private Set<String> emails;
    private String patternString = "<a\\s+href\\s*=\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>";
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
        while (!links.isEmpty()) { // DO_CHECK: isValidURL
            String nextURL = links.remove().trim();
            visited.add(nextURL);
            try {
                
                System.out.println("Reading form URL "+nextURL);
                URL url = new URL(nextURL);
                //System.out.println("URL " + url.toExternalForm());
                
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); // casting is important
                System.out.println("Response Message is " + httpURLConnection.getResponseMessage());
                Map<String, List<String>> hdrs = httpURLConnection.getHeaderFields();
                Set<String> hdrKeys = hdrs.keySet();
                //for (String k : hdrKeys)
                  //System.out.println("Key: " + k + "  Value: " + hdrs.get(k));
                
                
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String stringBuilderLine = bufferedReader.readLine();
                while (stringBuilderLine !=null) {
                    stringBuilder.append(stringBuilderLine);
                    //System.err.println(stringBuilderLine);
                    stringBuilderLine = bufferedReader.readLine();
                }

                // LINKS PATTERN AND SEARCHING
                Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
                Stream<MatchResult> matchResult = pattern.matcher(stringBuilder).results();
                matchResult.map(MatchResult::group).forEach(System.out::println); // Echo out all pattern matched results!
                
                Matcher matcher = pattern.matcher(stringBuilder.toString());
               while (matcher.find()) {  
                   String match = matcher.group(1); // <a href = will be neglected
                   String newURL = null;
                   if (initialURL.startsWith("/")) {
                       if (initialURL.endsWith("/")) {
                           newURL = initialURL + match.substring(1); //cut up
                       }
                   } else {
                       newURL = initialURL + match.substring(1);
                       System.out.println("NEW: "+newURL);
                   }
               }

                
                
                
                
                
            } catch (IOException e) {
                System.err.println("Can not read the URL: "+nextURL);
            }
        }
    }

}
