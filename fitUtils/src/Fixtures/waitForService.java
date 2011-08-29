/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Fixtures;

import Common.Symbol;
import fit.ColumnFixture;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 *
 * @author random
 */
public class waitForService extends ColumnFixture {
    public String serviceUrl;
    public String tryUntil;
    public String debug = "<br>Starting Fixture..";
    
    public String foundMatch() throws MalformedURLException, IOException, InterruptedException, URISyntaxException{
        
        boolean foundMatch = false;
        int iterations = 0;        
        
        serviceUrl = Symbol.parseSymbols(serviceUrl);
        tryUntil = Symbol.parseSymbols(tryUntil);


        URL url = new URL(serviceUrl);
        debug += url;

        while (!foundMatch && iterations < 10 ){
            long sleep = iterations * 1000;
            debug += "<br><br>Sleeping for " + sleep + "ms..";
            Thread.sleep(sleep);
            iterations++;
            debug += "<br>Entering run: " + iterations;
            
            HttpURLConnection call = (HttpURLConnection) url.openConnection();
            call.setRequestProperty("Content-Type", "application/json");
        
            BufferedReader in;
            try {
                in = new BufferedReader(
                    new InputStreamReader(call.getInputStream()));
            } catch (Exception e) {
                /* We want the response body, even if the call failed. */
                in = new BufferedReader(
                        new InputStreamReader(call.getErrorStream()));
            }
        
            boolean hasNext = true;
            String readIn = "";
            while (hasNext) {
                String next = in.readLine();
                if (next == null) {
                    hasNext = false;
                } else {
                    readIn += next;
                }     
            }
            in.close();

            foundMatch = readIn.toLowerCase().contains(tryUntil.toLowerCase());
            debug += "<br>Found match: " + foundMatch;
            debug += "<br>Total runs: " + iterations;
            debug += "<br>Service Response:<br><br>" + readIn + "<br>"; 
        }
        
        return "Found Match: " + foundMatch;
    }
    
}
