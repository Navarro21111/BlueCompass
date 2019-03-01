package com.example.a21736256.bluecompass.javabean;

public class Usuario {
    public Usuario() {
    }

    public Usuario(String nombre, String correo, int nacimiento) {
        this.nombre = nombre;
        this.correo = correo;
        this.nacimiento = nacimiento;
    }

    private String nombre;
    private String correo;
    private String contraseña;
    private int nacimiento;

    public Usuario(String nombre, String correo, String contraseña, int nacimiento) {
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.nacimiento = nacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(int nacimiento) {
        this.nacimiento = nacimiento;
    }
}
