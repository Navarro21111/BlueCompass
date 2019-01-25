package com.example.a21736256.bluecompass.javabean;

public class Usuario {
    private String nombre;
    private String correo;
    private String contraseña;
    private String nacimiento;

    public Usuario(String nombre, String correo, String contraseña, String nacimiento) {
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

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }
}
