package com.kristinxiomara.thefamilypets.data;

public class PerdidaModel {
    private String nombre;
    private String raza;
    private String descripcion;
    private String tipo;
    private String diasP;
    private String foto;
    private String color;

    public PerdidaModel(){

    }

    public PerdidaModel(String nombre, String raza, String descripcion, String tipo, String diasP, String foto, String color) {
        this.nombre = nombre;
        this.raza = raza;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.diasP = diasP;
        this.foto = foto;
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDiasP() {
        return diasP;
    }

    public void setDiasP(String diasP) {
        this.diasP = diasP;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
