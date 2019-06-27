package com.example.alumno.TP_LAB_V_RSS.enumerador;

/**
 * Created by alumno on 13/06/2019.
 */

public enum Eurl {
    PAGINA12("https://www.pagina12.com.ar/rss/portada"),
    CLARIN("https://www.clarin.com/rss/mundo/"),
    RT("https://actualidad.rt.com/feeds/all.rss"),
    FRANCE24("https://www.france24.com/es/rss");

    public String url;

    Eurl(String url) {
        this.url = url;
    }

    public static String getNombre(Eurl e){
        return e.name();
    }


}
