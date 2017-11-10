package com.greenacademy.ga_finalprojecthm.fragment;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

/**
 * Created by HoangHai Nguyen on 11/7/2017.
 */

public class ChiTietTapChiWebView extends WebViewClient {
    private EditText wvCTTC;

    public ChiTietTapChiWebView(EditText wvCTTC) {
        this.wvCTTC = wvCTTC;
    }

    //Khi click vào trình duyệt (WebView)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.i("MyLog","Click on any interlink on webview that time you got url :-" + url);
        wvCTTC.setText(url);
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        Log.i("MyLog", "Your current url when webpage loading.." + url);
    }

    //Khi trang tải xong
    @Override
    public void onPageFinished(WebView view, String url) {
        Log.i("MyLog", "Your current url when webpage loading.. finish" + url);
        super.onPageFinished(view, url);

    }

    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }
}
