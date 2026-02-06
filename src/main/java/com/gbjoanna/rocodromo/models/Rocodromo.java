package com.gbjoanna.rocodromo.models;

import java.io.Serializable;

public class Rocodromo implements Serializable {

    private String codigo;
    private boolean completa;
    private String direccion;
    private int capacidad;
    private int dificultad;

    public Rocodromo (String codigo, boolean completa, String direccion, int capacidad, int dificultad) {

        this.codigo = codigo;
        this.completa = completa;
        this.direccion = direccion;
        this.capacidad = capacidad;
        this.dificultad = dificultad;
    }

    public String getCodigo () {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isCompleta() {
        return completa;
    }

    public void setCompleta(boolean completa) {
        this.completa = completa;
    }

    public String getDireccion () {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }
}
