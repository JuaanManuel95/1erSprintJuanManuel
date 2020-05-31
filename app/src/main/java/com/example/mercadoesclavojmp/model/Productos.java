package com.example.mercadoesclavojmp.model;

import java.io.Serializable;

public class Productos implements Serializable {

    private String nombre;
    private Integer imagen;
    private String precio;
    private String descripcion;

    public Productos(String nombre, Integer imagen, String precio, String descripcion) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getImagen() {
        return imagen;
    }

    public void setImagen(Integer imagen) {
        this.imagen = imagen;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
