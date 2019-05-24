package com.example.alumno.TP_LAB_V_RSS;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by alumno on 04/04/2019.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    List<Noticia> noticias;
    MainActivity miapp;

    public MyAdapter(List<Noticia> n, MainActivity mipp ) {
        this.noticias = n;
        this.miapp = mipp;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rss_recyclerview,parent,false);
        MyViewHolder mv = new MyViewHolder(v);
        return mv;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Noticia n = this.noticias.get(position);
        holder.tvTitulo.setText(n.getTitulo());
        holder.tvFecha.setText(n.getFecha());
        holder.tvDesc.setText(n.getDescripcion());
        //personViewHolder.personPhoto.setImageResource(persons.get(i).photoId);
        holder.noticiaAux = n;


        if (!n.seEstaDescargando){
            MyHilo hilo2 = new MyHilo(this.miapp.handler,n.getFoto(),this.miapp.IMAGEN, position);
            hilo2.start();
            n.seEstaDescargando = Boolean.TRUE;
            //MyHilo hilo2 = new MyHilo(this.miapp.handler,n.getFoto(),this.miapp.IMAGEN, position);
            //hilo2.start();
            //n.seEstaDescargando = Boolean.TRUE;
        } else {
            //Bitmap bitmap = BitmapFactory.decodeByteArray(p.getImagenes(), 0, n.getImagenes().length);
            //holder.foto.setImageBitmap(bitmap);
        }

    }

    @Override
    public int getItemCount() {
        return this.noticias.size();
    }
}
