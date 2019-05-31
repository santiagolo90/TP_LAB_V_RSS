package com.example.alumno.TP_LAB_V_RSS;

import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MenuListener implements View.OnClickListener, SearchView.OnQueryTextListener, SearchView.OnCloseListener {
    MainActivity miapp;

    public MenuListener(MainActivity mipp ) {
        this.miapp = mipp;
    }



    @Override
    public boolean onQueryTextSubmit(String query) {
        filtrar(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText.length() > 2){
            filtrar(newText);
        }

        return false;
    }

    @Override
    public boolean onClose() {
        this.miapp.noticias.clear();
        this.miapp.noticias.addAll(this.miapp.OriNoticias);
        this.miapp.myAdapter.notifyDataSetChanged();
        return false;
    }

    @Override
    public void onClick(View v) {
        //LoggerUtils.d(LOG, "Search close button clicked");
        Log.d("Cerrar ","Search close button clicked");
        //impio texto del SearchBar
        this.miapp.etSearchBar.setText("");
        //limpio query
        this.miapp.sv.setQuery("",false);
        //Collapse the action view
        this.miapp.sv.onActionViewCollapsed();
        onClose();
    }

    private void filtrar(String query){
        List<Noticia> newList = new ArrayList<>();
        Iterator<Noticia> it = this.miapp.OriNoticias.iterator();
        Noticia n = null;
        while (it.hasNext()) {
            n = it.next();
            if (n.getTitulo().toLowerCase().contains(query.toLowerCase())) {
                newList.add(n);
            }
        }
        this.miapp.noticias.clear();
        this.miapp.noticias.addAll(newList);
        this.miapp.myAdapter.notifyDataSetChanged();
    }
}
