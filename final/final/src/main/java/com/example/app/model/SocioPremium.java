package com.example.app.model;

public class SocioPremium extends Socio {

    public SocioPremium() {
    }

    public SocioPremium(int id, String nombre) {
        super(id, nombre);
    }

    @Override
    public double aplicarBeneficio(double monto) {
        return monto * 0.5;
    }
}