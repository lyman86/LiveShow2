package com.lly.app.liveshow.activity.main.presenter;

import android.view.KeyEvent;

/**
 * Created by luoyan on 16/7/31.
 */
public interface MainPresenter {

    public void init();

    public void homeFramentChange();

    public void shopFragmentChange();

    public void fasionFramentChange();

    public void myinfoFragmentChange();

    public void showBottomTab();

    public void hideBottomTab();

    public boolean onKeyDown(int keyCode, KeyEvent event);
}
