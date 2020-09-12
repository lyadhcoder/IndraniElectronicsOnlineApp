package com.indrani.electronics;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {
    private WebView mywebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mywebView = (WebView)findViewById(R.id.webview);
        WebSettings webSettings=mywebView.getSettings();
        mywebView.loadUrl("https://indranielectronics.com");
        mywebView.setWebViewClient(new WebViewClient());
        webSettings.setJavaScriptEnabled(true);
    }

    public class myWebClinet extends WebViewClient{
        @Override
        public void onPageStarted (WebView view, String url, Bitmap favicon){
            super.onPageStarted(view, url, favicon);
        }
        public  boolean shouldOverrideURLLoading(WebView view, String url){
            view.loadUrl(url);
            return true;
        }
    }
    @Override
    public void onBackPressed(){
        if (mywebView.canGoBack()){
            mywebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

}
