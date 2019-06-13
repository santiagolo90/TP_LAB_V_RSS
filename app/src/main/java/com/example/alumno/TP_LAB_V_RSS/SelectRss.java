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

import java.util.ArrayList;
import java.util.List;


public class SelectRss extends DialogFragment {

    CheckBox pagina12 ;
    CheckBox rtNoticias;
    CheckBox clarin;
    CheckBox france24;
    SharedPreferences prefereces;
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

        this.pagina12 = (CheckBox) v.findViewById(R.id.CBP12);
        this.rtNoticias = (CheckBox) v.findViewById(R.id.CBRT);
        this.clarin = (CheckBox) v.findViewById(R.id.CBClarin);
        this.france24 = (CheckBox) v.findViewById(R.id.CBFr24);
        List<CheckBox> listaCheckBox = new ArrayList<>();

        this.prefereces = this.activity.getSharedPreferences("catalogo", Context.MODE_MULTI_PROCESS);

        pagina12.setChecked(this.prefereces.getBoolean((String)pagina12.getText(),false));
        rtNoticias.setChecked(this.prefereces.getBoolean((String)rtNoticias.getText(),false));
        clarin.setChecked(this.prefereces.getBoolean((String)clarin.getText(),false));
        france24.setChecked(this.prefereces.getBoolean((String)france24.getText(),false));



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
