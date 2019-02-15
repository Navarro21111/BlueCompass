package com.example.a21736256.bluecompass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a21736256.bluecompass.javabean.Usuario;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class Registro extends AppCompatActivity {
    private EditText etUsuario;
    private EditText etCorreo;
    private EditText etContrasena;
    private EditText etRepeticion;
    private EditText etNacimiento;

    private Usuario usuario;

    private String nombreUsuario;
    private  String Correo;
    private  String Contrasenna;
    private  String Repetir;
    private  String nacimiento;
    private DatabaseReference dbR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        etUsuario = findViewById(R.id.etUsuario);
        etCorreo = findViewById(R.id.etCorreo);
        etContrasena= findViewById(R.id.etContrasenna);
        etRepeticion = findViewById(R.id.etRepetir);
        etNacimiento =findViewById(R.id.etNac);

        dbR = FirebaseDatabase.getInstance().getReference().child("Usuarios");


    }
    /**public void onClick(View view) {
        switch (view.getId()) {
            case R.id.etPlannedDate:
                showDatePickerDialog();
                break;
        }
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = new DatePickerFragment();

        newFragment.show(getSupportFragmentManager(), "datePicker");

    }*/
    public void irMenu(View view){
            boolean comprobar=enviar();
            if(comprobar==true){
                Intent i = new Intent(this, CrearGrupo.class);
                startActivity(i);
            }



        }



    public boolean enviar(){
        boolean comprobar = false;
        nombreUsuario =etUsuario.getText().toString();
        Correo=etCorreo.getText().toString();
        Contrasenna=etContrasena.getText().toString();
        Repetir=etRepeticion.getText().toString();
        nacimiento=etNacimiento.getText().toString();

        usuario = new Usuario(nombreUsuario,Correo,Contrasenna,nacimiento);

        if(!Contrasenna.equals(Repetir)){
            Toast.makeText(getBaseContext(),R.string.DistCont,Toast.LENGTH_LONG).show();
        }
        else if(Contrasenna.equals("")||nacimiento.equals("")||Correo.equals("")||nombreUsuario.equals("")){
            Toast.makeText(getBaseContext(),R.string.CampoVacio,Toast.LENGTH_LONG).show();
        }

        else{
            dbR.push().setValue(usuario);
            comprobar=true;


    }
        return comprobar;


    }}
