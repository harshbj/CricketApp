package com.example.project_1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.multidex.MultiDex;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.project_1.Adapter.MatchAdapter;
import com.example.project_1.Domain.Match;
import com.example.project_1.Service.UPComingMatches;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {
    public static ArrayList<Match> matches = new ArrayList<>();
    public static RecyclerView cards;
    public static ProgressBar loader;
    public static TextView live;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cards = (RecyclerView) findViewById(R.id.rvMatches);
        loader = (ProgressBar) findViewById(R.id.loader);
        System.out.println("Reached Main");
        cards.setVisibility(View.INVISIBLE);


        //UPComingMatches matches = new UPComingMatches();
        new MainActivity().getData();



    }

    
    public void showScoreBoard(View view) {
        Intent scoreBoard = new Intent(this,Scoreboard.class);
        TextView id = view.findViewById(R.id.id);
        System.out.println("Passed Value "+id.getText() );
        scoreBoard.putExtra("id",id.getText());
        startActivity(scoreBoard);
        finish();
    }

    public void getData()
    {
        ArrayList<Match> matchList = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("collection")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            try {
                                matchList.addAll(Match.createMatchList(task));
                                MainActivity.matches = matchList;
                                new MainActivity().setUpMatches();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println("FIRESTORE"+ "Error getting documents."+ task.getException());
                        }
                    }
                });
    }

    private void setUpMatches() {
        MatchAdapter matchAdapter = new MatchAdapter(MainActivity.matches);
        System.out.println("Reached Method");
        loader.setVisibility(View.INVISIBLE);
        cards.setVisibility(View.VISIBLE);
        cards.setAdapter(matchAdapter);
        cards.setLayoutManager(new LinearLayoutManager(this));
    }
}