package com.example.alumno.TP_LAB_V_RSS;

import android.content.DialogInterface;
import android.util.Log;

/**
 * Created by alumno on 06/06/2019.
 */

public class DSRListener implements DialogInterface.OnClickListener {

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Log.d("Clic dialog","ID: ".concat(String.valueOf(which)));
    }
}
