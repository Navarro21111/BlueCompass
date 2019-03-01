package com.example.a21736256.bluecompass;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a21736256.bluecompass.javabean.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Registro extends AppCompatActivity {
    private EditText etUsuario;
    private EditText etCorreo;
    private EditText etContrasena;
    private EditText etRepeticion;
    private EditText etNacimiento;

    private Usuario usuario;

    private String nombreUsuario;
    private String correo;
    private String contrasenna;
    private String repetir;
    private String[] Compnacimiento;
    private int nacimiento;
    private FirebaseAuth fba;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        etUsuario = findViewById(R.id.etUsuario);
        etCorreo = findViewById(R.id.etCorreo);
        etContrasena= findViewById(R.id.etContrasenna);
        etRepeticion = findViewById(R.id.etRepetir);
        etNacimiento =findViewById(R.id.etAnnos);

        fba = FirebaseAuth.getInstance();


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


    private String validarDatos() {
        String msj = null;

        nombreUsuario =etUsuario.getText().toString().trim();
        correo =etCorreo.getText().toString().trim();
        contrasenna =etContrasena.getText().toString().trim();
        repetir =etRepeticion.getText().toString().trim();
        //Compnacimiento=etNacimiento.toString().split("/");
        nacimiento=Integer.parseInt(etNacimiento.getText().toString().trim());
        /*int dia=Integer.parseInt(Compnacimiento[0]);
        int mes=Integer.parseInt(Compnacimiento[1]);
        int año=Integer.parseInt(Compnacimiento[2]);
        Calendar edad=new GregorianCalendar(año,mes,dia);
        Calendar hoy=Calendar.getInstance();
        int años=hoy.get(Calendar.YEAR)-edad.get(Calendar.YEAR);
        edad.add(Calendar.YEAR,años);*/



        if(!contrasenna.equals(repetir)){
            //Toast.makeText(getBaseContext(),R.string.DistCont,Toast.LENGTH_LONG).show();
            msj=getString(R.string.DistCont);

        }
      /*  else if(años>1){
            msj="No es mayor de 18 años";
        }
*/


        else if(contrasenna.equals("")||nacimiento==0|| correo.equals("")||nombreUsuario.equals("")){
            msj=getString(R.string.CampoVacio);

            //Toast.makeText(getBaseContext(),R.string.CampoVacio,Toast.LENGTH_LONG).show();
        }
        else if (contrasenna.length() < 6) {
            msj=getString(R.string.contrDigit);

            //Toast.makeText(getBaseContext(),R.string.contrDigit,Toast.LENGTH_LONG).show();
        }
        else if(nacimiento<18){
            msj="Para entrar a esta aplicacion tienes que ser mayor de edad";
        }

        return msj;
    }

    public void enviar(View v){
        /**boolean comprobar = false;
        nombreUsuario =etUsuario.getText().toString();
        correo=etCorreo.getText().toString();
        contrasenna=etContrasena.getText().toString();
        repetir=etRepeticion.getText().toString();
        nacimiento=etNacimiento.getText().toString();

        usuario = new Usuario(nombreUsuario,correo,contrasenna,nacimiento);

        if(!contrasenna.equals(repetir)){
            Toast.makeText(getBaseContext(),R.string.DistCont,Toast.LENGTH_LONG).show();
        }
        else if(contrasenna.equals("")||nacimiento.equals("")||correo.equals("")||nombreUsuario.equals("")){
            Toast.makeText(getBaseContext(),R.string.CampoVacio,Toast.LENGTH_LONG).show();
        }
        else if (contrasenna.length() < 6) {
            Toast.makeText(getBaseContext(),R.string.contrDigit,Toast.LENGTH_LONG).show();
        }

        else{
            dbR.push().setValue(usuario);
            comprobar=true;


    }
        return comprobar;

         */

        String warning = validarDatos();
        if (warning == null) {
        fba.createUserWithEmailAndPassword(correo, contrasenna)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Usuario usuario = new Usuario(nombreUsuario, correo,nacimiento);
                            FirebaseDatabase.getInstance().getReference("Usuarios")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).
                                    setValue(usuario);


                            Intent i = new Intent(Registro.this, Menu.class);
                            startActivity(i);


                        } else {
                            Toast.makeText(Registro.this, getString(R.string.msj_no_registrado),                                        Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    } else {
        //Toast.makeText(this, getString(R.string.msj_no_data), Toast.LENGTH_LONG).show();
        Toast.makeText(this, warning,
                Toast.LENGTH_LONG).show();
    }
}

    }

