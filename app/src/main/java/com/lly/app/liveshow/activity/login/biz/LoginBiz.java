package com.lly.app.liveshow.activity.login.biz;


import android.content.Context;

import com.lly.app.liveshow.activity.login.LoginActivity;
import com.lly.app.liveshow.activity.login.model.LoginModel;
import com.lly.app.liveshow.commom.User;

/**
 * Created by ly on 2016/7/30.
 */
public interface LoginBiz {
    public void login(User user, LoginActivity activity);
}
