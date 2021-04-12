package com.example.project_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class Scoreboard extends AppCompatActivity {

    public static ProgressBar loader;
    public static TextView id;
    public static TextView status;
    public static TextView score;
    public static TextView over;
    public static TextView comment;
    public static ScrollView view;
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