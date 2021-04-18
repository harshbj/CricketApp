package com.example.project_1;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.project_1.Adapter.MatchAdapter;
import com.example.project_1.Domain.Match;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.project_1.ui.main.SectionsPagerAdapter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;

import static com.example.project_1.MainActivity.cards;
import static com.example.project_1.MainActivity.loader;

public class MainActivity2 extends AppCompatActivity {

//    public static ArrayList<Match> matches = new ArrayList<>();
//    public static RecyclerView cards;
//    public static ProgressBar loader;
//    public static TextView live;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);



        //UPComingMatches matches = new UPComingMatches();
//        new MainActivity2().getData1();


//        FloatingActionButton fab = findViewById(R.id.fab);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }
//
//    public void showScoreBoard1(View view) {
//        Intent scoreBoard = new Intent(this, Scoreboard.class);
//        TextView id = view.findViewById(R.id.id);
//        System.out.println("Passed Value " + id.getText());
//        scoreBoard.putExtra("id", id.getText());
//        startActivity(scoreBoard);
//        finish();
//    }
//
//    public void getData1() {
//        ArrayList<Match> matchList = new ArrayList<>();
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("collection")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @RequiresApi(api = Build.VERSION_CODES.N)
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            try {
//                                matchList.addAll(Match.createMatchList(task));
//                                MainActivity2.matches = matchList;
//                                new MainActivity2().setUpMatches();
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            } catch (ParseException e) {
//                                e.printStackTrace();
//                            }
//                        } else {
//                            System.out.println("FIRESTORE" + "Error getting documents." + task.getException());
//                        }
//                    }
//                });
//    }
//
//    public void setUpMatches() {
//        MatchAdapter matchAdapter2 = new MatchAdapter(MainActivity2.matches);
//        System.out.println("Reached Method");
////            loader.setVisibility(View.INVISIBLE);
//        cards.setVisibility(View.VISIBLE);
//        cards.setAdapter(matchAdapter2);
//        cards.setLayoutManager(new LinearLayoutManager(this));
//    }
//

}