package com.kmu.mma_ver1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LicenseWebView extends AppCompatActivity {

    private WebView mWebview;
    private WebSettings mWebSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        mWebview = (WebView)findViewById(R.id.webview);
        mWebview.setWebViewClient(new WebViewClient());
        mWebSettings = mWebview.getSettings();
        mWebSettings.setJavaScriptEnabled(true);

        mWebview.loadUrl("https://github.com/firebase/FirebaseUI-Android/blob/master/LICENSE");
    }
}
