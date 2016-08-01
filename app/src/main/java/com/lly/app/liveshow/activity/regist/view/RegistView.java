package com.lly.app.liveshow.activity.regist.view;

import android.widget.Button;
import android.widget.EditText;

import com.lly.app.liveshow.activity.regist.RegistActivity;
import com.lly.app.liveshow.custom.CustomSelectItem;

/**
 * Created by luoyan on 16/7/31.
 */
public interface RegistView {
    public EditText getEdtName();
    public EditText getEdtPwd();
    public EditText getEdtCode();
    public Button getBtnCode();
    public Button getBtnRegist();
    public CustomSelectItem getTitleBar();
    public RegistActivity getRegistActivity();
}
