package com.example.alumno.TP_LAB_V_RSS;

import android.app.AlertDialog;
import android.app.Dialog;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;


public class SelectRss extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle bundle){
        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        View v = inflater.inflate(R.layout.dialog_rss,null);


        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
        TextView titulo = new TextView(this.getContext());
        titulo.setText("Seleccione fuente(s)");
        titulo.setBackgroundColor(Color.parseColor("#007E33"));
        titulo.setPadding(10, 10, 10, 10);
        titulo.setGravity(Gravity.CENTER);
        titulo.setTextColor(Color.WHITE);
        titulo.setTextSize(20);

        b.setCustomTitle(titulo);
        //b.setMessage("Mensaje");
        b.setView(v);
        DSRListener drsListener = new DSRListener();

        b.setNegativeButton("Cerrar",drsListener);
        b.setPositiveButton("Aceptar",drsListener);
        return b.create();
    }

}
