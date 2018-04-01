package com.exe.paradox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.exe.paradox.util.TouchImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ImageZoomActivity extends AppCompatActivity {

    TouchImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_zoom);
        imageView = findViewById(R.id.image);

        supportPostponeEnterTransition();

        Picasso.get()
                .load(QuestionActivity.url)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        supportStartPostponedEnterTransition();
                    }

                    @Override
                    public void onError(Exception e) {
                        supportStartPostponedEnterTransition();
                    }
                });
    }
}
