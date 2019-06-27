package com.example.alumno.TP_LAB_V_RSS;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.alumno.TP_LAB_V_RSS.enumerador.Eurl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements Handler.Callback {

    List<Noticia> OriNoticias;
    List<Noticia> noticias;
    MyAdapter myAdapter;
    MenuListener ml;
    Handler handler;
    SwipeRefreshLayout refresh;
    ImageView closeButton;
    EditText etSearchBar;
    SearchView sv;

    public static final int TEXTO = 1;
    public static final int IMAGEN = 2;
    public String rss = null;

    SelectRss rssDialog;
    /*
    public static final String  PAGINA12 = "https://www.pagina12.com.ar/rss/portada";
    public static final String  CLARIN = "https://www.clarin.com/rss/mundo/";
    public static final String  RT = "https://actualidad.rt.com/feeds/all.rss";
    public static final String  ELPAIS = "http://ep00.epimg.net/rss/elpais/portada.xml";
    public static final String  FRANCE24 = "https://www.france24.com/es/rss";
*/
    private List<Eurl>  rssGuardados;

    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.preferences = getSharedPreferences("configuracion", Context.MODE_PRIVATE);
        this.rssDialog = new SelectRss(this);

        noticias = new ArrayList<Noticia>();
        RecyclerView rvNoticias = (RecyclerView) super.findViewById(R.id.listaNoticias);

        myAdapter = new MyAdapter(noticias , this);

        LinearLayoutManager lm = new LinearLayoutManager(this);

        rvNoticias.setAdapter(myAdapter);
        rvNoticias.setLayoutManager(lm);

        // Obtener el refreshLayout
        refresh = (SwipeRefreshLayout)findViewById(R.id.swipeRV);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //ejecutarHilo(TEXTO);
                cargarNoticas();
            }
        });

        //this.rss = "https://www.france24.com/es/rss";


        this.rssGuardados = traerPreferencias();
        cargarNoticas();

    }



    @Override
    public boolean handleMessage(Message msg) {
        if (msg.arg1 == MainActivity.TEXTO){
            //this.noticias.clear();
            //for (Noticia n: (List<Noticia>)msg.obj){
                //Log.d("desde el hilo texto",n.toString());
            //}
            this.noticias.addAll((List<Noticia>)msg.obj);
            this.OriNoticias = new ArrayList<>(this.noticias);
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

    public void OpenActivity(String link, String fuente){
        Intent i = new Intent(this,webNoticia.class);
        i.putExtra("link",link);
        i.putExtra("fuente",fuente);
        //Log.d("Link",link);
        this.startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem miMenu = menu.findItem(R.id.BtnBuscar);
        this.ml = new MenuListener(this);
        this.sv =(SearchView) miMenu.getActionView();
        this.closeButton = (ImageView)sv.findViewById(R.id.search_close_btn);
        this.etSearchBar= (EditText)sv.findViewById(R.id.search_src_text);
        this.closeButton.setOnClickListener(ml);

        this.sv.setOnQueryTextListener(ml);
        this.sv.setOnCloseListener(ml);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        refresh.setRefreshing(true);
        int id = item.getItemId();
        if (id == R.id.RssMenu) {

            this.rssDialog.show(getSupportFragmentManager(),"dialogo");
            refresh.setRefreshing(false);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void ejecutarHilo(int tipo,String fuente){
        this.handler = new Handler(this);
        MyHilo hiloUno = new MyHilo(handler,this.rss,tipo ,fuente);
        hiloUno.start();

    }

    public List<Eurl> traerPreferencias() {
        Set<String> defaultValue = new HashSet<>();
        defaultValue.add(Eurl.FRANCE24.name());
        Set<String> preferencesValues = this.preferences.getStringSet("configuracion", defaultValue);
        List<Eurl> listaURL = new ArrayList<>();

        for (String url : preferencesValues) {
            listaURL.add(Eurl.valueOf(url));

        }
        return listaURL;
    }

    public void guardarPreferencias(List<Eurl> eurl) {
        refresh.setRefreshing(true);
        SharedPreferences.Editor edit = this.preferences.edit();
        Set<String> urls = new HashSet<>();
        for (Eurl s : eurl) {
            urls.add(s.name());
        }
        edit.putStringSet("configuracion", urls);
        edit.commit();
        this.rssGuardados = traerPreferencias();
        this.cargarNoticas();
    }

    public void cargarNoticas() {
        this.noticias.clear();
        //this.OriNoticias.clear();
        //this.myAdapter.notifyDataSetChanged();
        for (Eurl url : this.rssGuardados) {

            this.rss = url.url;
            ejecutarHilo(TEXTO,url.name().toString());
        }
        this.myAdapter.notifyDataSetChanged();
    }


}
