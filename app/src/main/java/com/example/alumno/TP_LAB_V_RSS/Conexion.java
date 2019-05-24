package com.example.alumno.TP_LAB_V_RSS;


import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alumno on 11/04/2019.
 */

public class Conexion extends AsyncTask<String,String,String> {

    public byte[] conectarse(String miUrl){
        try {
            URL url = new URL(miUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int res = connection.getResponseCode();

            if (res == 200){
                InputStream is = connection.getInputStream();//enlace de comunicacion lo uso para leer
                // esta en el servidor porque no tiene new
                ByteArrayOutputStream myByte = new ByteArrayOutputStream();//como hice new esta en memoria
                byte[] arrayByte = new byte[1024];
                int cant = 0;
                //int cantidad = is.read(arrayByte);//lee la cantidad que tiene y guarda en el array de bytes
                while ((cant = is.read(arrayByte))!= -1){
                    myByte.write(arrayByte,0,cant);//objeto que escribe, desde donde hasta donde
                }
                is.close();

                return myByte.toByteArray();
            }else{
                //throw new RuntimeException("No responde el serviodr");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] conectarseImagen(String miUrl){

        return this.conectarse(miUrl);
    }

    public String conectarseString(String miUrl){
        String respuesta = new String(this.conectarse(miUrl));
        return respuesta;

    }

    @Override
    protected String doInBackground(String... miUrl) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(miUrl[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();


            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            //StringBuffer buffer = new StringBuffer();
            StringBuilder sb = new StringBuilder();
            String line = "";

            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }

            String buffer = sb.toString();
            return buffer;

        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Deprecated
    protected void convertToJson(String datos) {
        try {
            JSONArray jsonarray = new JSONArray(datos);
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject c = jsonarray.getJSONObject(i);
                Number idCarrusel = c.getInt("idCarrusel");
                String titulo = c.getString("titulo");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //return null;
    }




}
