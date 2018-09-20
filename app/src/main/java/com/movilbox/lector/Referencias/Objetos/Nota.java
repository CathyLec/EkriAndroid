package com.movilbox.lector.Referencias.Objetos;

public class Nota {
    private String titulo, texto, id;
    private int importancia;

    public Nota() {
    }

    public Nota(String titulo, String texto, int importancia) {
        this.titulo = titulo;
        this.texto = texto;
        this.importancia = importancia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getImportancia() {
        return importancia;
    }

    public void setImportancia(int importancia) {
        this.importancia = importancia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
