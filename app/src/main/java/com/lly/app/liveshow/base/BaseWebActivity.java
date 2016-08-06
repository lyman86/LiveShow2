package com.lly.app.liveshow.base;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lly.app.liveshow.R;
import com.lly.app.liveshow.activity.main.MainActivity;
import com.lly.app.liveshow.custom.CustomSelectItem;
import com.lly.app.liveshow.event.EventMessage;

import butterknife.Bind;
import io.rong.eventbus.EventBus;

/**
 * Created by luoyan on 16/8/4.
 */
public abstract class BaseWebActivity extends BaseActivity implements CustomSelectItem.OnBarViewClickListener{
    @Bind(R.id.webview)
    public WebView webView;

    @Bind(R.id.titleBar)
    public CustomSelectItem title;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_web);
    }

    @Override
    protected void initListener() {
        title.setOnBarViewClickListener(this);
    }

    public void webViewSetting(){
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setSavePassword(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        /**
         * 用WebView显示图片，可使用这个参数 设置网页布局类型：
         * 1、LayoutAlgorithm.NARROW_COLUMNS ：适应内容大小
         * 2、LayoutAlgorithm.SINGLE_COLUMN : 适应屏幕，内容将自动缩放
         */
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);

        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setHorizontalScrollbarOverlay(true);
        webView.setHorizontalScrollBarEnabled(true);
        webView.addJavascriptInterface(this, "nativeMethod");
        webView.requestFocus();
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }
        });
    }
    @Override
    public void onBarViewClick(View v, int whitch) {
        switch (whitch){
            case CustomSelectItem.LEFT_VIEW:
                EventBus.getDefault().post(new EventMessage(MainActivity.UPDATE_FOLLOW,-1));
                finish();
                break;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }else{
            finish();

        }
        return super.onKeyDown(keyCode, event);
    }

}
