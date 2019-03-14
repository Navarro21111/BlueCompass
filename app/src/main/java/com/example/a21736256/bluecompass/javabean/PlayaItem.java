package com.example.a21736256.bluecompass.javabean;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

public class PlayaItem implements Parcelable {

    private String nombre;
    private String zona;
    private String descripcion;
    private String imagen;




    public PlayaItem() {
    }

    public PlayaItem(String nombre, String zona, String descripcion, String imagen) {
        this.nombre = nombre;
        this.zona = zona;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    protected PlayaItem(Parcel in) {
        nombre = in.readString();
        zona = in.readString();
        descripcion = in.readString();
        imagen = in.readString();
    }

    public static final Creator<PlayaItem> CREATOR = new Creator<PlayaItem>() {
        @Override
        public PlayaItem createFromParcel(Parcel in) {
            return new PlayaItem(in);
        }

        @Override
        public PlayaItem[] newArray(int size) {
            return new PlayaItem[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String provincia) {
        this.zona = zona;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(zona);
        dest.writeString(descripcion);
        dest.writeString(imagen);

    }
}
