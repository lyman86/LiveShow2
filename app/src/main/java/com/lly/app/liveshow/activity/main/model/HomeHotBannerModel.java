package com.lly.app.liveshow.activity.main.model;

import com.lly.app.liveshow.base.BaseStatusBean;
import java.util.List;

/**
 * Created by ly on 2016/6/24.
 */
public class HomeHotBannerModel extends BaseStatusBean {

    /**
     * succeed : 1
     */
    private BaseStatusBean status;
    /**
     * ad_id : 2
     * name : 母亲节
     * url : https://m.myyll.com/topic/5.html
     * image : https://test-live.myyll.com:8089/mobile/data/upload/image/20160429/1461888133370024346.jpg
     */

    private List<BannerDataBean> data;

    public BaseStatusBean getStatus() {
        return status;
    }

    public void setStatus(BaseStatusBean status) {
        this.status = status;
    }

    public List<BannerDataBean> getData() {
        return data;
    }

    public void setData(List<BannerDataBean> data) {
        this.data = data;
    }


    public class BannerDataBean {
        private String ad_id;
        private String name;
        private String url;
        private String image;

        public String getAd_id() {
            return ad_id;
        }

        public void setAd_id(String ad_id) {
            this.ad_id = ad_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
