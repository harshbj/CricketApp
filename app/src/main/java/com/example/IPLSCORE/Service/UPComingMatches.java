package com.example.IPLSCORE.Service;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.IPLSCORE.Domain.Match;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class UPComingMatches extends AsyncTask<String,Void,ArrayList<Match>> {
    ArrayList<Match> matchList = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected ArrayList<Match> doInBackground(String... strings) {
        System.out.println("IN");
            db.collection("collection")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                try {
                                    matchList.addAll(Match.createUpComingMatchList(task));
                                    System.out.println("Result"+matchList.size());
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

            matchList = (ArrayList<Match>) matchList.stream()
                    .sorted(Comparator.comparing(Match::getDate))
                    .collect(Collectors.toList());
            return matchList;
        }


    }

