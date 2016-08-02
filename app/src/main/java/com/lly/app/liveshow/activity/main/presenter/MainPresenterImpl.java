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
    private int isWebView = 0;//0-不是webview 1-是尖客 2-商城
    public MainPresenterImpl(MainView mainView) {
        this.view = mainView;
        activity = mainView.getMainActivity();
        manager = activity.getSupportFragmentManager();
        transaction = manager.beginTransaction();
        fragmentHome = new HomeFragment();
        transaction.add(R.id.content, fragmentHome);
        fragmentFasion = new FasionFragment();
        transaction.add(R.id.content, fragmentFasion);
        fragmentMyinfo = new MyinfoFragment();
        transaction.add(R.id.content, fragmentMyinfo);
        fragmentShop = new ShopFragment();
        transaction.add(R.id.content, fragmentShop);
        hideFragment(transaction);
    }

    @Override
    public void init() {
        transaction.show(fragmentHome);
        transaction.commit();
        fragment = fragmentHome;
    }

    @Override
    public void homeFramentChange() {
        isWebView = 0;
        fragmentSet(fragmentHome);
        view.getIvHome().setImageResource(R.mipmap.homeing);
        view.getTvHome().setTextColor(activity.getResources().getColor(R.color.red_send));
        transaction.commit();
    }

    @Override
    public void shopFragmentChange() {
        isWebView = 2;
        fragmentSet(fragmentShop);
        view.getIvShop().setImageResource(R.mipmap.shoping);
        view.getTvShop().setTextColor(activity.getResources().getColor(R.color.red_send));
        transaction.commit();
    }

    @Override
    public void fasionFramentChange() {
        isWebView = 1;
        fragmentSet(fragmentFasion);
        view.getIvFasion().setImageResource(R.mipmap.quanzing);
        view.getTvFasion().setTextColor(activity.getResources().getColor(R.color.red_send));
        transaction.commit();
    }

    @Override
    public void myinfoFragmentChange() {
        isWebView = 0;
        fragmentSet(fragmentMyinfo);
        view.getIvMe().setImageResource(R.mipmap.wodeing);
        view.getTvMe().setTextColor(activity.getResources().getColor(R.color.red_send));
        transaction.commit();
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

    private void fragmentSet(Fragment fragment) {
        manager = activity.getSupportFragmentManager();
        transaction = manager.beginTransaction();
        hideFragment(transaction);
        resetAllTab();
        if (fragment instanceof HomeFragment) {
            transaction.show(fragmentHome);
            this.fragment = fragmentHome;
        } else if (fragment instanceof FasionFragment) {
            transaction.show(fragmentFasion);
            this.fragment = fragmentFasion;
        } else if (fragment instanceof MyinfoFragment) {
            transaction.show(fragmentMyinfo);
            this.fragment = fragmentMyinfo;
        } else if (fragment instanceof ShopFragment) {
            transaction.show(fragmentShop);
            this.fragment = fragmentShop;
        }

    }
}
