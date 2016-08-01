package com.lly.app.liveshow.utils;

import android.os.CountDownTimer;
import android.widget.Button;

public class TimeCount extends CountDownTimer {
    private Button button;
    public TimeCount(long millisInFuture, long countDownInterval, Button button) {
        super(millisInFuture, countDownInterval);
        this.button = button;
    }

    @Override
    public void onFinish() {// 计时完毕
        button.setText("获取验证码");
        button.setClickable(true);
    }

    @Override
    public void onTick(long millisUntilFinished) {// 计时过程
        button.setClickable(false);//防止重复点击
        button.setText((millisUntilFinished / 1000)+"s");
    }
}