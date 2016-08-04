package com.lly.app.liveshow.activity.main.fragment.home;

import android.view.View;
import android.widget.RelativeLayout;

import com.lly.app.liveshow.R;
import com.lly.app.liveshow.activity.main.MainActivity;
import com.lly.app.liveshow.activity.main.view.HomeFragmentView;
import com.lly.app.liveshow.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by luoyan on 16/7/31.
 */
public class HomeFragment extends BaseFragment implements HomeFragmentView{
    @Bind(R.id.layout_search)
    RelativeLayout layoutSearch;

    @Bind(R.id.layout_news)
    RelativeLayout layoutNews;

    @Override
    public void onViewClick(View v) {

    }

    @Override
    public void setLayout() {
        layoutId = R.layout.fragment_home;
        ButterKnife.bind(getActivity());
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }



    @Override
    public RelativeLayout getLayoutSearch() {
        return layoutSearch;
    }

    @Override
    public RelativeLayout getLayoutNews() {
        return layoutNews;
    }

    @Override
    public MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }
}
