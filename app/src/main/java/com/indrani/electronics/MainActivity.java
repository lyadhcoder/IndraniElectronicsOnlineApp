package com.indrani.electronics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private WebView mywebView;

    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mywebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = mywebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mywebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                netcheck();
                super.onReceivedError(view, request, error);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("http:") || url.startsWith("https:")) {
                    return false;
                }

                // Otherwise allow the OS to handle things like tel, mailto, etc.
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }
        });
        mywebView.loadUrl("https://indranielectronics.com");
        relativeLayout =(RelativeLayout) findViewById(R.id.nonet);
        netcheck();
    }
    @Override
    public void onBackPressed(){
        if (mywebView.canGoBack()){
            mywebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    public void netcheck(){
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobdata = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifidata = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);


        if (mobdata.isConnected()){
            mywebView.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
            mywebView.reload();

        }
        else if (wifidata.isConnected()){
            mywebView.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
            mywebView.reload();
        }
        else {
            mywebView.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
        }

    }
}
