package com.secretlabs.spotify;

/**
 * Created by Mirko on 17-03-2015.
 */
public class Artista {

    String nombre;
    String descripcion;


    public Artista() {
        super();
    }

    public Artista(String nombre, String descripcion) {
        super();
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



}