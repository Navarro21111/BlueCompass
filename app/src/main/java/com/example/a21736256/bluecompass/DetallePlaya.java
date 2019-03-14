package com.example.a21736256.bluecompass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a21736256.bluecompass.javabean.PlayaItem;

public class DetallePlaya extends AppCompatActivity {

    String nombre;
    PlayaItem playa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_playa);

        playa=getIntent().getParcelableExtra("PLAYA");
        System.out.println(playa.getNombre());
    }
}
