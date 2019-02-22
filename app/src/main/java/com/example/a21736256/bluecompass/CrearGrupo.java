package com.example.a21736256.bluecompass;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.Typeface;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a21736256.bluecompass.javabeans.evento;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CrearGrupo extends AppCompatActivity implements OnMapReadyCallback {

    EditText etFecha;
    EditText etNombre;
    EditText etMapa;
    private GoogleMap mMap;

    private FusedLocationProviderClient flpc;
    private Location localizacion;


    private DatabaseReference dbR;
    private GoogleMap mapa;
    private static final int PETICION_PERMISO_LOCALIZACION = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_grupo);


        etFecha = findViewById(R.id.etFechaGrupo);
        etNombre = findViewById(R.id.etNombreGrupo);


        dbR = FirebaseDatabase.getInstance().getReference().child("mensaje");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        /* DEPRECADO
        gac = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,
                        this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();*/

        flpc = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // si no los tenemos los solicitamos
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    PETICION_PERMISO_LOCALIZACION);
        } else {
            // si tenemos los permisos
            flpc.getLastLocation().addOnSuccessListener(this,
                    new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                localizacion = location;
                            }
                        }
                    });
        }


    }


    public void insertarEvento(View v) {
        evento ev = new evento(etFecha.getText().toString(), etNombre.getText().toString(), etMapa.getText().toString());
        String clave = dbR.push().getKey();
        dbR.child(clave).setValue(ev);

        etFecha.setText("");
        etMapa.setText("");
        etNombre.setText("");


    }

    @Override

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        /*LatLng ibiza = new LatLng(38.9, 1.4);
        mMap.addMarker(new MarkerOptions().position(ibiza).title("Ibiza"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ibiza));

        CameraPosition camPos = new CameraPosition.Builder()
                .target(new LatLng(40.7, -3.7))
                .zoom(10)
                .build();

        CameraUpdate cu = CameraUpdateFactory.newCameraPosition(camPos);
        mMap.animateCamera(cu);*/

        if (localizacion != null) {
            LatLng miLocalizacion = new LatLng(localizacion.getLatitude(),
                    localizacion.getLongitude());
            mMap.addMarker(new MarkerOptions().position(miLocalizacion).title("Mi localización"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(miLocalizacion));
        } else {
            LatLng ibiza = new LatLng(38.9, 1.4);
            mMap.addMarker(new MarkerOptions().position(ibiza).title("Ibiza"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(ibiza));

            Toast.makeText(this, "No se ha podido obtener la localización",
                    Toast.LENGTH_LONG).show();
        }

    }


    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PETICION_PERMISO_LOCALIZACION) {
            if (grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Permiso concedido
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                flpc.getLastLocation().addOnSuccessListener(this,
                        new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if (location != null) {
                                    localizacion = location;
                                }
                            }
                        });

            } else {
                //Permiso denegado:
                //Deberíamos deshabilitar toda la funcionalidad relativa a la localización.
                // mostramos en el log un mensaje de error
                Log.e("Localización de Android", "Permiso denegado");
            }
        }
    }

    /* DEPRECADO
    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            localizacion = LocationServices.FusedLocationApi.getLastLocation(gac);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "La conexión ha fallado",
                Toast.LENGTH_LONG).show();
    }*/
}



