package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.project_1.Adapter.MatchAdapter;
import com.example.project_1.Domain.Match;
import com.example.project_1.Service.UPComingMatches;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {
    public static ArrayList<Match> matches = new ArrayList<>();
    RecyclerView cards;
    ProgressBar loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cards = (RecyclerView) findViewById(R.id.rvMatches);
        loader = (ProgressBar) findViewById(R.id.loader);
        System.out.println("Reached Main");
        cards.setVisibility(View.INVISIBLE);



        UPComingMatches matches = new UPComingMatches();
        try {
            MainActivity.matches = matches.execute("https://cricapi.com/api/matches?apikey=94oEvY0JjrZQLjuUdHnYglfxduh1").get();
            MatchAdapter matchAdapter = new MatchAdapter(MainActivity.matches);
            System.out.println("Reached Method");
            loader.setVisibility(View.INVISIBLE);
            cards.setVisibility(View.VISIBLE);
            cards.setAdapter(matchAdapter);
            cards.setLayoutManager(new LinearLayoutManager(this));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }




}