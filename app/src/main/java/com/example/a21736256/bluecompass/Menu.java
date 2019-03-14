package com.example.a21736256.bluecompass;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a21736256.bluecompass.javabean.AdaptadorMenu;
import com.example.a21736256.bluecompass.javabean.PlayaItem;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {

    Button btnConsultarP;
    EditText etConsultarP;
    RecyclerView rvMenu;
    AdaptadorMenu adaptador;
    LinearLayoutManager llm;
    ArrayList<PlayaItem> datos;

    //database
    private DatabaseReference dbr;
    private ChildEventListener cel;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        rvMenu = findViewById(R.id.rvMenuPlaya);
        btnConsultarP = findViewById(R.id.btnConsultarM);
        etConsultarP = findViewById(R.id.etConsultarPlaya);

        dbr = FirebaseDatabase.getInstance().getReference().child("Playas");
        addChildEventListener();

        datos = new ArrayList<>();
        adaptador = new AdaptadorMenu(datos);
        llm = new LinearLayoutManager(this);


        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rvMenu.setLayoutManager(llm);
        rvMenu.setAdapter(adaptador);
        rvMenu.setItemAnimator(new DefaultItemAnimator());


        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference().child("Playas");


    }


    private void addChildEventListener() {
        if (cel == null) {
            cel = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    PlayaItem p = dataSnapshot.getValue(PlayaItem.class);
                    datos.add(p);
                    adaptador.notifyItemChanged(datos.size() - 1);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            };
            dbr.addChildEventListener(cel);
        }
    }

    public void consultarPlaya(View view) {
        Intent i = new Intent(Menu.this, DetallePlaya.class);
        PlayaItem playa= new PlayaItem(datos.get(1).getNombre(), datos.get(1).getZona(), datos.get(1).getDescripcion(),
                datos.get(1).getImagen());
        i.putExtra("PLAYA",playa );
        startActivity(i);

    }

}
