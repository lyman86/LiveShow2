package com.lly.app.liveshow.activity.main.view;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;
import com.lly.app.liveshow.activity.main.fragment.home.HomeFragment;
import com.lly.app.liveshow.custom.ViewPagerIndecator;

/**
 * Created by luoyan on 16/8/2.
 */
public interface HomeFragmentView {
    public ViewPagerIndecator getIndecator();
    public ViewPager getViewPager();
    public HomeFragment getHomeFragment();

}
