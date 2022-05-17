package com.kristinxiomara.thefamilypets.data;

public class EncontradaModel {
    String FirstName;
    String LastName;
    String tipo;
    String image;

    public EncontradaModel() {
    }

    public EncontradaModel(String firstName, String lastName, String tipo, String image) {
        FirstName = firstName;
        LastName = lastName;
        this.tipo = tipo;
        this.image = image;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
