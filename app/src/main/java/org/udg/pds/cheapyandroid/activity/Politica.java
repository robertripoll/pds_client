package org.udg.pds.cheapyandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import org.udg.pds.cheapyandroid.R;
import org.udg.pds.cheapyandroid.rest.CheapyApi;

public class Politica extends AppCompatActivity{

    private CheapyApi mCheapyService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        WebView webView = (WebView) findViewById(R.id.webView);

        webView.loadUrl("file:///android_asset/privacy_policy.html");
    }

}
