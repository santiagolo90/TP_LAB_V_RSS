package com.example.alumno.TP_LAB_V_RSS;

import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Handler.Callback{

    List<Noticia> noticias;
    MyAdapter myAdapter;
    Handler handler;
    SwipeRefreshLayout refresh;
    public static final int TEXTO = 1;
    public static final int IMAGEN = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noticias = new ArrayList<Noticia>();
        //noticias.add(new Noticia("RT","Notica RT","24/05/2019","esto es la discripcion","https://onemoretry.eu/assets/fotos/carrusel/20190514_151257.jpg"));
        //noticias.add(new Noticia("Pagina 12","Pagina 12","24/05/2019","esto es la discripcion","https://onemoretry.eu/assets/fotos/carrusel/20190514_190037.jpg"));
        //noticias.add(new Noticia("Clarin","Clarin","24/05/2019","esto es la discripcion","https://onemoretry.eu/assets/fotos/carrusel/20190226_111412.jpg"));
        RecyclerView rvNoticias = (RecyclerView) super.findViewById(R.id.listaNoticias);

        myAdapter = new MyAdapter(noticias , this);

        LinearLayoutManager lm = new LinearLayoutManager(this);

        rvNoticias.setAdapter(myAdapter);
        rvNoticias.setLayoutManager(lm);



        this.handler = new Handler(this);
        //MyHilo hiloUno = new MyHilo(handler,"https://actualidad.rt.com/feeds/all.rss",TEXTO);
        //hiloUno.start();

        // Obtener el refreshLayout
        refresh = (SwipeRefreshLayout)findViewById(R.id.swipeRV);

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                MyHilo hiloUno = new MyHilo(handler,"https://www.clarin.com/rss/mundo/",TEXTO);
                hiloUno.start();
            }
        });

        MyHilo hiloDos = new MyHilo(handler,"https://www.pagina12.com.ar/rss/portada",TEXTO);
        hiloDos.start();

    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.arg1 == MainActivity.TEXTO){
            this.noticias.clear();
            for (Noticia n: (List<Noticia>)msg.obj){
                Log.d("desde el hilo texto",n.toString());
            }


            this.noticias.addAll((List<Noticia>)msg.obj);
            this.myAdapter.notifyDataSetChanged();
        }else if(msg.arg1  == MainActivity.IMAGEN){
            this.noticias.get(msg.arg2).setFotos((byte[])msg.obj);
            this.myAdapter.notifyItemChanged(msg.arg2);
            /*ImageView imagen =(ImageView) super.findViewById(R.id.imagenTest);
            Bitmap bitmap = BitmapFactory.decodeByteArray((byte[])msg.obj, 0, ((byte[])msg.obj).length);
            imagen.setImageBitmap(bitmap);*/
        }
        refresh.setRefreshing(false);
        return false;
    }

    public void OpenActivity(String link){
        Intent i = new Intent(this,webNoticia.class);
        i.putExtra("link",link);
        //Log.d("Link",link);
        this.startActivity(i);
    }
}
