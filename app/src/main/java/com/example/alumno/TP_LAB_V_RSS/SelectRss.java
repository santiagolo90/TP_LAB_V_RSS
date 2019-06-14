package com.example.alumno.TP_LAB_V_RSS;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.alumno.TP_LAB_V_RSS.enumerador.Eurl;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class SelectRss extends DialogFragment {

    CheckBox pagina12 ;
    CheckBox rtNoticias;
    CheckBox clarin;
    CheckBox france24;
    List<Eurl> prefereces;
    List<CheckBox> listaCheckBox;

    public MainActivity activity;

    public SelectRss(){ }

    @SuppressLint("ValidFragment")
    public SelectRss(MainActivity activity){
        this.activity = activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle bundle){
        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        View v = inflater.inflate(R.layout.dialog_rss,null);
        View li = v.findViewById(R.id.liRSS);
        this.prefereces = this.activity.traerPreferencias();
        this.listaCheckBox = new ArrayList<>();
        for(View vi: li.getTouchables()){
            CheckBox cb = (CheckBox) vi;
            if (this.prefereces.contains(Eurl.valueOf(cb.getTag().toString()))){
                cb.setChecked(true);

            }
            listaCheckBox.add(cb);

        }



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
        DSRListener drsListener = new DSRListener(this.activity,this.listaCheckBox);

        b.setNegativeButton("Cerrar",drsListener);
        b.setPositiveButton("Aceptar",drsListener);
        return b.create();
    }


}
