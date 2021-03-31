package com.example.project_1.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.project_1.Domain.Match;
import com.example.project_1.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {
    private ArrayList<Match> matches;
    public MatchAdapter(ArrayList<Match> inMatches)
    {
        matches = inMatches;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View matchView = inflater.inflate(R.layout.match_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(matchView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Match match = matches.get(position);
        TextView team1 = holder.team1;
        team1.setText(match.getTeam_1());
        TextView team2 = holder.team2;
        team2.setText(match.getTeam_2());
        TextView date = holder.date;
        date.setText(match.getDate());


    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView team1;
        public TextView team2;
        public TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            team1 = (TextView) itemView.findViewById(R.id.team1);
            team2 = (TextView) itemView.findViewById(R.id.team2);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }
}
