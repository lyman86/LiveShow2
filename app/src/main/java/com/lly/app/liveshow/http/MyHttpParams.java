package com.lly.app.liveshow.http;

import com.lly.app.liveshow.commom.OnLoadListener;
import com.lly.app.liveshow.commom.User;

/**
 * Created by luoyan on 16/7/30.
 */
public interface MyHttpParams {
    /**
     * 登录
     * @param userName
     * @param passWord
     */
    void postLoginServe(String userName,String passWord);
    /**
     * 注册
     * @param user
     */
    void postRegistServe(User user,String url);
    /**
     * 获取验证码
     * @param phoneNum
     */
    void postPhoneCodeServe(String phoneNum,String url);

}
