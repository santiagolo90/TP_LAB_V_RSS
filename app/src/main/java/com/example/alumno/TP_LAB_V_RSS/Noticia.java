package com.example.alumno.TP_LAB_V_RSS;

import android.util.Log;

public class Noticia {

    private String fuente;
    private String titulo;
    private String fecha;
    private String descripcion;
    private String link;
    private String foto;
    private byte[] fotos;
    public Boolean seEstaDescargando = Boolean.FALSE;

    public Noticia() {

    }

    public Noticia(String fuente, String titulo, String fecha, String descripcion, String foto) {
        this();
        this.fuente = fuente;
        this.titulo = titulo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        int index = fecha.indexOf(":");
        String s =  fecha.substring(0,index -2);
        this.fecha = s;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public byte[] getFotos() {
        return fotos;
    }

    public void setFotos(byte[] fotos) {
        this.fotos = fotos;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Noticia{" +
                "fuente='" + fuente + '\'' +
                ", titulo='" + titulo + '\'' +
                ", fecha='" + fecha + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }
}
