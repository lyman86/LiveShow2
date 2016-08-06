package com.lly.app.liveshow.activity.main.fragment.home;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import com.lly.app.liveshow.R;
import com.lly.app.liveshow.activity.main.presenter.HomeFragmentPresenter;
import com.lly.app.liveshow.activity.main.presenter.HomeFragmentPresenterImpl;
import com.lly.app.liveshow.activity.main.view.HomeFragmentView;
import com.lly.app.liveshow.base.BaseFragment;
import com.lly.app.liveshow.custom.ViewPagerIndecator;
import butterknife.Bind;

/**
 * Created by luoyan on 16/7/31.
 */
public class HomeFragment extends BaseFragment implements HomeFragmentView,ViewPagerIndecator.OnViewPagerChangeListener,
        ViewPagerIndecator.OncIndecaterItemClickListener{
    @Bind(R.id.layout_search)
    RelativeLayout layoutSearch;

    @Bind(R.id.layout_news)
    RelativeLayout layoutNews;

    @Bind(R.id.indecator)
    ViewPagerIndecator viewPagerIndecator;

    @Bind(R.id.viewPager)
    ViewPager viewPager;

    private HomeFragmentPresenter presenter;

    @Override
    public void onViewClick(View v) {
        switch (v.getId()){
            case R.id.layout_search:
                presenter.startHomeHotSearchActivity();
                break;
        }
    }

    @Override
    public void setLayout() {
        layoutId = R.layout.fragment_home;
    }

    @Override
    public void initView() {
        presenter = new HomeFragmentPresenterImpl(this);
    }

    @Override
    public void initListener() {
        viewPagerIndecator.setOnViewPagerChangeListener(this);
        viewPagerIndecator.setOncIndecaterItemClickListener(this);
        layoutSearch.setOnClickListener(this);
        layoutNews.setOnClickListener(this);
    }

    @Override
    public void processLogic() {
        super.processLogic();
        presenter.init();
    }

    @Override
    public ViewPagerIndecator getIndecator() {
        return viewPagerIndecator;
    }

    @Override
    public ViewPager getViewPager() {
        return viewPager;
    }

    @Override
    public HomeFragment getHomeFragment() {
        return this;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void OncIndecaterItemClick(View v, int position) {
        presenter.setCurrentItem(position);
    }
}
