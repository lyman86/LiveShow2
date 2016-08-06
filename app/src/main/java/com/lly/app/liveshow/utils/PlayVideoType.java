package com.lly.app.liveshow.utils;

import android.content.Context;
import android.content.Intent;

import com.lly.app.liveshow.R;
import com.lly.app.liveshow.activity.main.model.HomeHotFragmentModel;
import com.lly.app.liveshow.activity.main.model.VideoRecordModel;

/**
 * Created by luoyan on 16/8/5.
 */
public class PlayVideoType {
    private Context context;

    public PlayVideoType(Context context) {
        this.context = context;
    }

    public void playRecord(HomeHotFragmentModel.HomeHotDataBean data,VideoRecordModel model) {

        if(model.getData().getOrigUrl()!=null){
            String decodeType = "software";  //解码类型，默认软件解码
            String mediaType = "videoondemand"; //媒体类型，默认网络直播
            Intent intent=new Intent();
            //把多个参数传给NEVideoPlayerActivity
            intent.putExtra("media_type", mediaType);
            intent.putExtra("decode_type", decodeType);
            intent.putExtra("videoPath", model.getData().getOrigUrl());
            intent.putExtra("dickName", data.getNickname());
            intent.putExtra("faceUrl", data.getFaceUrl());
            intent.putExtra("liverID", data.getAnchor_user_id());//主播id
//            intent.setClass(context, MyInfoHistoryVideoActivity.class);
//            context.startActivity(intent);
        }else{
            T.showLong(context,context.getResources().getString(R.string.record_loss));
        }
    }

    public void playVideo(HomeHotFragmentModel.HomeHotDataBean data) {
        String decodeType = "software";  //解码类型，默认软件解码
        String mediaType = "livestream"; //媒体类型，默认网络直播
        String url=data.getRtmpPullUrl();

        if(url!=null){
            Intent intent=new Intent();
            //把多个参数传给NEVideoPlayerActivity
            intent.putExtra("media_type", mediaType);
            intent.putExtra("decode_type", decodeType);
            intent.putExtra("videoPath", url);
            intent.putExtra("dickName", data.getNickname());
            intent.putExtra("faceUrl", data.getFaceUrl());
            intent.putExtra("liverID", data.getAnchor_user_id());//主播id
            intent.putExtra("channel_id",""+data.getChannel_id());//频道id

            long time = Long.parseLong(data.getCtime());
            long currentTime = System.currentTimeMillis();
            intent.putExtra("liveTime", String.valueOf((currentTime/1000-time)));//直播了多少时间
//            intent.setClass(context, NEVideoPlayerActivity.class);
//            context.startActivity(intent);
        }else{
            T.showLong(context,context.getResources().getString(R.string.viedeo_loss));
        }
    }
}
