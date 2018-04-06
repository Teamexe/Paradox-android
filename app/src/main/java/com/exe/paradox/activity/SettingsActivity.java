package com.exe.paradox.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.exe.paradox.R;
import com.exe.paradox.util.Preferences;
import com.gjiazhe.panoramaimageview.GyroscopeObserver;
import com.gjiazhe.panoramaimageview.PanoramaImageView;

public class SettingsActivity extends AppCompatActivity {
    private GyroscopeObserver gyroscopeObserver;
    private TextView writeToUs;
    private Switch notificationSwitch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        gyroscopeObserver = new GyroscopeObserver();
        gyroscopeObserver.setMaxRotateRadian(Math.PI / 3);

        PanoramaImageView panoramaImageView = findViewById(R.id.panorama_image_view);
        writeToUs = findViewById(R.id.mail_to_us);
        notificationSwitch = findViewById(R.id.notification_switch);
        panoramaImageView.setGyroscopeObserver(gyroscopeObserver);
        writeToUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:teamexenith@gmail.com"));
                startActivity(emailIntent);
            }
        });

        if (Preferences.getNotification(this)) {
            notificationSwitch.setChecked(true);
        } else {
            notificationSwitch.setChecked(false);
        }

        notificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Preferences.setNotification(SettingsActivity.this, true);
                }
                else {
                    Preferences.setNotification(SettingsActivity.this, false);
                }
            }
        });
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
