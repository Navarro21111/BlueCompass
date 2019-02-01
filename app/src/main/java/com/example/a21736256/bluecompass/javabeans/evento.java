package com.example.a21736256.bluecompass.javabeans;

import java.util.ArrayList;

public class evento {
    String fecha;
    String playa;
    String nombre;
    String provincia;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPlaya() {
        return playa;
    }

    public void setPlaya(String playa) {
        this.playa = playa;
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

    public evento(String fecha, String playa, String nombre) {
        this.fecha = fecha;
        this.playa = playa;
        this.nombre = nombre;
    }
}
