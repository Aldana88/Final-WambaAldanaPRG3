package com.example.app.model;

public abstract class Socio {
    private int id;
    private String nombre;

    public Socio() {
    }

    public Socio(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public abstract double aplicarBeneficio(double monto);
}