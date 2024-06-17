package com.miage.miagiquespring.utilities;

public class BilletNonDisponible extends RuntimeException {
    public BilletNonDisponible(String message) {
        super(message);
    }
}
