package com.example.app.strategy;

public class MultaCampania implements MultaStrategy {
    @Override
    public double calcular(int diasAtraso) {
        return diasAtraso * 60.0;
    }
}