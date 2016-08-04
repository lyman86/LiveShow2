package com.lly.app.liveshow.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebView;

public class MyWebView extends WebView {
    public WebViewScrollInterface mScrollInterface;

    public MyWebView(Context context) {
        super(context);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {

        super.onScrollChanged(l, t, oldl, oldt);
        if (mScrollInterface!=null){
            mScrollInterface.onSChanged(l, t, oldl, oldt);
        }
    }

    public void setOnCustomScroolChangeListener(WebViewScrollInterface scrollInterface) {

        this.mScrollInterface = scrollInterface;

    }

    public interface WebViewScrollInterface {

        public void onSChanged(int l, int t, int oldl, int oldt);

    }

}