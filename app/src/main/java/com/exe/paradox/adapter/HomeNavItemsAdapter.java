package com.exe.paradox.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exe.paradox.R;

import java.util.ArrayList;


public class HomeNavItemsAdapter extends RecyclerView.Adapter<HomeNavItemsAdapter.HomeNavViewHolder> {
    class HomeNavViewHolder extends RecyclerView.ViewHolder{
        public HomeNavViewHolder(View itemView) {
            super(itemView);
        }
    }
    ArrayList<String> titles;

    public HomeNavItemsAdapter() {
        titles = new ArrayList<>();
        titles.add("Home");
        titles.add("Leaderboard");
        titles.add("Paradox");
        titles.add("Stats");
        titles.add("Members");
    }

    @NonNull
    @Override
    public HomeNavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeNavViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nav, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeNavViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
