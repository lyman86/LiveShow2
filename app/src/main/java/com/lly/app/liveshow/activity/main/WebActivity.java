package com.lly.app.liveshow.activity.main;

import android.content.Intent;
import android.view.View;
import android.webkit.JavascriptInterface;
import com.lly.app.liveshow.base.BaseWebActivity;
import com.lly.app.liveshow.custom.CustomSelectItem;
import com.lly.app.liveshow.utils.UtilTool;
import org.apache.http.util.EncodingUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by luoyan on 16/6/28.
 */
public class WebActivity extends BaseWebActivity implements CustomSelectItem.OnBarViewClickListener{
    private boolean post = false;
    @Override
    protected void onClickEvent(View paramView) {

    }

    @Override
    protected void processLogic() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String flag = intent.getStringExtra("flag");
        post = intent.getBooleanExtra("post",false);
        if (flag.equals("0")){
            title.setVisibility(View.GONE);
        }else{
            title.setVisibility(View.VISIBLE);
        }
        if (!post){
            webView.loadUrl(url);
        }else{
            JSONObject jsonObject=new JSONObject();
            try {
                jsonObject.put("userAuth", UtilTool.getJSONO_userAuth(this));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String postDataShop = "json="+jsonObject.toString();
            webView.postUrl(url, EncodingUtils.getBytes(postDataShop,"base64"));
        }
        webViewSetting();
    }

    @JavascriptInterface
    public void closeH5View() {
        this.finish();
    }


}
