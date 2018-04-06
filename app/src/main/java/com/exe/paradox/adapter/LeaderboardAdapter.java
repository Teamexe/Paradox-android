package com.exe.paradox.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.exe.paradox.activity.DpActivity;
import com.exe.paradox.R;
import com.exe.paradox.api.model.Leaderboard;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewholder> {
    private List<Leaderboard> leaderboardList;
    private Context mContext;

    public LeaderboardAdapter(List<Leaderboard> leaderboardList, Context mContext) {
        this.leaderboardList = leaderboardList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public LeaderboardViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LeaderboardViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_leaderboard, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final LeaderboardViewholder holder, final int position) {
        holder.name.setText(leaderboardList.get(position).getName().split(" ")[0]);

        if (leaderboardList.get(position).getScore() < 0)
            holder.score.setText("0");
        else
            holder.score.setText(String.valueOf(leaderboardList.get(position).getScore()));
        holder.level.setText(String.valueOf(leaderboardList.get(position).getLevel()));
        Picasso.get().load(leaderboardList.get(position).getPicture()).placeholder(R.drawable.user_icon).into(holder.image, new Callback() {
            @Override
            public void onSuccess() {

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            holder.image.setTransitionName("fuckYou");
                            Intent intent = new Intent(mContext, DpActivity.class).putExtra(Intent.EXTRA_TEXT, leaderboardList.get(position).getPicture());

                            ActivityOptionsCompat options = ActivityOptionsCompat.
                                    makeSceneTransitionAnimation((AppCompatActivity)mContext,
                                            holder.image,
                                            mContext.getString(R.string.transition_string));

                            mContext.startActivity(intent, options.toBundle());
                        }
                    }
                });
            }

            @Override
            public void onError(Exception e) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "No Image, bruh!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
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
