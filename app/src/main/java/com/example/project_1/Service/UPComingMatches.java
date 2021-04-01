package com.example.project_1.Service;

import android.os.AsyncTask;

import com.example.project_1.Domain.Match;
import com.example.project_1.MainActivity;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

public class UPComingMatches extends AsyncTask<String,Void,ArrayList<Match>> {
    String matchData;
    ArrayList<Match> matchList;
    @Override
    protected ArrayList<Match> doInBackground(String... strings) {
        try {

            URL url = new URL("https://cricapi.com/api/matches?apikey=94oEvY0JjrZQLjuUdHnYglfxduh1");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                matchData = output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        try {
            try {
                matchList = Match.createMatchList(matchData);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return matchList;
    }



}
