package com.lly.app.liveshow.activity.main.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lly.app.liveshow.activity.main.MainActivity;

/**
 * Created by luoyan on 16/7/31.
 */
public interface MainView {
    public View getMyView();

    public LinearLayout getLayoutHome();

    public LinearLayout getLayoutMe();

    public LinearLayout getLayoutShop();

    public LinearLayout getLayoutFasion();

    public LinearLayout getBottomTab();

    public ImageView getIvVideo();

    public ImageView getIvHome();

    public ImageView getIvMe();

    public ImageView getIvShop();

    public ImageView getIvFasion();

    public ImageView getToolBarImg();

    public TextView getTvHome();

    public TextView getTvMe();

    public TextView getTvShop();

    public TextView getTvFasion();


    public MainActivity getMainActivity();


}
