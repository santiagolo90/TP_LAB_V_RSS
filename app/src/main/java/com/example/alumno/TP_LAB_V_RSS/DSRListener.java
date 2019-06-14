package com.example.alumno.TP_LAB_V_RSS;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.example.alumno.TP_LAB_V_RSS.enumerador.Eurl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by alumno on 06/06/2019.
 */

public class DSRListener implements DialogInterface.OnClickListener, View.OnClickListener {

    List<CheckBox> listaCheckBox;
    public MainActivity activity;

    public DSRListener( MainActivity activity,List<CheckBox> listaCheckBox) {
        this.listaCheckBox = listaCheckBox;
        this.activity = activity;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == -1){
            List<Eurl> e =  new ArrayList<>();
            for (CheckBox s : listaCheckBox) {
                if (s.isChecked()){
                    e.add(Eurl.valueOf(s.getTag().toString()));
                }
            }
            this.activity.guardarPreferencias(e);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
