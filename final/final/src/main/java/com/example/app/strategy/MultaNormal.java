package com.example.app.strategy;

public class MultaNormal implements MultaStrategy {
    @Override
    public double calcular(int diasAtraso) {
        return diasAtraso * 100.0;
    }
}