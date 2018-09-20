package com.movilbox.lector.Referencias.Objetos;

import java.util.ArrayList;
import java.util.List;

public class Libro {
    private String titulo, historia, id;
    private List<Personaje> listaPersonajes;
    private List<Lugar> listaLugares;
    private List<Suceso> listaCronogia;
    private List<Nota> listaNotas;

    public Libro() {
        this.titulo = "";
        this.historia = "";
        this.listaPersonajes = new ArrayList<Personaje>();
        this.listaLugares =  new ArrayList<Lugar>();
        this.listaCronogia = new ArrayList<Suceso>();
        this.listaNotas = new ArrayList<Nota>();
    }

    public Libro(String titulo, String historia) {
        this.titulo = titulo;
        this.historia = historia;
        this.listaPersonajes = new ArrayList<Personaje>();
        this.listaLugares =  new ArrayList<Lugar>();
        this.listaCronogia = new ArrayList<Suceso>();
        this.listaNotas = new ArrayList<Nota>();
    }

    public Libro(String titulo, String historia, List<Personaje> listaPersonajes, List<Lugar> listaLugares, List<Suceso> listaCronoogia, List<Nota> listaNotas) {
        this.titulo = titulo;
        this.historia = historia;
        this.listaPersonajes = listaPersonajes;
        this.listaLugares = listaLugares;
        this.listaCronogia = listaCronoogia;
        this.listaNotas = listaNotas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String texto) {
        this.historia = texto;
    }

    public void setListaPersonajes(List<Personaje> listaPersonajes) {
        this.listaPersonajes = listaPersonajes;
    }

    public void setListaLugares(List<Lugar> listaLugares) {
        this.listaLugares = listaLugares;
    }

    public void setListaCronogia(List<Suceso> listaCronogia) {
        this.listaCronogia = listaCronogia;
    }

    public void setListaNotas(List<Nota> listaNotas) {
        this.listaNotas = listaNotas;
    }

    public List<Personaje> getListaPersonajes() {
        return listaPersonajes;
    }

    public List<Lugar> getListaLugares() {
        return listaLugares;
    }

    public List<Suceso> getListaCronogia() {
        return listaCronogia;
    }

    public List<Nota> getListaNotas() {
        return listaNotas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
