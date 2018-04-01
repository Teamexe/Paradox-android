package com.exe.paradox.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.exe.paradox.Member;
import com.exe.paradox.R;

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
        holder.imageView.setImageDrawable(mContext.getResources().getDrawable(memberList.get(position).getDrawable()));
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
