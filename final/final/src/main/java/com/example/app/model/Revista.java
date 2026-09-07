package com.example.app.model;

public class Revista extends Material {
    private int numeroEdicion;

    public Revista() {
    }

    public Revista(String codigo, String titulo, boolean disponible, int numeroEdicion) {
        super(codigo, titulo, disponible);
        this.numeroEdicion = numeroEdicion;
    }

    public int getNumeroEdicion() {
        return numeroEdicion;
    }

    public void setNumeroEdicion(int numeroEdicion) {
        this.numeroEdicion = numeroEdicion;
    }
}