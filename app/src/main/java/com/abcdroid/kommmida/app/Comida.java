package com.abcdroid.kommmida.app;


public class Comida{

    public Long id;
    public String nombre;
    public String foto;
    public String descripcion;

    public Comida(Long id, String nombre, String foto, String descripcion) {
        this.id= id;
        this.nombre = nombre;
        this.foto = foto;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
