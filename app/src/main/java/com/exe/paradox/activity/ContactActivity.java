package com.exe.paradox.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.exe.paradox.R;
import com.gjiazhe.panoramaimageview.GyroscopeObserver;
import com.gjiazhe.panoramaimageview.PanoramaImageView;

public class ContactActivity extends AppCompatActivity {
    private GyroscopeObserver gyroscopeObserver;
    private TextView writeToUs, numAnkit, numShasha;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        gyroscopeObserver = new GyroscopeObserver();
        gyroscopeObserver.setMaxRotateRadian(Math.PI / 3);

        PanoramaImageView panoramaImageView = findViewById(R.id.panorama_image_view);
        writeToUs = findViewById(R.id.mail_to_us);
        numAnkit = findViewById(R.id.num_ankit);
        numShasha = findViewById(R.id.num_shasha);
        panoramaImageView.setGyroscopeObserver(gyroscopeObserver);
        writeToUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:teamexenith@gmail.com"));
                startActivity(emailIntent);
            }
        });

        numAnkit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call(numAnkit.getText().toString());
            }
        });

        numShasha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call(numShasha.getText().toString());
            }
        });
    }

    public void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phone));
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gyroscopeObserver.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gyroscopeObserver.unregister();
    }
}
