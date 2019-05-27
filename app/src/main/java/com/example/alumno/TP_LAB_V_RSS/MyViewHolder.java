package com.example.alumno.TP_LAB_V_RSS;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by alumno on 04/04/2019.
 */

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



    public TextView tvTitulo;
    public TextView tvFecha;
    public TextView tvDesc;
    public TextView tvFuente;
    public ImageView foto;
    public Noticia noticiaAux;
    private MyListener myListener;
    private String link;

    private MainActivity miapp;


    public MyViewHolder(View itemView,MainActivity miapp) {
        super(itemView);
        this.myListener = new MyListener(this);

        this.miapp = miapp;
        this.tvTitulo = (TextView)itemView.findViewById(R.id.tvTitulo);
        this.tvFecha = (TextView)itemView.findViewById(R.id.tvFecha);
        this.tvDesc = (TextView)itemView.findViewById(R.id.tvDesc);
        this.tvFuente = (TextView)itemView.findViewById(R.id.tvFuente);
        this.foto = (ImageView)itemView.findViewById(R.id.imagenrss);
        this.foto.setOnClickListener(this.myListener);
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public MainActivity getMiapp() {
        return miapp;
    }

    @Override
    public void onClick(View v) {

        Log.d("Click",noticiaAux.toString());
    }
}
