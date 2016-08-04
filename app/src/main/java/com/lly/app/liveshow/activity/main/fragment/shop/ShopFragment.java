package com.lly.app.liveshow.activity.main.fragment.shop;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.KeyEvent;
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
public class ShopFragment extends BaseWebFragment{
    private String url = "";
    @Override
    public void processLogic() {
        super.processLogic();
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("userAuth", UtilTool.getJSONO_userAuth(getActivity()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String postData = "json="+jsonObject.toString()+"&callback=/site/index";
        url = RequestUrl.MALL_H5;
        webView.postUrl(url, EncodingUtils.getBytes(postData,"base64"));
        webViewSetting();
    }

    @Override
    public void onRefresh() {
        webView.reload();
        loadListener();
    }

    private void loadListener() {
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url)
            {
                super.onPageFinished(view, url);
                swipeRefreshLayout.setRefreshing(false);
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {

                super.onPageStarted(view, url, favicon);
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
            return true;
        }else{
            return false;
        }

    }

}
