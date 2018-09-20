package com.movilbox.lector.Referencias.Objetos;

public class Suceso {
    private String fecha, titulo,acontecimiento,id;

    public Suceso() {
    }

    public Suceso(String fecha, String titulo, String acontecimiento) {
        this.fecha = fecha;
        this.titulo = titulo;
        this.acontecimiento = acontecimiento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAcontecimiento() {
        return acontecimiento;
    }

    public void setAcontecimiento(String acontecimiento) {
        this.acontecimiento = acontecimiento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
