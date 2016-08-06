package com.lly.app.liveshow.activity.main.presenter;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

/**
 * Created by luoyan on 16/8/4.
 */
public interface HomeHotFragmentPresenter {
    public void init();

    public void getBannerData();

    public void loadMore();

    public void refresh();

    public void refreLayoutEnable(View firstChild, SwipeRefreshLayout swipeRefreshLayout);

    public void getContentData(int pageid);
}
