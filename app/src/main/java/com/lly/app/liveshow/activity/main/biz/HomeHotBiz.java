package com.lly.app.liveshow.activity.main.biz;

import android.support.v4.app.Fragment;

/**
 * Created by luoyan on 16/8/4.
 */
public interface HomeHotBiz {
    public void getBannerData(Fragment fragment);
    public void getContentData(int pageid,Fragment fragment);
}
