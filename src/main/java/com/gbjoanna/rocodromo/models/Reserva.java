package com.gbjoanna.rocodromo.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Reserva implements Serializable {
    private float precio;
    private String nReserva;
    private LocalDate fechaReserva;
    private String estado;
    private String tipoPago;


    // Constructor
    public Reserva(float precio, String nReserva, LocalDate fechaReserva, String estado, String tipoPago) {
        this.precio = precio;
        this.nReserva = nReserva;
        this.fechaReserva = fechaReserva;
        this.estado = estado;
        this.tipoPago = tipoPago;
    }

    // Crear los estados de la reserva
    private static final ArrayList<String> estadoReserva = new ArrayList<>(
            Arrays.asList(
                    "Cancelada",
                    "Reservada",
                    "Disfrutada"
            )
    );

    public static ArrayList<String> getEstadoReserva() {
        return estadoReserva;
    }
    // Crear los tipos de pago
    private static final ArrayList<String> tiposPagos = new ArrayList<>(
            Arrays.asList(
                    "Efectivo",
                    "Tarjeta"
            )
    );

    public static ArrayList<String> getTiposPagos() {
        return tiposPagos;
    }
    // getters/setters
    public float getPrecio(){
        return precio;
    }
    public void setPrecio(float precio){
        this.precio = precio;
    }

    public String getnReserva() {
        return nReserva;
    }
    public void setnReserva(String nReserva) {
        this.nReserva = nReserva;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }
    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }
}

