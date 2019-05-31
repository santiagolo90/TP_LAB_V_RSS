package com.example.alumno.TP_LAB_V_RSS;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class webNoticia extends AppCompatActivity {
    WebView webView;
    SwipeRefreshLayout refresh;
    private String link;
    private String fuente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_noticia);
        Intent i = getIntent();
        this.link = i.getStringExtra("link");
        this.fuente = i.getStringExtra("fuente");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(fuente);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2E2E2E")));

        refresh = (SwipeRefreshLayout)findViewById(R.id.swipe);



        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                WebAction();
            }
        });

        WebAction();

    }

    public void WebAction(){
        refresh.setRefreshing(true);

        webView = (WebView) findViewById(R.id.webView);
        //Permito JavaScript
        webView.getSettings().setJavaScriptEnabled(true);
        // Habilita el cache
        webView.getSettings().setAppCacheEnabled(true);
        // Habilita el Zoom
        //webView.getSettings().setBuiltInZoomControls(true);
        // Oculta los botones de zoom, haciendo que solo funcione con gestos.
        //webView.getSettings().setDisplayZoomControls(false);
        webView.loadUrl(this.link );
        webView.setWebViewClient(new WebViewClient(){



            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                Log.d("Error ",description);
                //webView.loadUrl("file:///android_assets/error.html");

            }

            public void onPageFinished(WebView view, String url) {
                // do your stuff here
                refresh.setRefreshing(false);
            }

        });

    }



    @Override
    public void onBackPressed(){

        if (webView.canGoBack()){
            //webView.goBack();
            finish();
        }else {
            finish();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        refresh.setRefreshing(true);
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();;
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
