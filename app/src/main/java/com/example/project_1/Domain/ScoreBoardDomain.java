package com.example.project_1.Domain;

import org.json.JSONException;
import org.json.JSONObject;

public class ScoreBoardDomain {
    private String team_1;
    private String team_2;
    private String score;
    private String description;

    public ScoreBoardDomain(String team_1, String team_2, String score, String description) {
        this.team_1 = team_1;
        this.team_2 = team_2;
        this.score = score;
        this.description = description;
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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ScoreBoardDomain createScoreBoard(String json) throws JSONException {
        JSONObject scoreObject = new JSONObject();
        String team_1 = scoreObject.getString("team-1");
        String team_2 = scoreObject.getString("team-2");
        String score_var = scoreObject.getString("score");
        String desc = scoreObject.getString("description");
        ScoreBoardDomain score = new ScoreBoardDomain(team_1,team_2,score_var,desc);
        return score;
    }
}
