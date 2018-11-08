package com.movilbox.lector.Referencias.Objetos;

import java.util.List;

public class Personaje {
    private String nombre, genero, nacionalidad, marcas, personalidad, id;
    private int edad;
    private double altura;

    public Personaje() {

    }

    public Personaje(String nombre, String genero, String nacionalidad, String marcas, String personalidad, int edad, double altura) {
        this.nombre = nombre;
        this.genero = genero;
        this.nacionalidad = nacionalidad;
        this.marcas = marcas;
        this.personalidad = personalidad;
        this.edad = edad;
        this.altura = altura;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getMarcas() {
        return marcas;
    }

    public void setMarcas(String marcas) {
        this.marcas = marcas;
    }

    public String getPersonalidad() {
        return personalidad;
    }

    public void setPersonalidad(String personalidad) {
        this.personalidad = personalidad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

