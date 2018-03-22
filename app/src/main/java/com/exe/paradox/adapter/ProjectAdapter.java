package com.exe.paradox.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.exe.paradox.R;

/**
 * Created by shasha on 23/3/18.
 */

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {
    private Context mContext;
    private int[] drawables = {R.drawable.u1, R.drawable.u2, R.drawable.u3, R.drawable.u4, R.drawable.u5, R.drawable.u6, R.drawable.u7, R.drawable.u8, R.drawable.one};

    public ProjectAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProjectViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orange, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        holder.imageView.setImageDrawable(mContext.getResources().getDrawable(drawables[position]));
    }

    @Override
    public int getItemCount() {
        return drawables.length;
    }

    class ProjectViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ProjectViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
