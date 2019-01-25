package com.example.a21736256.bluecompass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_activity);
    }
    public void irMenu(View view){
        Intent i = new Intent(this, Menu.class);
        startActivity(i);
    }
}
