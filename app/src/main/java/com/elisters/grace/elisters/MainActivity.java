package com.elisters.grace.elisters;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ProgressBar mProgressBar;
    private WebView mWebView;
    private Context mContext;
    private Activity mActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Getting the url that we passed from the main activity
        mProgressBar = findViewById(R.id.pb);

        // Get the application context
        mContext = getApplicationContext();
        // Get the activity
        mActivity = MainActivity.this;

        //This will help us to render the page
        mWebView = findViewById(R.id.webView);
        mProgressBar = findViewById(R.id.pb);

        renderPage("https://elisters2000limited.freshdesk.com/");
    }
    protected void renderPage(String urlToRender){
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                // Do something on page loading started
                // Visible the progressbar
                mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url){
                // Do something when page loading finished
                Toast.makeText(mContext,"Page Loaded.", Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(view.GONE);
            }

        });
        mWebView.setWebChromeClient(new WebChromeClient(){

            public void onProgressChanged(WebView view, int newProgress){
                // Update the progress bar with page loading progress
                mProgressBar.setProgress(newProgress);
                if(newProgress == 100){
                    // Hide the progressbar
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });

        // Enable the javascript
        mWebView.getSettings().setJavaScriptEnabled(true);
        // Render the web page
        mWebView.loadUrl(urlToRender);

    }
}