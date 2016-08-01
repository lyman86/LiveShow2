package com.lly.app.liveshow.activity.login.view;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lly.app.liveshow.activity.login.LoginActivity;

/**
 * Created by ly on 2016/7/30.
 */
public interface LoginView {
    public EditText getEdtUserName();
    public EditText getEdtUserPwd();
    public Button getBtnLogin();
    public TextView getTvReset();
    public LinearLayout getLayoutRegist();
    public LoginActivity getActivity();

}
