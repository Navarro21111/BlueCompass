package com.example.a21736256.bluecompass;


import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a21736256.bluecompass.javabean.PlayaItem;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AniadirPlaya extends AppCompatActivity {

    private static final int GALLERY_INTENT=1;

    private Button btnSeleccionarImagen;
    private Button btnSubir;
    private EditText etNombrePlaya;
    private EditText etProvincia;
    private EditText etdescripcion;
    private ImageView imgPlaya;
    private ProgressBar progressBar;
    //Uri de la imagen
    private Uri imgUri;

    //Storage
    private StorageReference storageRef;
    private DatabaseReference databaseReference;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadir_playa);

        btnSeleccionarImagen= findViewById(R.id.btnSeleccionarPlaya);
        btnSubir=findViewById(R.id.btnSubirPlaya);
        etNombrePlaya=findViewById(R.id.etNombreAñadir);
        etProvincia= findViewById(R.id.etProvinciaAniadir);
        etdescripcion=findViewById(R.id.etDescripcionCP);
        imgPlaya=findViewById(R.id.imgFotoSleccionada);
        progressBar=findViewById(R.id.pbSubidaPlaya);

        //Storage
        storageRef=FirebaseStorage.getInstance().getReference("Playas/");
        databaseReference= FirebaseDatabase.getInstance().getReference("Playas");
    }

    public void seleccionarImagen(View view){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_INTENT);

    }

    public void subirPlaya(View view){
        if(imgUri!=null){
            StorageReference fileReference= storageRef.child(System.currentTimeMillis() + "." + getFileExtension(imgUri));

            //Para el progress bar
            fileReference.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler= new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(0);
                        }
                    }, 5000);
                    PlayaItem playa= new PlayaItem(etNombrePlaya)
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AniadirPlaya.this, "Hubo un error", Toast.LENGTH_LONG).show();
                }
            })
            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress=(100.0 *taskSnapshot.getBytesTransferred()/ taskSnapshot.getTotalByteCount());
                    progressBar.setProgress((int)progress);
                }
            });

        }else{
            Toast.makeText(this, "Ningun archivo seleccionado", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERY_INTENT && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imgUri=data.getData();

            Glide.with(this).load(imgUri).into(imgPlaya);
            }
    }
    //metodo para coger la extension de la imagen
    private String getFileExtension(Uri uri){
        ContentResolver cR= getContentResolver();
        MimeTypeMap mime= MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
}
