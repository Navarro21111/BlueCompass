package com.example.a21736256.bluecompass;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AniadirPlaya extends AppCompatActivity {

    private Button btnSfP;
    //Storage
    private StorageReference rStorage;
    private FirebaseStorage mFirebaseStorage;

    public static final int GALLERY_INTENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadir_playa);

        //Storage
        rStorage=FirebaseStorage.getInstance().getReference();

        btnSfP=findViewById(R.id.btnSubirFoto);


    }

    public void selccionarFoto(View view){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_INTENT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALLERY_INTENT && resultCode==RESULT_OK){
            Uri uri=data.getData();

            StorageReference filePath = rStorage.child("Playas").child(uri.getLastPathSegment());

            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(AniadirPlaya.this, "Se subio la foto correctamente", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
