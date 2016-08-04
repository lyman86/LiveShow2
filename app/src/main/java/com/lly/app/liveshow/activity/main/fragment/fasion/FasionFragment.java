package com.lly.app.liveshow.activity.main.fragment.fasion;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lly.app.liveshow.activity.main.MainActivity;
import com.lly.app.liveshow.base.BaseWebFragment;
import com.lly.app.liveshow.event.EventMessage;
import com.lly.app.liveshow.http.url.RequestUrl;
import com.lly.app.liveshow.utils.L;
import com.lly.app.liveshow.utils.UtilTool;
import org.apache.http.util.EncodingUtils;
import org.json.JSONException;
import org.json.JSONObject;

import io.rong.eventbus.EventBus;

/**
 * Created by luoyan on 16/7/31.
 */
public class FasionFragment extends BaseWebFragment{
    private final String homeUrl = "https://live.myyll.com/mobile/article";
    private String webUrl = "";
    private String url = "";
    private String postData;


    @Override
    public void processLogic() {
        super.processLogic();
        bottomView.setVisibility(View.VISIBLE);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userAuth", UtilTool.getJSONO_userAuth(getActivity()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        postData = "json=" + jsonObject.toString() + "&callback=/site/index";
        url = RequestUrl.FASION_H5;
        webView.postUrl(url, EncodingUtils.getBytes(postData, "base64"));
        webViewSetting();
        loadListener();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        if (homeUrl.equals(webUrl)){
            webView.postUrl(url, EncodingUtils.getBytes(postData, "base64"));
            swipeRefreshLayout.setRefreshing(false);
        }else{
            webView.reload();
            loadListener();
        }
    }

    private void loadListener() {
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url)
            {
                super.onPageFinished(view, url);
                swipeRefreshLayout.setRefreshing(false);
                webUrl = url;
                swipeRefreshLayout.setEnabled(true);
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                super.onPageStarted(view, url, favicon);
                swipeRefreshLayout.setEnabled(false);
            }
        });
    }

    @JavascriptInterface
    public void bottomBarOperate(String op) {
        L.e("operate", op);
        if(TextUtils.equals(op, "show")){
            EventBus.getDefault().post(new EventMessage(MainActivity.SHOW_BOTTOMTAB,-1));
        }else if(TextUtils.equals(op, "hide")){
            EventBus.getDefault().post(new EventMessage(MainActivity.HIDE_BOTTOMTAB,-1));
        }
    }

    public boolean clickBack(int keycode,KeyEvent event){
        if(keycode== KeyEvent.KEYCODE_BACK&&webView.canGoBack()){
            webView.goBack();
            loadListener();
            return true;
        }else{
            return false;
        }

    }

}
