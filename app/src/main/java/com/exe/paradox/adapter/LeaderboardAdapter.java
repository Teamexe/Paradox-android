package com.exe.paradox.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exe.paradox.R;

/**
 * Created by shasha on 23/3/18.
 */

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewholder> {
    class LeaderboardViewholder extends RecyclerView.ViewHolder{
        public LeaderboardViewholder(View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public LeaderboardViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LeaderboardViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_leaderboard, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardViewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }
}
