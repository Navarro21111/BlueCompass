package com.example.a21736256.bluecompass.javabean;

import android.media.Image;

public class PlayaItem {
    private int id;
    private String nombre;
    private String provincia;
    private String descripcion;
    private String imagen;

    public PlayaItem(String nombre, String provincia, String descripcion, String imagen) {
        this.nombre = nombre;
        this.provincia = provincia;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
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

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
