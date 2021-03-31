package com.example.project_1.Domain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Match {


    public Match(Integer id, String date, String localDateTime, String team_1, String team_2, Boolean squad, String type, String toss_winner, String winner_team, boolean match_started) {
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
    }
    private Integer id;
    private String date;
    private String localDateTime;
    private String team_1;
    private String team_2;
    private Boolean squad;
    private String type;
    private String toss_winner;
    private String winner_team;
    private Boolean match_started;

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


    public Boolean getMatch_started() {
        return match_started;
    }

    public void setMatch_started(Boolean match_started) {
        this.match_started = match_started;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public boolean isMatch_started() {
        return match_started;
    }

    public void setMatch_started(boolean match_started) {
        this.match_started = match_started;
    }





    public static ArrayList<Match> createMatchList(String matchData) throws JSONException {
        ArrayList<Match> matches = new ArrayList<Match>();
        JSONObject json = new JSONObject(matchData);
        JSONArray matchesJson  = json.getJSONArray("matches");
        for(int i=0;i<matchesJson.length();i++)
        {
            Match match = new Match(matchesJson.getJSONObject(i).getInt("unique_id"),
                    matchesJson.getJSONObject(i).getString("date"),
                    matchesJson.getJSONObject(i).getString("dateTimeGMT"),
                    matchesJson.getJSONObject(i).getString("team-1"),
                    matchesJson.getJSONObject(i).getString("team-2"),
                    matchesJson.getJSONObject(i).getBoolean("squad"),
                    matchesJson.getJSONObject(i).getString("type"),
            "","",false);
            matches.add(match);

        }
        return matches;
    }
}
