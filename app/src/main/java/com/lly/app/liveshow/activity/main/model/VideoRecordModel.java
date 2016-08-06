package com.lly.app.liveshow.activity.main.model;

import com.lly.app.liveshow.base.BaseStatusBean;

public class VideoRecordModel extends BaseStatusBean{
    /**
     * origUrl : http://vodzqqsdylh.vod.126.net/vodzqqsdylh/1_59c01c4bf5b64bb994a0d85062af2dd9_1467819574309_1467819721331_61037-00000.flv
     * status : {"succeed":1}
     */
    private DataBean data;
    private BaseStatusBean status;
    public BaseStatusBean getStatus() {
        return status;
    }

    public void setStatus(BaseStatusBean status) {
        this.status = status;
    }
    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String origUrl;

        public String getOrigUrl() {
            return origUrl;
        }

        public void setOrigUrl(String origUrl) {
            this.origUrl = origUrl;
        }
    }
}
