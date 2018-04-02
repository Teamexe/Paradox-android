package com.exe.paradox.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.exe.paradox.R;
import com.exe.paradox.model.Event;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

@SuppressLint("ValidFragment")
public class MyFragment extends Fragment {
    Event event;

    public MyFragment(Event event) {
        super();
        this.event = event;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_image, container, false);
        ImageView imageView = rootView.findViewById(R.id.image);

        Glide.with(getContext())
                .load(event.getPath())
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MaterialStyledDialog.Builder dialog = new MaterialStyledDialog.Builder(getContext())
                        .setHeaderDrawable(event.getPath())
                        .setHeaderScaleType(ImageView.ScaleType.CENTER_CROP)
                        .withDialogAnimation(false)
                        .setTitle(event.getName())
                        .setDescription(event.getDesc())
                        .setPositiveText("Go back")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        });
                dialog.build().show();
            }
        });
        return rootView;
    }
}
