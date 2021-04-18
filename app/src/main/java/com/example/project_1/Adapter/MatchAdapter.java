package com.example.project_1.Adapter;


import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.sax.TextElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_1.Domain.Match;
import com.example.project_1.R;

import java.net.URI;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

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
        TextView id = holder.id;
        id.setText(match.getId());
        ImageView team1img = holder.team1img;
        team1img.setImageURI(Uri.parse(match.getTeam1img()));
        ImageView team2img = holder.team2img;
        team2img.setImageURI(Uri.parse(match.getTeam2img()));
        if(match.getMatch_started().equals("LIVE"))
        {
            TextView state = holder.state;
            state.setText("LIVE");
            state.setTypeface(null, Typeface.BOLD);
            state.setVisibility(View.VISIBLE);
            Animation anim = new AlphaAnimation(0.0f, 1.0f);
            anim.setDuration(800); //You can manage the blinking time with this parameter
            anim.setStartOffset(20);
            anim.setRepeatMode(Animation.REVERSE);
            anim.setRepeatCount(Animation.INFINITE);
            state.startAnimation(anim);
        }


    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView team1;
        public TextView team2;
        public TextView date;
        public TextView id;
        public TextView state;
        public ImageView team1img;
        public ImageView team2img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            team1 = (TextView) itemView.findViewById(R.id.team1);
            team2 = (TextView) itemView.findViewById(R.id.team2);
            date = (TextView) itemView.findViewById(R.id.date);
            id = (TextView) itemView.findViewById(R.id.id);
            state = (TextView) itemView.findViewById(R.id.state);
            team1img = (ImageView) itemView.findViewById(R.id.imageteam1);
            team2img = (ImageView) itemView.findViewById(R.id.imageteam2);
        }
    }
}
