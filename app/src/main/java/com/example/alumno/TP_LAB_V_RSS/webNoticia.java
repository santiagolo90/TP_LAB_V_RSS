package com.example.alumno.TP_LAB_V_RSS;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class webNoticia extends AppCompatActivity {
    WebView webView;
    SwipeRefreshLayout refresh;
    private String link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_noticia);
        Intent i = getIntent();
        this.link = i.getStringExtra("link");
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
}
