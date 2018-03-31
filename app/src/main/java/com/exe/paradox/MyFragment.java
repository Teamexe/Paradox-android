package com.exe.paradox;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;

/**
 * Created by shasha on 18/3/18.
 */

@SuppressLint("ValidFragment")
public class MyFragment extends Fragment {
    int imagePath, position;

    public MyFragment(int imagePath, int position) {
        super();
        this.position = position;
        this.imagePath = imagePath;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_image, container, false);
        ImageView imageView = rootView.findViewById(R.id.image);

        Glide.with(getContext())
             .load(imagePath)
             .into(imageView);

        final GPlusFragment gPlusFragment = new GPlusFragment();
        if(position == 7)
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                    alertDialog.setTitle("Log out?");
                    alertDialog.setMessage("Do you really want to log out?");
                    alertDialog.setPositiveButton("Yes, get me out!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //BECAUSE SIGN_IN TRIGGERS AT ONSTART() OF GPlusFragment so re-login will happen again and again
                            gPlusFragment.signOut();
                            startActivity(new Intent(getContext(), LoginActivity.class));
                            getActivity().finish();
                        }
                    });
                    alertDialog.setNegativeButton("I clicked it by mistake, Take me back :)", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertDialog.show();
                }
            });
        return rootView;
    }
}
