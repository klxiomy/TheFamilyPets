package com.kristinxiomara.thefamilypets.data;

public class AyudaModel {
    String descripcion;
    String email;
    String tipo;
    String ubicacion;
    String image;

    public AyudaModel() {
    }

    public AyudaModel(String descripcion, String email, String tipo, String ubicacion, String image) {
        this.descripcion = descripcion;
        this.email = email;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.image = image;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
