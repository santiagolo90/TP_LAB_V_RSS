package com.example.alumno.TP_LAB_V_RSS;

import android.icu.text.SimpleDateFormat;
import android.util.Log;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.List;

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
        this.fuente = "Sin fuente";
        this.titulo = "Sin titulo";
        this.fecha = "11/11/1111";
        this.descripcion = "Sin descripcion";
        //this.foto = "https://www.tattoow.com/src/themes/claue/assets/images/placeholder.png";

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
        //int index = fecha.indexOf(":");
        //String s =  fecha.substring(0,index -2);
        //this.fecha = s;
        String oldFecha ="Thu, 06 Jun 2019 22:46:01 GMT";
        String source = fecha;
        String[] sourceSplit= source.split(" ");

        int dia= Integer.parseInt(sourceSplit[1]);
        String mes= convertMonth(sourceSplit[2]);
        int anio = Integer.parseInt(sourceSplit[3]);
        String hora = sourceSplit[4];
        String fechaFinal = String.valueOf(dia).concat("/").concat(mes).concat("/").concat(String.valueOf(anio)).concat(" ").concat(hora);

        this.fecha = fechaFinal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        int inicio = descripcion.indexOf("<img");
        int fin = descripcion.indexOf("/>");
        // Carácter en la posición

        if (inicio > -1 && fin > -1 ){
            String descFiltrada = descripcion.substring(fin +2);
            this.descripcion = descFiltrada;
        }else{
            this.descripcion = descripcion;
        }



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


    public String convertMonth(String mm){
        List<String> months = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
        if (months.indexOf(mm)+1>9){
            return String.valueOf(months.indexOf(mm)+1);
        }
        return "0"+String.valueOf(months.indexOf(mm)+1);
    }
}
