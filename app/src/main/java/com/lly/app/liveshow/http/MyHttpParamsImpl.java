package com.lly.app.liveshow.http;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.lly.app.liveshow.activity.login.model.LoginModel;
import com.lly.app.liveshow.commom.OnLoadListener;
import com.lly.app.liveshow.commom.RequestVo;
import com.lly.app.liveshow.commom.User;
import com.lly.app.liveshow.http.url.RequestUrl;
import com.lly.app.liveshow.utils.JsonUtil;
import com.lly.app.liveshow.utils.L;
import com.lly.app.liveshow.utils.UtilTool;

import org.json.JSONObject;
import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpParams;

import io.rong.eventbus.EventBus;

/**
 * Created by luoyan on 16/7/30.
 */
public class MyHttpParamsImpl implements MyHttpParams {
    private Context context;
    private RequestVo requestVo;
    private KJHttp kjHttp;
    private HttpParams params;
    private OnLoadListener onLoadListener;
    private JSONObject jsonObject;

    public MyHttpParamsImpl(Context context,OnLoadListener onLoadListener) {
        this.context = context;
        this.onLoadListener = onLoadListener;
        requestVo = new RequestVo();
        params = new HttpParams();
        kjHttp = new KJHttp();
        jsonObject=new JSONObject();
    }

    /**
     * 登录
     * @param userName
     * @param passWord
     */
    @Override
    public void postLoginServe(String userName, String passWord) {
        try{
            jsonObject.put("username",userName);
            jsonObject.put("password",passWord);
        }catch (Exception e){
        }
        params.put("json",jsonObject.toString());
        kjHttp.post(RequestUrl.YLL_LOGIN, params, new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                requestVo.sucess = true;
                requestVo.resObj = t;
                onLoadListener.loadStatus(requestVo);
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                requestVo.sucess = false;
                requestVo.erroMsg = strMsg;
                requestVo.erroNo = errorNo;
                onLoadListener.loadStatus(requestVo);
            }
        });
        params=null;
        kjHttp=null;
        jsonObject=null;
    }

    /**
     * 注册
     * @param user
     */
    @Override
    public void postRegistServe(User user,String url) {
        try{
            jsonObject.put("username",user.name);
            jsonObject.put("mobile_code",user.code);
            jsonObject.put("password",user.pwd);
        }catch (Exception e){
        }
        requestVo.requestUrl = url;
        params.put("json",jsonObject.toString());
        kjHttp.post(url, params, new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                requestVo.sucess = true;
                requestVo.resObj = t;
                onLoadListener.loadStatus(requestVo);
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                requestVo.sucess = false;
                requestVo.erroMsg = strMsg;
                requestVo.erroNo = errorNo;
                onLoadListener.loadStatus(requestVo);
            }
        });
        params=null;
        kjHttp=null;
        jsonObject=null;
    }

    /**
     * 获取验证码
     * @param phoneNum
     * @param url
     */
    @Override
    public void postPhoneCodeServe(String phoneNum,String url) {
        try{
            jsonObject.put("mobile_phone",phoneNum);
        }catch (Exception e){
        }
        requestVo.requestUrl = url;
        params.put("json",jsonObject.toString());
        kjHttp.post(url, params, new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                requestVo.sucess = true;
                requestVo.resObj = t;
                onLoadListener.loadStatus(requestVo);
            }
            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                requestVo.sucess = false;
                requestVo.erroMsg = strMsg;
                requestVo.erroNo = errorNo;
                onLoadListener.loadStatus(requestVo);
            }
        });
        params=null;
        kjHttp=null;
        jsonObject=null;
    }

    /**
     * 获取首页热门广告
     */
    @Override
    public void getHomeHotBannerServe() {
        requestVo.requestUrl =RequestUrl.YLL_ADS;
        kjHttp.get(RequestUrl.YLL_ADS, new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                requestVo.sucess = true;
                requestVo.resObj = t;
                onLoadListener.loadStatus(requestVo);
            }
            @Override
            public void onFailure(int errorNo, String strMsg) {
                requestVo.sucess = false;
                requestVo.erroMsg = strMsg;
                requestVo.erroNo = errorNo;
                onLoadListener.loadStatus(requestVo);
            }
        });
        kjHttp = null;
    }

    /**
     * 获取首页热门内容
     */
    @Override
    public void postHomeHotContentServe(int pageid) {
        try{
            jsonObject.put("userAuth", UtilTool.getJSONO_userAuth(context));
            jsonObject.put("pageid",pageid);
        }catch (Exception e){
            e.printStackTrace();
        }
        params.put("json",jsonObject.toString());
        requestVo.requestUrl =RequestUrl.YLL_HOME_HOT;
        kjHttp.post(RequestUrl.YLL_HOME_HOT, params, new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                requestVo.sucess = true;
                requestVo.resObj = t;
                onLoadListener.loadStatus(requestVo);
            }
            @Override
            public void onFailure(int errorNo, String strMsg) {
                requestVo.sucess = false;
                requestVo.erroMsg = strMsg;
                requestVo.erroNo = errorNo;
                onLoadListener.loadStatus(requestVo);
            }
        });
        kjHttp = null;
        params = null;
        jsonObject = null;
    }


}
