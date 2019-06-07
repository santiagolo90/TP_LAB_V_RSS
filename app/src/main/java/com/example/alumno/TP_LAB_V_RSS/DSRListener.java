package com.example.alumno.TP_LAB_V_RSS;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

/**
 * Created by alumno on 06/06/2019.
 */

public class DSRListener implements DialogInterface.OnClickListener, View.OnClickListener {



    @Override
    public void onClick(DialogInterface dialog, int which) {
        Log.d("Clic dialog","ID: ".concat(String.valueOf(which)));


    }

    @Override
    public void onClick(View v) {

    }
}
