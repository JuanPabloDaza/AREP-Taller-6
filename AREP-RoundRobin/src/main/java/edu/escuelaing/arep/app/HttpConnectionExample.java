package edu.escuelaing.arep.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnectionExample {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GET_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=fb&apikey=Q1QZFVJQ21K7C6XM";
    private static final String[] LOG_SERVERS = new String[]{
                                                            "http://logservice1:4568",
                                                            "http://logservice2:4568",
                                                            "http://logservice3:4568"
                                                            };
    private static int currentServer=0;

    public static String remoteLogCall(String value) throws IOException{
        System.out.println(LOG_SERVERS[currentServer] + "/log?value=" + value);
        return remoteHttpCall(LOG_SERVERS[currentServer] + "/log?value=" + value);
    }
    
    public static String remoteHttpCall(String url) throws IOException{

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        
        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        StringBuffer response = new StringBuffer();
        
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
            return "404 error";
        }
        System.out.println("GET DONE");
        rotateRoundRobinServer();
        return response.toString();
    }

    public static void rotateRoundRobinServer(){
        currentServer = (currentServer + 1) % 3;
    }

} 