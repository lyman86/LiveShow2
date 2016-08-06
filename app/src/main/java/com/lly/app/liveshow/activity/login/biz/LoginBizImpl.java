package com.lly.app.liveshow.activity.login.biz;
import android.text.TextUtils;
import com.lly.app.liveshow.R;
import com.lly.app.liveshow.activity.login.LoginActivity;
import com.lly.app.liveshow.commom.OnLoadListener;
import com.lly.app.liveshow.commom.User;
import com.lly.app.liveshow.http.MyHttpParams;
import com.lly.app.liveshow.http.MyHttpParamsImpl;
import com.lly.app.liveshow.utils.T;

/**
 * Created by ly on 2016/7/30.
 */
public class LoginBizImpl implements LoginBiz {
    private OnLoadListener onLoadListener;

    public LoginBizImpl(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }

    @Override
    public void login(User user, LoginActivity activity) {
        if (TextUtils.isEmpty(user.name)){
            T.showShort(activity,activity.getString(R.string.toast_login_name));
            return;
        }
        if (TextUtils.isEmpty(user.pwd)){
            T.showShort(activity,activity.getString(R.string.toast_login_pwd));
            return;
        }
        activity.showProgressDialog();
        MyHttpParams params = new MyHttpParamsImpl(activity,onLoadListener);
        params.postLoginServe(user.name,user.pwd);
        params = null;
    }
}
