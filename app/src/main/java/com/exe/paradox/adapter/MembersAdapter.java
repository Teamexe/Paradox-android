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
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.exe.paradox.R;
import com.exe.paradox.activity.DpActivity;
import com.exe.paradox.activity.MemberDisplayActivity;
import com.exe.paradox.activity.MembersActivity;
import com.exe.paradox.model.Member;

import java.util.List;

public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.MembersViewHolder> {
    private Context mContext;
    private List<Member> memberList;

    public MembersAdapter(Context context, List<Member> members) {
        mContext = context;
        this.memberList = members;
    }

    @NonNull
    @Override
    public MembersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MembersViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orange, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MembersViewHolder holder, int position) {
        Glide.with(mContext).load(memberList.get(position).getDrawable()).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.imageView.setTransitionName("fuckYou");
                }
                Intent intent = new Intent(mContext, MemberDisplayActivity.class).putExtra(Intent.EXTRA_TEXT, memberList.get(holder.getAdapterPosition()));

                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((AppCompatActivity)mContext,
                                holder.imageView,
                                mContext.getString(R.string.transition_string));

                mContext.startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    class MembersViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MembersViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
