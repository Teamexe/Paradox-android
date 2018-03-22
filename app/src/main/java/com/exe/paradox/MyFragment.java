package com.exe.paradox;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by shasha on 18/3/18.
 */

@SuppressLint("ValidFragment")
public class MyFragment extends Fragment {
    int imagePath;

    public MyFragment(int imagePath) {
        super();
        this.imagePath = imagePath;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_image, container, false);
        ImageView imageView = rootView.findViewById(R.id.image);
        imageView.setImageDrawable(getActivity().getResources().getDrawable(imagePath));
        return rootView;
    }
}
