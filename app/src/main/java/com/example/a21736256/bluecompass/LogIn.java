package com.example.a21736256.bluecompass;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {

    private FirebaseAuth fba;
    private FirebaseUser user;
    EditText etUser;
    EditText etPassword;
    String nombre;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_activity);

        fba = FirebaseAuth.getInstance();

        etUser = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.contraseñaLogIn);
    }

    public void irMenu(View view) {
        Intent i = new Intent(this, Menu.class);
        startActivity(i);
    }

    private String validarDatos() {
        String msj = null;

        nombre = etUser.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        if (nombre.isEmpty() || password.isEmpty()) {
            msj = "Debe introducir una contraseña";
        } else if (password.length() < 6) {
            msj = "La contraseña tiene que tener mínimo 6 caracteres";
        }

        return msj;
    }



   /* public void acceder(View v) {
        //if (validarDatos()) {
        String warning = validarDatos();
        if (warning == null) {
            fba.signInWithEmailAndPassword(nombre, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                user = fba.getCurrentUser();
                                Toast.makeText(LogIn.this,
                                        "Usuario logado "+ user.getNombre(),
                                        Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(LogIn.this, MainActivity.class);
                                i.putExtra("USER", user.getNombre());
                                startActivity(i);
                            } else {
                                Toast.makeText(LogIn.this,
                                        "No se puede accerder",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        } else {
            //Toast.makeText(this, getString(R.string.msj_no_data), Toast.LENGTH_LONG).show();
            Toast.makeText(this, warning,
                    Toast.LENGTH_LONG).show();
        }*/

}
