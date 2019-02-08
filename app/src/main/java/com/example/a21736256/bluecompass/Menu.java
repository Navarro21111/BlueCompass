package com.example.a21736256.bluecompass;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.a21736256.bluecompass.javabean.AdaptadorMenu;
import com.example.a21736256.bluecompass.javabean.PlayaItem;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {
    String remitente;


    private RecyclerView reyclerViewMenu;
    private AdaptadorMenu adaptadorMenu;
    private LinearLayoutManager llm;
    private ArrayList<PlayaItem> datos;

    private DatabaseReference dbr;
    private ChildEventListener cel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        reyclerViewMenu = findViewById(R.id.rvMenu);
        datos=new ArrayList<PlayaItem>();
        adaptadorMenu= new AdaptadorMenu(datos);
        llm=new LinearLayoutManager(this);

        reyclerViewMenu.setLayoutManager(llm);
        reyclerViewMenu.setAdapter(adaptadorMenu);
        reyclerViewMenu.setItemAnimator(new DefaultItemAnimator());

        dbr=FirebaseDatabase.getInstance().getReference().child("Playas");

        addChilEventListener();


    }

    private void addChilEventListener(){
        if (cel==null){
            cel= new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    System.out.println("Nueva Playa");
                    PlayaItem p = dataSnapshot.getValue(PlayaItem.class);
                    int pos=0;
                    for (int i=0;i< datos.size();i++){
                        if(datos.get(i).getNombre().equals(p.getNombre())){
                            datos.set(i,p);
                            pos=i;
                        }
                    }

                    adaptadorMenu.notifyItemChanged(pos);
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
}
