package com.lly.app.liveshow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lly.app.liveshow.activity.login.LoginActivity;
import com.lly.app.liveshow.activity.main.MainActivity;
import com.lly.app.liveshow.utils.SPUtils;

/**
 * 用来判断是到登录还是主界面
 * Created by ly on 2016/6/6.
 */
public class ChoiceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String userId = (String) SPUtils.get(this,"token","");
        if (!userId.equals("")){
            startActivity(new Intent(this,MainActivity.class));
        }else{
            startActivity(new Intent(this,LoginActivity.class));
        }
        finish();
    }
}
