package com.miage.miagiquespring.utilities;

public class BilletInexistant extends RuntimeException {
    public BilletInexistant(String message) {
        super(message);
    }
}
