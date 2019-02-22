package com.example.a21736256.bluecompass;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LogIn extends AppCompatActivity {
    EditText etPrueba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_activity);
        etPrueba = findViewById(R.id.etUsuario);

    }
    public void irMenu(View view){
        Intent i = new Intent(this, CrearGrupo.class);
        startActivity(i);
    }
}
