package com.lly.app.liveshow.activity.main.view;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;

import com.lly.app.liveshow.custom.InsideViewPager;
import com.lly.app.liveshow.custom.LoadListView;
import com.lly.app.liveshow.custom.MyListView;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by luoyan on 16/8/4.
 */
public interface HomeHotFragmentView {
    public LoadListView getLoadListView();

    public SwipeRefreshLayout getSwipeRefreshLayout();

    public Fragment getHomeHotFragment();
}
