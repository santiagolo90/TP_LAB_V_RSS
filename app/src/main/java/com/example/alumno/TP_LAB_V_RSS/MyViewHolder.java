package com.example.alumno.TP_LAB_V_RSS;

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

    public MyViewHolder(View itemView) {
        super(itemView);

        this.tvTitulo = (TextView)itemView.findViewById(R.id.tvTitulo);
        this.tvFecha = (TextView)itemView.findViewById(R.id.tvFecha);
        this.tvDesc = (TextView)itemView.findViewById(R.id.tvDesc);
        this.foto = (ImageView)itemView.findViewById(R.id.imagenrss);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.d("Click",noticiaAux.toString());
    }
}
