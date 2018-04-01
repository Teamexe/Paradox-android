package com.exe.paradox.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.exe.paradox.R;
import com.exe.paradox.api.model.Leaderboard;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by shasha on 23/3/18.
 */

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewholder> {
    private List<Leaderboard> leaderboardList;

    public LeaderboardAdapter(List<Leaderboard> leaderboardList) {
        this.leaderboardList = leaderboardList;
    }

    @NonNull
    @Override
    public LeaderboardViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LeaderboardViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_leaderboard, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardViewholder holder, int position) {
        String nameValue = "";
        if (leaderboardList.get(position).getName().length() > 12)
            nameValue = leaderboardList.get(position).getName().substring(0, 11) + "...";
        else
            nameValue = leaderboardList.get(position).getName();

        holder.name.setText(nameValue);

        if (leaderboardList.get(position).getScore() < 0)
            holder.score.setText("0");
        else
            holder.score.setText(String.valueOf(leaderboardList.get(position).getScore()));
        holder.level.setText(String.valueOf(leaderboardList.get(position).getLevel()));
        Picasso.get().load(leaderboardList.get(position).getPicture()).placeholder(R.drawable.user_icon).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return leaderboardList.size();
    }

    class LeaderboardViewholder extends RecyclerView.ViewHolder {
        TextView level, name, score;
        CircleImageView image;

        public LeaderboardViewholder(View itemView) {
            super(itemView);
            level = itemView.findViewById(R.id.level);
            name = itemView.findViewById(R.id.name);
            score = itemView.findViewById(R.id.scoreTv);
            image = itemView.findViewById(R.id.image);
        }
    }
}
