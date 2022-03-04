package com.example.thebug_zoo.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.thebug_zoo.R;

public class PrivacyPolicy extends AppCompatActivity {
    WebView PrivacyPolicyWeb;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        setBackButton();

        PrivacyPolicyWeb = findViewById(R.id.privacy_policy_web);
        WebSettings webSettings = PrivacyPolicyWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        PrivacyPolicyWeb.setWebViewClient(new Callback());
        PrivacyPolicyWeb.loadUrl("https://thebugprivacypolicy.blogspot.com/2022/02/privacy-policy-for-thebug-zoo.html");
    }

    private static class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }

    void setBackButton(){
        back = findViewById(R.id.imageSeta);
        back.setOnClickListener(v -> finish());
    }
}