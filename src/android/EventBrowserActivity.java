package com.megaphone.cordova.events;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

public class EventBrowserActivity extends AppCompatActivity {

        private WebView _webview;
        private Button _backButton;
        private Button _forwardButton;
        private ProgressBar _progressBar;

        private static FakeR fakeR;

        class RoundUpWebViewClient extends WebViewClient {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                enableDisableButtons();
            }

        }

        private void enableDisableButtons() {
            _backButton.setEnabled(_webview.canGoBack());
            _forwardButton.setEnabled(_webview.canGoForward());
        }


        @Override
        protected void onCreate(Bundle savedInstanceState) {

            Intent intent = getIntent();
            String url = intent.getStringExtra("url");

            super.onCreate(savedInstanceState);
            fakeR = new FakeR(this);
            setContentView(fakeR.getId("layout", "activity_event_browser"));
            _progressBar = (ProgressBar) this.findViewById(fakeR.getId("id", "pageProgress"));

            _backButton = (Button) this.findViewById(fakeR.getId("id", "backButton"));

            _backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _webview.goBack();
                }
            });


            _forwardButton = (Button) this.findViewById(fakeR.getId("id", "forwardButton"));

            _forwardButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _webview.goForward();
                }
            });
            _webview = (WebView) this.findViewById(fakeR.getId("id", "webView"));
            _webview.setWebViewClient(new RoundUpWebViewClient());
            _webview.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView view, int progress) {
                    _progressBar.setVisibility(View.VISIBLE);
                    if (progress == 100) {
                        _progressBar.setVisibility(View.INVISIBLE);
                    }
                }
            });
            _webview.getSettings().setJavaScriptEnabled(true);
            _webview.loadUrl(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(fakeR.getId("menu", "menu"), menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == fakeR.getId("id", "menu_create")) {
            String url = _webview.getUrl();

            Intent intent = new Intent();
            intent.putExtra("url", url);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {

        return super.onCreateView(parent, name, context, attrs);
    }


}
