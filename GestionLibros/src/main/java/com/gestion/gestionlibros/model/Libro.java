package com.gestion.gestionlibros.model;

public class Libro {

    private String nombre;
    private String categoria;
    private int ano;


    public Libro(String nombre, String categoria, int ano) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.ano = ano;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

}
