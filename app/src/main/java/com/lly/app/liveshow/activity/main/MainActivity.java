package com.lly.app.liveshow.activity.main;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.lly.app.liveshow.R;
import com.lly.app.liveshow.activity.main.presenter.MainPresenter;
import com.lly.app.liveshow.activity.main.presenter.MainPresenterImpl;
import com.lly.app.liveshow.activity.main.view.MainView;
import com.lly.app.liveshow.base.EventBusActivity;
import com.lly.app.liveshow.event.EventMessage;
import butterknife.Bind;

public class MainActivity extends EventBusActivity implements MainView{
    @Bind(R.id.view)
    View view;

    @Bind(R.id.layout_home)
    LinearLayout layoutHome;

    @Bind(R.id.layout_me)
    LinearLayout layoutMe;

    @Bind(R.id.layout_cf)
    LinearLayout layoutFasion;

    @Bind(R.id.layout_shop)
    LinearLayout layoutShop;

    @Bind(R.id.tab)
    LinearLayout bottomTab;

    @Bind(R.id.toolbar_img)
    ImageView toolbarImg;

    @Bind(R.id.iv_video)
    ImageView ivVideo;

    @Bind(R.id.iv_me)
    ImageView ivMe;

    @Bind(R.id.iv_home)
    ImageView ivHome;

    @Bind(R.id.iv_cf)
    ImageView ivCf;

    @Bind(R.id.iv_shop)
    ImageView ivShop;

    @Bind(R.id.tv_home)
    TextView tvHome;

    @Bind(R.id.tv_me)
    TextView tvMe;

    @Bind(R.id.tv_cf)
    TextView tvCf;

    @Bind(R.id.tv_shop)
    TextView tvShop;

    //再按一次退出时间
    private long exitTime = 0;
    private MainPresenter presenter;



    /**
     * 刷新个人信息
     */
    public static final String REFRESH_ME = "refresh_me";
    /**
     * 结束整个进程
     */
    public static final String FINISH_ME = "finish_me";
    /**
     * 刷新“我的 直播”
     */
    public static final String UPDATE_VIDEO = "update_video";
    /**
     * 刷新“我的 关注”
     */
    public static final String UPDATE_FOLLOW = "update_follow";
    /**
     * 刷新“我的 图片”
     */
    public static final String UPDATE_PICTURE = "update_picture";
    /**
     * 刷新“我的 粉丝”
     */
    public static final String UPDATE_FANS = "update_fans";
    /**
     * 刷新“首页 热门”
     */
    public static final String REFRESH_HOME_HOT = "refresh_home_hot";
    /**
     * webview 显示BottomTab
     */
    public static final String SHOW_BOTTOMTAB = "show_bottomtab";
    /**
     * webview 隐藏BottomTab
     */
    public static final String HIDE_BOTTOMTAB = "hide_bottomtab";
    /**
     * 购物车 去逛逛
     */
    public static final String GOSHOPPING = "goshopping";

    @Override
    protected void onClickEvent(View paramView) {
        switch (paramView.getId()){
            case R.id.layout_home:
                presenter.homeFramentChange();
                break;
            case R.id.layout_me:
                presenter.myinfoFragmentChange();
                break;
            case R.id.layout_cf:
                presenter.fasionFramentChange();
                break;
            case R.id.layout_shop:
                presenter.shopFragmentChange();
                break;
        }
    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initListener() {
        layoutHome.setOnClickListener(this);
        layoutMe.setOnClickListener(this);
        ivVideo.setOnClickListener(this);
        layoutFasion.setOnClickListener(this);
        layoutShop.setOnClickListener(this);
    }

    public void onEventMainThread(EventMessage eventMessage) {
        Log.d("onEventMainThread", eventMessage.getMsg() + "   msg");

    }
    @Override
    protected void processLogic() {
        presenter = new MainPresenterImpl(this);
        presenter.init();
    }

    @Override
    public View getMyView() {
        return view;
    }

    @Override
    public LinearLayout getLayoutHome() {
        return layoutHome;
    }

    @Override
    public LinearLayout getLayoutMe() {
        return layoutMe;
    }

    @Override
    public LinearLayout getLayoutShop() {
        return layoutShop;
    }

    @Override
    public LinearLayout getLayoutFasion() {
        return layoutFasion;
    }

    @Override
    public ImageView getIvVideo() {
        return ivVideo;
    }

    @Override
    public ImageView getIvHome() {
        return ivHome;
    }

    @Override
    public ImageView getIvMe() {
        return ivMe;
    }

    @Override
    public ImageView getIvShop() {
        return ivShop;
    }

    @Override
    public ImageView getIvFasion() {
        return ivCf;
    }

    @Override
    public TextView getTvHome() {
        return tvHome;
    }

    @Override
    public TextView getTvMe() {
        return tvMe;
    }

    @Override
    public TextView getTvShop() {
        return tvShop;
    }

    @Override
    public TextView getTvFasion() {
        return tvCf;
    }

    @Override
    public MainActivity getMainActivity() {
        return this;
    }

}
