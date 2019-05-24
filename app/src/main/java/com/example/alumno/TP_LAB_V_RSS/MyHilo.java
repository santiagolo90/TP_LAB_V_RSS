package com.example.alumno.TP_LAB_V_RSS;

import android.os.Handler;
import android.os.Message;
import android.util.Log;


/**
 * Created by alumno on 11/04/2019.
 */

public class MyHilo extends Thread {
    Conexion myConeccion = new Conexion();
    Handler handler;
    String url;
    int tipo ;
    int currentIndex;

    public MyHilo(Handler handler, String url, int tipo){
        this.handler = handler;
        this.url = url;
        this.tipo = tipo;
    }

    public MyHilo(Handler handler, String url, int tipo, int currentIndex){
        this(handler, url, tipo);
        this.currentIndex = currentIndex;
    }

    @Override
    public void run(){
        Message mensaje = new Message();
        mensaje.arg1 = tipo;
        if (this.tipo == MainActivity.TEXTO){
            String respues = myConeccion.conectarseString(url);
            mensaje.obj = XmlParser.obtenerNoticias(respues);
        }/*else if(this.tipo == MainActivity.IMAGEN){
            byte[] respues = myConeccion.conectarseImagen(url);
            mensaje.obj = respues;
            Log.d("URL_FOTO",mensaje.obj.toString());
            mensaje.arg2 = currentIndex;
        }*/
        this.handler.sendMessage(mensaje);
    }


}
