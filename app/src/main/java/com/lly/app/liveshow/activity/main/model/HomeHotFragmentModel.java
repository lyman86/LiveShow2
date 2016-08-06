package com.lly.app.liveshow.activity.main.model;

import com.lly.app.liveshow.base.BaseStatusBean;

import java.util.List;

/**
 * Created by ly on 2016/6/7.
 */
public class HomeHotFragmentModel extends BaseStatusBean {
//    private HomeHotBannerModel homeHotBannerModel;
//
//    public HomeHotBannerModel getHomeHotBannerModel() {
//        return homeHotBannerModel;
//    }
//
//    public void setHomeHotBannerModel(HomeHotBannerModel homeHotBannerModel) {
//        this.homeHotBannerModel = homeHotBannerModel;
//    }

    /**
     * succeed : 1
     */

    private BaseStatusBean status;
    /**
     * faceUrl : http://localhost/API/Uploads/Picture/01.jpg
     * pictureUrl : http://localhost/API/Uploads/AnchorImage/576a4087ef09e.png
     * nickname : 231w21ewdwdww112212we12w11e211123
     * ctime : 178367727
     * anchor_user_id : 25
     * rtmpPullUrl : rtmp://v1.live.126.net/live/286e163c1a0f46f392c81af4159e799f
     */

    private List<HomeHotDataBean> data;

    public BaseStatusBean getStatus() {
        return status;
    }

    public void setStatus(BaseStatusBean status) {
        this.status = status;
    }

    public List<HomeHotDataBean> getData() {
        return data;
    }

    public void setData(List<HomeHotDataBean> data) {
        this.data = data;
    }

    public static class HomeHotDataBean {

        private String faceUrl;
        private String pictureUrl;
        private String nickname;
        private String ctime;
        private String anchor_user_id;
        private String rtmpPullUrl;
        private String desc;
        private int channel_id;
        private String cid;
        private String record;
        private int is_live;
        private int cur_visit_count;
        private int visit_count;


        public int getCur_visit_count() {
            return cur_visit_count;
        }

        public void setCur_visit_count(int cur_visit_count) {
            this.cur_visit_count = cur_visit_count;
        }

        public int getVisit_count() {
            return visit_count;
        }

        public void setVisit_count(int visit_count) {
            this.visit_count = visit_count;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getRecord() {
            return record;
        }

        public void setRecord(String record) {
            this.record = record;
        }

        public int getIs_live() {
            return is_live;
        }

        public void setIs_live(int is_live) {
            this.is_live = is_live;
        }

        public int getChannel_id() {
            return channel_id;
        }

        public void setChannel_id(int channel_id) {
            this.channel_id = channel_id;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getFaceUrl() {
            return faceUrl;
        }

        public void setFaceUrl(String faceUrl) {
            this.faceUrl = faceUrl;
        }

        public String getPictureUrl() {
            return pictureUrl;
        }

        public void setPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getAnchor_user_id() {
            return anchor_user_id;
        }

        public void setAnchor_user_id(String anchor_user_id) {
            this.anchor_user_id = anchor_user_id;
        }

        public String getRtmpPullUrl() {
            return rtmpPullUrl;
        }

        public void setRtmpPullUrl(String rtmpPullUrl) {
            this.rtmpPullUrl = rtmpPullUrl;
        }
    }
}
