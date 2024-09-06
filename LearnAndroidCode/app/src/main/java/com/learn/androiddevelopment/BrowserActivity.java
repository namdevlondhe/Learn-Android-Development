package com.learn.androiddevelopment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


public class BrowserActivity extends AppCompatActivity {

    WebView webViewBrowser;
    ProgressBar progress1;
    private AdView mAdView;
    private String baseUrl = "file:///android_asset/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getExtras().getString("title"));

        webViewBrowser = findViewById(R.id.webViewBrowser);
        progress1 = findViewById(R.id.progress1);
        webViewBrowser.getSettings().setJavaScriptEnabled(true);
        webViewBrowser.getSettings().setBuiltInZoomControls(true);
        webViewBrowser.getSettings().setDisplayZoomControls(false);
        webViewBrowser.clearCache(true);
        webViewBrowser.clearHistory();
        webViewBrowser.setWebChromeClient(new WebChromeClient());
        webViewBrowser.getSettings().setLoadWithOverviewMode(true);
        webViewBrowser.getSettings().setUseWideViewPort(true);
        /*webViewBrowser.getSettings().setSupportZoom(true);
        webViewBrowser.getSettings().setMinimumFontSize(18);*/

        webViewBrowser.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                progress1.setVisibility(View.VISIBLE);

                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                progress1.setVisibility(View.GONE);
            }

        });

        String strtext = getIntent().getExtras().getString("url");
        Log.d("WebViewFragment", strtext);
        webViewBrowser.loadUrl(baseUrl + strtext);

        mAdView = findViewById(R.id.adViewHome);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        // Get reference to singleton RewardedVideoAd object

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
