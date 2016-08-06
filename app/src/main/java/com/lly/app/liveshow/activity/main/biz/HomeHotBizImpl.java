package com.lly.app.liveshow.activity.main.biz;

import android.support.v4.app.Fragment;
import com.lly.app.liveshow.commom.OnLoadListener;
import com.lly.app.liveshow.http.MyHttpParams;
import com.lly.app.liveshow.http.MyHttpParamsImpl;

/**
 * Created by luoyan on 16/8/4.
 */
public class HomeHotBizImpl implements HomeHotBiz {
    private OnLoadListener onLoadListener;

    public HomeHotBizImpl(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }

    @Override
    public void getBannerData(Fragment fragment) {
        MyHttpParams params = new MyHttpParamsImpl(fragment.getActivity(),onLoadListener);
        params.getHomeHotBannerServe();
        params = null;
    }

    @Override
    public void getContentData(int pageid, Fragment fragment) {
        MyHttpParams params = new MyHttpParamsImpl(fragment.getActivity(),onLoadListener);
        params.postHomeHotContentServe(pageid);
        params = null;
    }

}
