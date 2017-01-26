package com.uihyun.newyorktimes.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.uihyun.newyorktimes.R;
import com.uihyun.newyorktimes.logger.Logger;

public class StoryActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        Logger.info(getClass().getSimpleName(), "open activity");

        Intent intent = getIntent();
        String storyLink = (String) intent.getSerializableExtra("story_link");

        webView = (WebView) findViewById(R.id.webview_story);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        webView.setWebViewClient(new WebViewClient());

        Logger.debug(getClass().getSimpleName(), "link = " + storyLink);
        webView.loadUrl(storyLink);
    }
}
