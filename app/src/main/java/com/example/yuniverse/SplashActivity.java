package com.example.yuniverse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private TextView textAppName;
    private Handler handler;
    private Animation textViewAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        textAppName = findViewById(R.id.text_app_name);
        textViewAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.textview_animation);
        textAppName.setAnimation(textViewAnimation);


//Statusbar ve home tuşlarını gizler, aktiviteyi tam ekran yapar
        View decorView = getWindow().getDecorView();

        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;

        decorView.setSystemUiVisibility(uiOptions|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        handler = new Handler();


        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this, IntroActivity.class);
                startActivity(intent);
                finish();
            }
        };

        handler.postDelayed(runnable,12000);

    }
}
