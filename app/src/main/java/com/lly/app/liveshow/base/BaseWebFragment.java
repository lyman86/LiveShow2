package com.lly.app.liveshow.base;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lly.app.liveshow.R;
import com.lly.app.liveshow.custom.MyWebView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by luoyan on 16/8/4.
 */
public class BaseWebFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,MyWebView.WebViewScrollInterface{
    @Bind(R.id.web_view)
    public MyWebView webView;

    @Bind(R.id.swipeLayout)
    public SwipeRefreshLayout swipeRefreshLayout;

    @Bind(R.id.view)
    public View bottomView;

    @Override
    public void onViewClick(View v) {

    }

    @Override
    public void initListener() {
        super.initListener();
        swipeRefreshLayout.setOnRefreshListener(this);
        webView.setOnCustomScroolChangeListener(this);
    }

    @Override
    public void initView() {
        super.initView();
        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorAccent,
                R.color.green);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
    }

    @Override
    public void setLayout() {
        layoutId = R.layout.fragment_web;
        ButterKnife.bind(getActivity());
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
    public void onRefresh() {

    }

    @Override
    public void onSChanged(int l, int t, int oldl, int oldt) {
        if (t==0){
            swipeRefreshLayout.setEnabled(true);
        }else{
            swipeRefreshLayout.setEnabled(false);
        }
    }
}
