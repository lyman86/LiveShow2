package com.lly.app.liveshow.activity.regist.biz;

import com.lly.app.liveshow.activity.regist.RegistActivity;
import com.lly.app.liveshow.commom.User;

/**
 * Created by luoyan on 16/7/31.
 */
public interface RegistBiz {
    public void regist(User user,String url,RegistActivity registActivity);

    public void getPhoneCode(String phone,String url,RegistActivity registActivity);
}
