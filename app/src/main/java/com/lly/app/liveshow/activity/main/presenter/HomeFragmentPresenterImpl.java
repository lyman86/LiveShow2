package com.lly.app.liveshow.activity.main.presenter;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import com.lly.app.liveshow.R;
import com.lly.app.liveshow.activity.main.fragment.home.HomeDiscoverFragment;
import com.lly.app.liveshow.activity.main.fragment.home.HomeFollowFragment;
import com.lly.app.liveshow.activity.main.fragment.home.HomeFragment;
import com.lly.app.liveshow.activity.main.fragment.home.HomeHotFragment;
import com.lly.app.liveshow.activity.main.view.HomeFragmentView;
import com.lly.app.liveshow.commom.MyFragmentPagerAdapter;
import com.lly.app.liveshow.custom.ViewPagerIndecator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by luoyan on 16/8/4.
 */
public class HomeFragmentPresenterImpl implements HomeFragmentPresenter{
    private List<String> mTitles = Arrays.asList("关注","热门","发现");
    private List<Fragment>fragments = new ArrayList<>();
    private HomeDiscoverFragment homeDiscoverFragment;
    private HomeHotFragment homeHotFragment;
    private HomeFollowFragment homeFollowFragment;

    private HomeFragment fragment;

    private HomeFragmentView view;

    public HomeFragmentPresenterImpl(HomeFragmentView view) {
        this.view = view;
    }

    @Override
    public void init() {
        fragment = view.getHomeFragment();
        ViewPagerIndecator indecator = view.getIndecator();
        indecator.setIndecatorColor(fragment.getResources().getColor(R.color.red_send));
        indecator.setTabItemTitles(mTitles,0);
        indecator.setTextColorNomal(Color.parseColor("#333333"));
        indecator.setTextColorSelect(fragment.getResources().getColor(R.color.red_send));
        indecator.setViewPageChange(view.getViewPager());
        initFragment();
    }

    @Override
    public void setCurrentItem(int position) {
        view.getViewPager().setCurrentItem(position);
    }

    @Override
    public void startHomeHotSearchActivity() {
//        fragment.getActivity().startActivity(new Intent(fragment.getActivity(),HomeHotSearchActivity.class));
    }

    private void initFragment() {
        ViewPager viewPager = view.getViewPager();
        homeDiscoverFragment = new HomeDiscoverFragment();
        homeHotFragment = new HomeHotFragment();
        homeFollowFragment = new HomeFollowFragment();
        fragments.add(homeFollowFragment);
        fragments.add(homeHotFragment);
        fragments.add(homeDiscoverFragment);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new MyFragmentPagerAdapter(fragment.getChildFragmentManager(),fragments));
        viewPager.setCurrentItem(1);
    }
}
