package com.gbjoanna.rocodromo.models;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.io.Serializable;

public class Ciudad implements Serializable {
    private String codigoPostal;
    private String nombre;
    private boolean operativa;
    private String provincia;
    private String comunidad;

    // Constructor
    public Ciudad(String codigoPostal, String nombre, boolean operativa, String provincia, String comunidad) {
        this.codigoPostal = codigoPostal;
        this.nombre = nombre;
        this.operativa = operativa;
        this.provincia = provincia;
        this.comunidad = comunidad;
    }

    // getters/setters


    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isOperativa() {
        return operativa;
    }

    public void setOperativa(boolean operativa) {
        this.operativa = operativa;
    }

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
