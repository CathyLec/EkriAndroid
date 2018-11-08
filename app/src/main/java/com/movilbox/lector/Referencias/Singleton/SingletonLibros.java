package com.movilbox.lector.Referencias.Singleton;

import com.movilbox.lector.Referencias.Objetos.Libro;

import java.util.ArrayList;
import java.util.List;

public class SingletonLibros {
    private static final SingletonLibros ourInstance = new SingletonLibros();
    private List<Libro> listaLibros;
    private int libroSeleccionado;
    private int personajeSeleccionado;
    private int notaSeleccionada;
    private int lugarSeleccionado;
    private int sucesoSeleccionado;

    public static SingletonLibros getInstance() {
        return ourInstance;
    }

    private SingletonLibros() {

        if (ourInstance == null){
            listaLibros = new ArrayList<>();
            libroSeleccionado = -1;
            personajeSeleccionado = -1;
            notaSeleccionada = -1;
            lugarSeleccionado = -1;
            sucesoSeleccionado = -1;
        }

    }


    public List<Libro> getListaLibros() {
        return listaLibros;
    }

    public void setListaLibros(List<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

    public void eliminarLista(){
        listaLibros.remove(listaLibros);
    }

    public void agragarLibro(Libro libro){
        listaLibros.add(libro);
    }

    public int getLibroSeleccionado() {
        return libroSeleccionado;
    }

    public void setLibroSeleccionado(int libroSeleccionado) {
        this.libroSeleccionado = libroSeleccionado;
    }

    public int getPersonajeSeleccionado() {
        return personajeSeleccionado;
    }

    public void setPersonajeSeleccionado(int personajeSeleccionado) {
        this.personajeSeleccionado = personajeSeleccionado;
    }

    public int getNotaSeleccionada() {
        return notaSeleccionada;
    }

    public void setNotaSeleccionada(int notaSeleccionada) {
        this.notaSeleccionada = notaSeleccionada;
    }

    public int getLugarSeleccionado() {
        return lugarSeleccionado;
    }

    public void setLugarSeleccionado(int lugarSeleccionado) {
        this.lugarSeleccionado = lugarSeleccionado;
    }

    public int getSucesoSeleccionado() {
        return sucesoSeleccionado;
    }

    public void setSucesoSeleccionado(int sucesoSeleccionado) {
        this.sucesoSeleccionado = sucesoSeleccionado;
    }
}
