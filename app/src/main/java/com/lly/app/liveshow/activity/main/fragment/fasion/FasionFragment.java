package com.lly.app.liveshow.activity.main.fragment.fasion;

import android.view.View;
import android.webkit.WebView;

import com.lly.app.liveshow.R;
import com.lly.app.liveshow.base.BaseFragment;
import com.lly.app.liveshow.utils.L;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by luoyan on 16/7/31.
 */
public class FasionFragment extends BaseFragment{
//    @Bind(R.id.web_view)
//    WebView webView;
    @Override
    public void onViewClick(View v) {

    }

    @Override
    public void setLayout() {
        layoutId = R.layout.layou3;
    }

    @Override
    public void initView() {
        ButterKnife.bind(getActivity());
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initLogic() {
//        L.d("fsdasd", String.valueOf(webView==null));
    }
}
