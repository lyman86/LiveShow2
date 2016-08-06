package com.lly.app.liveshow.activity.main.fragment.home;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import com.lly.app.liveshow.R;
import com.lly.app.liveshow.activity.main.presenter.HomeHotFragmentPresenter;
import com.lly.app.liveshow.activity.main.presenter.HomeHotFragmentPresenterImpl;
import com.lly.app.liveshow.activity.main.view.HomeHotFragmentView;
import com.lly.app.liveshow.base.BaseFragment;
import com.lly.app.liveshow.custom.LoadListView;
import com.lly.app.liveshow.utils.L;

import butterknife.Bind;

/**
 * Created by luoyan on 16/8/2.
 */
public class HomeHotFragment extends BaseFragment implements HomeHotFragmentView,
                                                             LoadListView.ILoadListener,
                                                             LoadListView.OnLoadScrollListener,
                                                             SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.lv)
    LoadListView listView;

    @Bind(R.id.swipeLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private HomeHotFragmentPresenter presenter;
    @Override
    public void onViewClick(View v) {
        switch (v.getId()){
            case R.id.hot_bg:{
                int position = (int) v.getTag();
                showToast(position+"");
                L.d("fasggfs",position+" 111");
                break;
            }

            case R.id.item_layout_top:{
                int position = (int) v.getTag();
                showToast(position+"");
                break;
            }
        }
    }

    @Override
    public void setLayout() {
        layoutId = R.layout.fragment_home_hot;
    }

    @Override
    public void initView() {
        configRefreshLayout(swipeRefreshLayout);
        presenter = new HomeHotFragmentPresenterImpl(this);
    }

    @Override
    public void initListener() {
        swipeRefreshLayout.setOnRefreshListener(this);
        listView.setILoadListener(this);
        listView.setOnLoadScrollListener(this);
    }

    @Override
    public void processLogic() {
        super.processLogic();
        presenter.init();
    }

    @Override
    public LoadListView getLoadListView() {
        return listView;
    }

    @Override
    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

    @Override
    public Fragment getHomeHotFragment() {
        return this;
    }

    @Override
    public void onLoad() {
        presenter.loadMore();
    }

    @Override
    public void onLoadScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        View firstChild = listView.getChildAt(0);
        presenter.refreLayoutEnable(firstChild,swipeRefreshLayout);
    }

    @Override
    public void onLoadScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onRefresh() {
        presenter.refresh();
    }
}
