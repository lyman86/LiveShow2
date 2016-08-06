package com.lly.app.liveshow.activity.main.presenter;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.lly.app.liveshow.R;
import com.lly.app.liveshow.activity.main.WebActivity;
import com.lly.app.liveshow.activity.main.adapter.HomeHotFragmentAdapter;
import com.lly.app.liveshow.activity.main.biz.HomeHotBiz;
import com.lly.app.liveshow.activity.main.biz.HomeHotBizImpl;
import com.lly.app.liveshow.activity.main.fragment.home.HomeHotFragment;
import com.lly.app.liveshow.activity.main.model.HomeHotBannerModel;
import com.lly.app.liveshow.activity.main.model.HomeHotFragmentModel;
import com.lly.app.liveshow.activity.main.view.HomeHotFragmentView;
import com.lly.app.liveshow.commom.CommonAdapter;
import com.lly.app.liveshow.commom.OnLoadListener;
import com.lly.app.liveshow.commom.RequestVo;
import com.lly.app.liveshow.commom.ResponseModel;
import com.lly.app.liveshow.commom.ViewPageAdapter;
import com.lly.app.liveshow.custom.InsideViewPager;
import com.lly.app.liveshow.http.url.RequestUrl;
import com.lly.app.liveshow.utils.JsonUtil;
import com.lly.app.liveshow.utils.PicturePlay;
import com.lly.app.liveshow.utils.UtilTool;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luoyan on 16/8/4.
 */
public class HomeHotFragmentPresenterImpl implements HomeHotFragmentPresenter,OnLoadListener{
    private HomeHotFragmentView view;
    private HomeHotBiz biz;

    private HomeHotFragment fragment;
    private View headerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private HomeHotFragmentAdapter adapter;
    private HomeHotBannerModel bannerModel;
    private PicturePlay picturePlay;
    //第一页
    private int pageid = 1;
    private boolean refresh = false;
    private boolean loadAll = false;


    public HomeHotFragmentPresenterImpl(HomeHotFragmentView view) {
        this.view = view;
        biz = new HomeHotBizImpl(this);
        swipeRefreshLayout = view.getSwipeRefreshLayout();
        fragment = (HomeHotFragment) view.getHomeHotFragment();
        adapter = new HomeHotFragmentAdapter(fragment.getActivity(),null,R.layout.item_home_hot_content);
        view.getLoadListView().setAdapter(adapter);
        picturePlay =new PicturePlay();
        picturePlay.start();
    }

    @Override
    public void init() {
        getBannerData();
    }

    @Override
    public void getBannerData() {
        biz.getBannerData(view.getHomeHotFragment());
    }

    @Override
    public void loadMore() {
        if (!loadAll){
            refresh = false;
            pageid++;
            getContentData(pageid);
        }
    }

    @Override
    public void refresh() {
        refresh = true;
        pageid=1;
        adapter.clearAll();
        view.getSwipeRefreshLayout().setEnabled(false);
        view.getLoadListView().removeHeaderView(headerView);
        init();
    }

    @Override
    public void getContentData(int pageid) {
        biz.getContentData(pageid,view.getHomeHotFragment());
    }

    @Override
    public void loadStatus(RequestVo requestVo) {
        if (requestVo.sucess){
            ResponseModel responseModel = JsonUtil.jsonToEntity((String) requestVo.resObj,ResponseModel.class);
            if (responseModel.getStatus().getSucceed()==0){
                if (responseModel.getStatus().getError_code()==100){
                    UtilTool.startLoginActivity(fragment.getActivity());
                }
                fragment.showToast(responseModel.getStatus().getError_desc());
            }else{
                switch (requestVo.requestUrl){
                    case RequestUrl.YLL_HOME_HOT:{
                        HomeHotFragmentModel model = JsonUtil.jsonToEntity((String) requestVo.resObj,HomeHotFragmentModel.class);
                        adapter.addAll(model.getData());
                        if (refresh){
                            loadAll = false;
                            view.getSwipeRefreshLayout().setEnabled(true);
                        }else{
                            if (model.getData().size()==0){
                                loadAll = true;
                                fragment.showToast(fragment.getResources().getString(R.string.load_all));
                            }else{
                                loadAll = false;
                            }
                        }
                        view.getLoadListView().loadComplete();
                        swipeRefreshLayout.setRefreshing(false);
                        break;
                    }
                    case RequestUrl.YLL_ADS:{
                        bannerModel = JsonUtil.jsonToEntity((String) requestVo.resObj,HomeHotBannerModel.class);
                        addHeaderView(bannerModel);
                        getContentData(pageid);
                        break;
                    }
                }
            }
        }else {
            if (requestVo.erroNo==-1)fragment.showToast(fragment.getResources().getString(R.string.check_network));
        }
    }

    private void addHeaderView(HomeHotBannerModel bannerModel) {
        headerView = LayoutInflater.from(fragment.getActivity()).inflate(R.layout.item_home_hot_banner, null);
        InsideViewPager viewPager = (InsideViewPager) headerView.findViewById(R.id.viewpager);
        CirclePageIndicator indicator = (CirclePageIndicator) headerView.findViewById(R.id.indicator);
        List<ImageView> images = new ArrayList<>();
        for (int i =0;i<bannerModel.getData().size();i++){
            ImageView img = new ImageView(fragment.getActivity());
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageLoader.getInstance().displayImage(bannerModel.getData().get(i).getImage(),img, CommonAdapter.bannerDefault);
            images.add(img);
            setBannerItemListener(img,bannerModel.getData().get(i));
            img = null;
        }
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(images);
        this.view.getLoadListView().addHeaderView(headerView);
        viewPager.setAdapter(viewPageAdapter);
        indicator.setViewPager(viewPager);
        //图片轮播
        picturePlay.setViewPagers(viewPager,viewPageAdapter.getCount());
        viewPageAdapter = null;
        images = null;


    }

    @Override
    public void refreLayoutEnable(View firstChild, SwipeRefreshLayout swipeRefreshLayout){
        try {
            if (firstChild.getTop()==0){
                swipeRefreshLayout.setEnabled(true);
            }else{
                if (adapter.getCount()==0){
                    swipeRefreshLayout.setEnabled(true);
                }else {
                    swipeRefreshLayout.setEnabled(false);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setBannerItemListener(ImageView iv,final HomeHotBannerModel.BannerDataBean bannerDataBean) {
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = bannerDataBean.getUrl();
                Intent intent = new Intent(fragment.getActivity(), WebActivity.class);
                intent.putExtra("flag","0");
                intent.putExtra("url",url);
                fragment.getActivity().startActivity(intent);
            }
        });
    }
}
