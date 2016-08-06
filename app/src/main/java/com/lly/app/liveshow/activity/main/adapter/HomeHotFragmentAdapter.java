package com.lly.app.liveshow.activity.main.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lly.app.liveshow.R;
import com.lly.app.liveshow.activity.main.model.HomeHotFragmentModel;
import com.lly.app.liveshow.commom.CommonAdapter;
import com.lly.app.liveshow.utils.PlayVideoType;
import com.lly.app.liveshow.utils.ViewHolder;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.List;

/**
 * Created by luoyan on 16/8/5.
 */
public class HomeHotFragmentAdapter extends CommonAdapter<HomeHotFragmentModel.HomeHotDataBean> {
    private Context context;
    private PlayVideoType playVideoType;
    public HomeHotFragmentAdapter(Context context, List<HomeHotFragmentModel.HomeHotDataBean> datas, int layoutId) {
        super(context, datas, layoutId);
        this.context = context;
        playVideoType = new PlayVideoType(context);
    }

    @Override
    public void convert(ViewHolder holder, HomeHotFragmentModel.HomeHotDataBean homeHotFragmentModel, int position) {
        ImageView ivAvartar = holder.getView(R.id.avartar);
        ImageView ivBg = holder.getView(R.id.hot_bg);
        TextView tvStatus = holder.getView(R.id.tv_status);
        TextView tvDes = holder.getView(R.id.tv_des);
        RelativeLayout itemLayoutTop = holder.getView(R.id.item_layout_top);
        ImageLoader.getInstance().displayImage(homeHotFragmentModel.getFaceUrl(), ivAvartar, CommonAdapter.baseOptions3);
        ImageLoader.getInstance().displayImage(homeHotFragmentModel.getPictureUrl(), ivBg, CommonAdapter.baseOptions3);
        int isLive = homeHotFragmentModel.getIs_live();
        if (isLive == 0) {
            tvStatus.setText("回放");
            if (homeHotFragmentModel.getVisit_count()>=10000){
                holder.setText(R.id.tv_people_count,homeHotFragmentModel.getVisit_count()/10000+" 万观看");
            }else{
                holder.setText(R.id.tv_people_count,homeHotFragmentModel.getVisit_count()+" 观看");
            }
        } else {
            tvStatus.setText("直播");
            if (homeHotFragmentModel.getCur_visit_count()>=10000){
                holder.setText(R.id.tv_people_count,homeHotFragmentModel.getCur_visit_count()/10000+" 万人");
            }else{
                holder.setText(R.id.tv_people_count,homeHotFragmentModel.getCur_visit_count()+" 人");
            }
        }
        //描述为空，则隐藏描述内容
        if (TextUtils.isEmpty(homeHotFragmentModel.getDesc())){
            tvDes.setVisibility(View.GONE);
        }else{
            tvDes.setVisibility(View.VISIBLE);
            tvDes.setText(homeHotFragmentModel.getDesc());
        }
        holder.setText(R.id.tv_nick, homeHotFragmentModel.getNickname());
        initListener(itemLayoutTop,ivBg,homeHotFragmentModel,position);
    }

    private void initListener(RelativeLayout itemLayoutTop, ImageView ivBg, final HomeHotFragmentModel.HomeHotDataBean homeHotFragmentModel, int position) {
        itemLayoutTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入个人界面
            }
        });
        ivBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (homeHotFragmentModel.getIs_live()==0){
                    playVideoType.playRecord(homeHotFragmentModel,null);
                }else{
                    playVideoType.playVideo(homeHotFragmentModel);
                }
            }
        });
    }
}