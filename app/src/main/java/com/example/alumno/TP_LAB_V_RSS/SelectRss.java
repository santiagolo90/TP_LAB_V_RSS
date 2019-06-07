package com.example.alumno.TP_LAB_V_RSS;

import android.app.AlertDialog;
import android.app.Dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;



public class SelectRss extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle bundle){
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
        b.setTitle("Titulo");
        b.setMessage("Mensaje");
        DSRListener drsListener = new DSRListener();
        b.setNegativeButton("Cerrar",drsListener);
        b.setPositiveButton("Aceptar",drsListener);
        return b.create();
    }
}
