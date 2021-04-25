package com.example.IPLSCORE.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.example.IPLSCORE.R;

import com.example.IPLSCORE.fragments.FinishedMatches;
import com.example.IPLSCORE.fragments.UpcomingMatches;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.IPLSCORE.Adapter.SectionsPagerAdapter;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Upcoming"));
        tabs.addTab(tabs.newTab().setText("Live"));
        tabs.addTab(tabs.newTab().setText("Finished"));
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs.setupWithViewPager(viewPager);




    }
    public void showScoreBoard(View view) {
        Intent scoreBoard = new Intent(this, Scoreboard.class);
        TextView id = view.findViewById(R.id.id);
        System.out.println("Passed Value "+id.getText() );
        scoreBoard.putExtra("id",id.getText());
        startActivity(scoreBoard);

    }

}