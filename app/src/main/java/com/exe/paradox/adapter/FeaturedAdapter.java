package com.exe.paradox.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.exe.paradox.R;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.ImageViewHolder>{
    private Context mContext;
    class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        private int[] drawables = {R.drawable.u1, R.drawable.u2, R.drawable.u3, R.drawable.u4, R.drawable.u5, R.drawable.u6, R.drawable.u7, R.drawable.u8};
        ImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }

    public FeaturedAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_featured_projects, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.imageView.setImageDrawable(mContext.getResources().getDrawable(holder.drawables[position]));
    }

    @Override
    public int getItemCount() {
        return 8;
    }
}
