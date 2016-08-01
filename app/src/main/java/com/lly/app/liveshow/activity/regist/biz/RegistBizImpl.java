package com.lly.app.liveshow.activity.regist.biz;

import android.text.TextUtils;

import com.lly.app.liveshow.R;
import com.lly.app.liveshow.activity.regist.RegistActivity;
import com.lly.app.liveshow.commom.OnLoadListener;
import com.lly.app.liveshow.commom.User;
import com.lly.app.liveshow.http.MyHttpParams;
import com.lly.app.liveshow.http.MyHttpParamsImpl;
import com.lly.app.liveshow.utils.CheckInfo;
import com.lly.app.liveshow.utils.T;

/**
 * Created by luoyan on 16/7/31.
 */
public class RegistBizImpl implements RegistBiz {
    private OnLoadListener onLoadListener;

    public RegistBizImpl(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }

    @Override
    public void regist(User user,String url,RegistActivity activity) {
        if (TextUtils.isEmpty(user.name)) {
            T.showShort(activity,activity.getString(R.string.toast_regist_tips01));
            return;
        }
        if (TextUtils.isEmpty(user.pwd)){
            T.showShort(activity,activity.getString(R.string.toast_regist_tips02));
            return;
        }
        if (TextUtils.isEmpty(user.code)){
            T.showShort(activity,activity.getString(R.string.toast_regist_tips03));
            return;
        }
        if (user.pwd.length()<6){
            T.showShort(activity,activity.getString(R.string.toast_regist_tips04));
            return;
        }
        if (user.pwd.length()>20){
            T.showShort(activity,activity.getString(R.string.toast_regist_tips05));
            return;
        }
        MyHttpParams params = new MyHttpParamsImpl(activity,onLoadListener);
        activity.showProgressDialog();
        params.postRegistServe(user,url);
        params = null;

    }

    @Override
    public void getPhoneCode(String phone, String url,RegistActivity activity) {
        if (!TextUtils.isEmpty(phone)){
            if (CheckInfo.checkPhoneNumber(phone)){
                activity.showProgressDialog();
                MyHttpParams params = new MyHttpParamsImpl(activity,onLoadListener);
                params.postPhoneCodeServe(phone,url);
                params = null;
            }else{
                T.showShort(activity,activity.getString(R.string.toast_regist_correct_phone));
            }
        }else{
            T.showShort(activity,activity.getString(R.string.toast_regist_phone));
        }
    }
}
