package com.exe.paradox.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.exe.paradox.Project;
import com.exe.paradox.R;

import java.util.List;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.ImageViewHolder>{
    private Context mContext;
    private List<Project> projects;
    class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }

    public FeaturedAdapter(Context mContext, List<Project> projects) {
        this.mContext = mContext;
        this.projects = projects;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_featured_projects, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.imageView.setImageDrawable(mContext.getResources().getDrawable(projects.get(position).getDrawablePath()));
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }
}
