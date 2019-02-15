package com.example.a21736256.bluecompass;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a21736256.bluecompass.javabeans.evento;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CrearGrupo extends AppCompatActivity {

    EditText etFecha;
    EditText etNombre;
    EditText etMapa;
    TextView tvJfoo;


    private DatabaseReference dbR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_grupo);



        etFecha=findViewById(R.id.etFechaGrupo);
        etNombre=findViewById(R.id.etNombreGrupo);
        tvJfoo=findViewById(R.id.tvNombreGrupo);


        dbR = FirebaseDatabase.getInstance().getReference().child("mensaje");


    }
    public void insertarEvento(View v){
            evento ev = new evento(etFecha.getText().toString(),etNombre.getText().toString(),etMapa.getText().toString());
            String clave = dbR.push().getKey();
            dbR.child(clave).setValue(ev);

            etFecha.setText("");
            etMapa.setText("");
            etNombre.setText("");


    }

}
