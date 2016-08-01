package com.lly.app.liveshow.activity.main.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.lly.app.liveshow.R;
import com.lly.app.liveshow.activity.main.MainActivity;
import com.lly.app.liveshow.activity.main.fragment.fasion.FasionFragment;
import com.lly.app.liveshow.activity.main.fragment.home.HomeFragment;
import com.lly.app.liveshow.activity.main.fragment.myinfo.MyinfoFragment;
import com.lly.app.liveshow.activity.main.fragment.shop.ShopFragment;
import com.lly.app.liveshow.activity.main.view.MainView;

/**
 * Created by luoyan on 16/7/31.
 */
public class MainPresenterImpl implements MainPresenter {
    private FasionFragment fragmentFasion;
    private HomeFragment fragmentHome;
    private MyinfoFragment fragmentMyinfo;
    private ShopFragment fragmentShop;
    private Fragment fragment;
    private MainView view;
    private MainActivity activity;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    public MainPresenterImpl(MainView mainView) {
        this.view = mainView;
        activity = mainView.getMainActivity();
    }

    @Override
    public void init() {
        manager = activity.getSupportFragmentManager();
        fragmentHome = new HomeFragment();
        fragment = fragmentHome;
        transaction = manager.beginTransaction();
        transaction.add(R.id.content, fragment);
        transaction.commit();
    }

    @Override
    public void homeFramentChange() {

    }

    @Override
    public void shopFragmentChange() {

    }

    @Override
    public void fasionFramentChange() {

    }

    @Override
    public void myinfoFragmentChange() {

    }

    private void resetAllTab() {
        view.getIvHome().setImageResource(R.mipmap.home);
        view.getTvHome().setTextColor(activity.getResources().getColor(R.color.text_gray));
        view.getIvMe().setImageResource(R.mipmap.wode);
        view.getTvMe().setTextColor(activity.getResources().getColor(R.color.text_gray));
        view.getIvFasion().setImageResource(R.mipmap.quanzi);
        view.getTvFasion().setTextColor(activity.getResources().getColor(R.color.text_gray));
        view.getIvShop().setImageResource(R.mipmap.shop);
        view.getTvShop().setTextColor(activity.getResources().getColor(R.color.text_gray));
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (fragmentHome != null) {
            transaction.hide(fragmentHome);
        }
        if (fragmentMyinfo != null) {
            transaction.hide(fragmentMyinfo);
        }
        if (fragmentFasion != null) {
            transaction.hide(fragmentFasion);
        }
        if (fragmentShop != null) {
            transaction.hide(fragmentShop);
        }
    }
}
