package com.example.a21736256.bluecompass;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    Button btnRegistroPlaya;
    private Toolbar toolbar;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.appbar);

        setActionBar(toolbar);


    }
    public void logIn(View view){
        Intent i = new Intent(this, LogIn.class);
        startActivity(i);
    }

    public void registro(View view){
        Intent i = new Intent(this, Registro.class);
        startActivity(i);
    }


}
