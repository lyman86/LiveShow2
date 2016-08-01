package com.lly.app.liveshow;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import com.lly.app.liveshow.commom.User;
import com.lly.app.liveshow.utils.L;
import com.lly.app.liveshow.utils.SPUtils;

import io.rong.imkit.RongIM;

/**
 * Created by ly on 2016/7/30.
 */
public class MyApplication extends Application {

    public static final String SHARE_VALUES = "liveshow";// 全局共享 标示
    public static final String ISGUDIE = "isgudie";// 是否展示过引导页  0-没有 1-有
    public static final String MYW = "myw";// 本机的屏幕的宽度
    public static final String MYH = "myh";// 本机的屏幕的高度
    public static final String MYW_RATIO = "myw_ratio";// 本机的屏幕的宽度 比例值
    public static final String MYH_RATIO = "myh_ratio";// 本机的屏幕的高度 比例值
    public static final String USER_ID = "user_id";
    public static final String TOKEN = "token";
    public static final String IM_TOKEN = "im_token";
    public static final String USER_NAME = "user_name";//用户昵称
    public static final String USER_PHOTO = "user_photo";//用户头像地址

    public static View SELE_GIFT = null;//选中的礼物

    public static final int W=480;//以这个为基准
    public static final int H=854;//以这个为基准

    public static SharedPreferences SHARE;
    private static MyApplication insetance = null;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        SHARE=getSharedPreferences(MyApplication.SHARE_VALUES, 0);
        context = getApplicationContext();
    }

    public static MyApplication getInsetace(){
        if (insetance==null){
            insetance = new MyApplication();
        }
        return insetance;
    }

    public User getUser(){
        User user = new User();
        user.userId = (String) SPUtils.get(context,USER_ID,"-1");
        user.token = (String) SPUtils.get(context,TOKEN,"0");
        return user;
    }
    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return 进程号
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
