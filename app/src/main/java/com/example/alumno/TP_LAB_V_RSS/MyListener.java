package com.example.alumno.TP_LAB_V_RSS;

import android.content.Intent;
import android.view.View;

public class MyListener implements View.OnClickListener {

    private MyViewHolder holeder;


    public MyListener(MyViewHolder holeder) {
        this.holeder = holeder;
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.imagenrss) {
            //Log.d("Click","Agrego");
            //Log.d("Click",this.holeder.getProductoBuscado().toString());
            String myLink = this.holeder.getLink();
            //this.holeder.getMiapp().OpenActivity(myLink);


            Intent i = new Intent(this.holeder.getMiapp(),webNoticia.class);
            i.putExtra("link",myLink);
            this.holeder.getMiapp().startActivity(i);
        }else{
            this.holeder.getMiapp().finish();
        }

    }
}