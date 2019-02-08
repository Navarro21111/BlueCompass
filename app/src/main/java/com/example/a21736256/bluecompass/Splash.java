package com.example.a21736256.bluecompass;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    ImageView imgSplashLogo;
    TextView tvNombreApp;
    TextView tvSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imgSplashLogo=findViewById(R.id.imgLogoSplah);
        tvNombreApp=findViewById(R.id.tvSlogan);
        tvSlogan=findViewById(R.id.tvNombreApp);

        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.anim_splash);

        imgSplashLogo.startAnimation(myanim);
        tvNombreApp.startAnimation(myanim);
        tvSlogan.startAnimation(myanim);
        openApp(true);
    }

    private void openApp(boolean b) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash
                        .this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }
}
