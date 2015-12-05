package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

/**
 * Created by Dillon on 12/5/2015.
 */
public class ResolverThread extends Thread{

    private boolean alive = true; public void kill(){alive = false;}

    private String fiftyfifty, adrs1, adrs2;
    long solveTime;
    int tryCount = 0;

    public ResolverThread(String fiftyfifty){
        this.fiftyfifty = fiftyfifty;
    }

    public void run(){
        solveTime = System.currentTimeMillis();
        synchronized (this) {
            try {
                adrs1 = adrs2 = sendGet(fiftyfifty);
                do {
                    tryCount++;
                    if(!alive)
                        return;
                    adrs2 = sendGet(fiftyfifty);
                    Thread.sleep(1000);
                } while (adrs1.equals(adrs2));
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            solveTime = System.currentTimeMillis() - solveTime;
            this.notify();
        }
    }

    private String sendGet(String url) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();

        if(con.getResponseCode() != 200)
            System.out.println("Response Code : " + responseCode + " on url: " + url);

        return con.getURL().toString();

    }

    public String getFiftyfifty(){
        return fiftyfifty;
    }

    public String getAdrs1() {
        return adrs1;
    }

    public String getAdrs2() {
        return adrs2;
    }
}
