package com.example.a21736256.bluecompass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class MainActivity extends AppCompatActivity {

    Button tbn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
