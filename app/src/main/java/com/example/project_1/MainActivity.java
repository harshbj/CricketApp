package com.example.project_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.project_1.Adapter.MatchAdapter;
import com.example.project_1.Domain.Match;
import com.example.project_1.Service.UPComingMatches;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    public static ArrayList<Match> matches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView cards = (RecyclerView) findViewById(R.id.rvMatches);
        ProgressBar loader = (ProgressBar) findViewById(R.id.loader);

        UPComingMatches matches = new UPComingMatches();
        AsyncTask<String, Void, ArrayList<Match>> matchList = matches.execute("https://cricapi.com/api/matches?apikey=94oEvY0JjrZQLjuUdHnYglfxduh1");


        while(matchList.getStatus()==AsyncTask.Status.PENDING || matchList.getStatus() == AsyncTask.Status.RUNNING)
        {
            cards.setVisibility(View.INVISIBLE);
            loader.setVisibility(View.VISIBLE);
        }
        MatchAdapter matchAdapter = new MatchAdapter(MainActivity.matches);
        cards.setAdapter(matchAdapter);
        cards.setLayoutManager(new LinearLayoutManager(this));
    }

    public void getUpcomingMatches(ArrayList<Match> getmatches) {
        MainActivity.matches = getmatches;


    }

}