package com.kristinxiomara.thefamilypets.data;

public class DenunciaModel {
    String Titulo;
    String Descripcion;
    String image;

    public DenunciaModel() {
    }

    public DenunciaModel(String titulo, String descricion, String image) {
        Titulo = titulo;
        Descripcion = descricion;
        this.image = image;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDescricion() {
        return Descripcion;
    }

    public void setDescricion(String descricion) {
        Descripcion = descricion;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
