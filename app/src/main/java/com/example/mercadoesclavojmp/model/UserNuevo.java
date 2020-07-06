package com.example.mercadoesclavojmp.model;

import java.io.Serializable;
import java.util.List;

public class UserNuevo  {

    private String nombre;
    private String apellido;
    private String username;
    private String email;
    private List<Producto> favoritos;

    public UserNuevo(){

    }


    public UserNuevo(String nombre, String apellido, String username, String email, List<Producto> favoritos) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.email = email;
        this.favoritos = favoritos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Producto> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<Producto> favoritos) {
        this.favoritos = favoritos;
    }
}


