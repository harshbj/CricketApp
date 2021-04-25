package com.example.IPLSCORE.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.IPLSCORE.Adapter.MatchAdapter;
import com.example.IPLSCORE.Domain.Match;
import com.example.IPLSCORE.R;
import com.example.IPLSCORE.Activities.Scoreboard;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpcomingMatches#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpcomingMatches extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public static ArrayList<Match> matches = new ArrayList<>();
    public static RecyclerView cards;
    public static ProgressBar loader;
    public static TextView live;
    public static FragmentManager fragmentManager;
    public static Activity activity;

    public UpcomingMatches() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpcomingMatches.
     */
    // TODO: Rename and change types and number of parameters
    public static UpcomingMatches newInstance(String param1, String param2) {
        UpcomingMatches fragment = new UpcomingMatches();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        System.out.println("Reached Main");
        new UpcomingMatches().getData();
        System.out.println("Again Reached Main");
        UpcomingMatches.fragmentManager = getFragmentManager();
        MatchAdapter matchAdapter = new MatchAdapter(UpcomingMatches.matches);

        View view =  inflater.inflate(R.layout.fragment_upcoming_matches, container, false);
        cards = (RecyclerView) view.findViewById(R.id.rvMatches);
        loader = (ProgressBar) view.findViewById(R.id.loader);
        cards.setVisibility(View.INVISIBLE);
        activity = getActivity();
        return view;
    }
    public void showScoreBoard(View view) {
        Intent scoreBoard = new Intent(UpcomingMatches.activity, Scoreboard.class);
        TextView id = view.findViewById(R.id.id);
        System.out.println("Passed Value "+id.getText() );
        scoreBoard.putExtra("id",id.getText());
        startActivity(scoreBoard);

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
                                matchList.addAll(Match.createUpComingMatchList(task));
                                UpcomingMatches.matches = matchList;
                                System.out.println(UpcomingMatches.matches.size());
                                new UpcomingMatches().setUpMatches();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            System.out.println("Data Fetched");
                        } else {
                            System.out.println("FIRESTORE"+ "Error getting documents."+ task.getException());
                        }
                    }
                });
    }

    private void setUpMatches() {
        MatchAdapter matchAdapter = new MatchAdapter(UpcomingMatches.matches);
        System.out.println("Reached Method");
        loader.setVisibility(View.INVISIBLE);
        cards.setVisibility(View.VISIBLE);
        cards.setAdapter(matchAdapter);
        cards.setLayoutManager(new LinearLayoutManager(getContext()));
        Fragment upcomingMatches = new UpcomingMatches();
        FragmentTransaction fragmentTransaction = UpcomingMatches.fragmentManager.beginTransaction();
        fragmentTransaction.detach(upcomingMatches);
        fragmentTransaction.attach(upcomingMatches);
        fragmentTransaction.commit();

    }


}