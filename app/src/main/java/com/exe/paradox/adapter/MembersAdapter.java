package com.exe.paradox.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.exe.paradox.Member;
import com.exe.paradox.R;
import com.squareup.picasso.Picasso;

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
    public void onBindViewHolder(@NonNull MembersViewHolder holder, int position) {
        Glide.with(mContext).load(memberList.get(position).getDrawable()).into(holder.imageView);
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
