package com.lly.app.liveshow.activity.regist.presenter;

import com.lly.app.liveshow.commom.User;

/**
 * Created by luoyan on 16/7/31.
 */
public interface RegistPresenter {
    /**
     * 获取验证码
     */
    public void getPhoneCode(int type);
    /**
     * 注册
     */
    public void regist(int type);
}
