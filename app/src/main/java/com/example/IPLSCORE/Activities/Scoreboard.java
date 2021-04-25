package com.example.IPLSCORE.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.IPLSCORE.R;
import com.example.IPLSCORE.fragments.FinishedMatches;
import com.example.IPLSCORE.fragments.UpcomingMatches;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firestore.v1.UpdateDocumentRequestOrBuilder;

import java.util.ArrayList;
import java.util.Map;

public class Scoreboard extends AppCompatActivity {
    public static ProgressBar loader;
    public static TextView id;
    public static TextView status;
    public static TextView score;
    public static TextView over;
    public static TextView comment;
    public static ScrollView view;
    public  ArrayList<String> teamPlayer1 = new ArrayList<>();
    public  ArrayList<String> teamPlayer2 = new ArrayList<>();
    public static ArrayAdapter adapter1;
    public static ArrayAdapter adapter2;
    public static ListView team1;
    public static ListView team2;
    @Override
    public void onBackPressed() {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scoreboard);
        loader = (ProgressBar) findViewById(R.id.loader);
        id = (TextView) findViewById(R.id.vs);
        status = (TextView) findViewById(R.id.statusValue);
        score = (TextView) findViewById(R.id.scoreValue);
        over = (TextView) findViewById(R.id.overValue);
        comment = (TextView) findViewById(R.id.commentValue);
        view = (ScrollView) findViewById(R.id.scrollView2);
        view.setVisibility(View.INVISIBLE);
        loader.setVisibility(View.VISIBLE);
        Intent main = getIntent();
        String id = main.getStringExtra("id");
        adapter1 = new ArrayAdapter<String>(this,
                R.layout.activity_listview, teamPlayer1);
        team1 = (ListView) findViewById(R.id.player1_list);
        team1.setAdapter(adapter1);
        adapter2 = new ArrayAdapter<String>(this,
                R.layout.activity_listview, teamPlayer2);
        team2 = (ListView) findViewById(R.id.player2_list);
        team2.setAdapter(adapter2);
        new Scoreboard().getScore(id);

    }

    private void getScore(String id) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("collection").document(id);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        new Scoreboard().setData(document);
                    } else {
                        System.out.println("TAG"+ "No such document");
                    }
                } else {
                    System.out.println("TAG"+ "get failed with "+ task.getException());
                }
            }
        });

    }

    private void setData(DocumentSnapshot document) {
        view.setVisibility(View.VISIBLE);
        loader.setVisibility(View.INVISIBLE);
        Map<String,Object> data = document.getData();
        Map<String,String> match = (Map<String, String>) data.get("match");
        ArrayList<Map<String,ArrayList>> list = (ArrayList) document.getData().get("team_players");
        Map<String,ArrayList> teamarray1 = list.get(0);
        Map<String,ArrayList> teamarray2 = list.get(1);
        teamPlayer1.addAll(teamarray1.get("players"));
        teamPlayer2.addAll(teamarray2.get("players"));
        System.out.println("teamPlayer1"+teamPlayer1.size());
        System.out.println("teamPlayer1"+teamPlayer2.size());


        team1.setAdapter(adapter1);
        team2.setAdapter(adapter2);
        team1.invalidateViews();
        team2.invalidateViews();
        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();

        System.out.println("Id val"+id);
        id.setText(match.get("match_name"));
        status.setText(match.get("state"));
        if(match.get("state").equals("POST") || match.get("state").equals("LIVE"))
        {
            Map<String,String> stats = (Map<String, String>) data.get("recent_ball_stats");
            score.setText(stats.get("score"));
            over.setText(stats.get("over"));
            comment.setText(stats.get("comment"));
        }
        else
        {
            score.setVisibility(View.INVISIBLE);
            over.setVisibility(View.INVISIBLE);
            comment.setVisibility(View.INVISIBLE);
        }
    }


}