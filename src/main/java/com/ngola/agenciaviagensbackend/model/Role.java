package com.ngola.agenciaviagensbackend.model;

public enum Role {
    ADMIN(0),
    GESTOR(1),
    USER(2);
    int value;

    Role(int valor){
        this.value = valor;
    }
}
