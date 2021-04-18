package com.example.project_1.Domain;

import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Match {


    public Match(String id, String date, String localDateTime, String team_1, String team_2, Boolean squad, String type, String toss_winner, String winner_team, String match_started,String team1img,String team2img) {
        this.id = id;
        this.date = date;
        this.localDateTime = localDateTime;
        this.team_1 = team_1;
        this.team_2 = team_2;
        this.squad = squad;
        this.type = type;
        this.toss_winner = toss_winner;
        this.winner_team = winner_team;
        this.match_started = match_started;
        this.team1img = team1img;
        this.team2img = team2img;
    }
    private String id;
    private String date;
    private String localDateTime;
    private String team_1;
    private String team_2;
    private Boolean squad;

    public String getTeam1img() {
        return team1img;
    }

    public void setTeam1img(String team1img) {
        this.team1img = team1img;
    }

    public String getTeam2img() {
        return team2img;
    }

    public void setTeam2img(String team2img) {
        this.team2img = team2img;
    }

    private String type;
    private String toss_winner;
    private String winner_team;
    private String match_started;
    private String team1img;
    private String team2img;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocalDateTime() {
        return localDateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLocalDateTime(String localDateTime) {
        this.localDateTime = localDateTime;
    }


    public String getMatch_started() {
        return match_started;
    }

    public void setMatch_started(String match_started) {
        this.match_started = match_started;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeam_1() {
        return team_1;
    }

    public void setTeam_1(String team_1) {
        this.team_1 = team_1;
    }

    public String getTeam_2() {
        return team_2;
    }

    public void setTeam_2(String team_2) {
        this.team_2 = team_2;
    }

    public Boolean getSquad() {
        return squad;
    }

    public void setSquad(Boolean squad) {
        this.squad = squad;
    }


    public String getToss_winner() {
        return toss_winner;
    }

    public void setToss_winner(String toss_winner) {
        this.toss_winner = toss_winner;
    }

    public String getWinner_team() {
        return winner_team;
    }

    public void setWinner_team(String winner_team) {
        this.winner_team = winner_team;
    }

    public String isMatch_started() {
        return match_started;
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    public static ArrayList<Match> createMatchList(Task<QuerySnapshot> matchData) throws JSONException, ParseException {
        ArrayList<Match> matches = new ArrayList<Match>();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy");

        for (QueryDocumentSnapshot document : matchData.getResult()) {
            Map<String, Object> map = document.getData();
            Map<String,String> matchMap = (Map<String, String>) map.get("match");
            ArrayList<Object> teams = (ArrayList<Object>) map.get("teams");
            Map<String,String> team1 = (Map<String, String>) teams.get(0);
            Map<String,String> team2 = (Map<String, String>) teams.get(1);
                Date date = inputFormat.parse(matchMap.get("start_time"));
                String formattedDate = outputFormat.format(date);
                Match match = new Match(document.getId(),
                        formattedDate,
                        "",
                        team1.get("name"),
                        team2.get("name"),
                        true,
                        "IPL",
                        "", "", matchMap.get("state"),
                        team1.get("logo"),team2.get("logo"));

                matches.add(match);
        }
        System.out.println("Dtaa passed"+matches.size());
        return matches;
    }
}
