package com.lly.app.liveshow.activity.regist.model;

import com.lly.app.liveshow.base.BaseStatusBean;

/**
 * Created by ly on 2016/6/2.
 */
public class PhoneCodeModel extends BaseStatusBean{
    /**
     * verify : OK
     */

    private PhoneCodeDataBean data;
    /**
     * succeed : 1
     */

    private BaseStatusBean status;

    public PhoneCodeDataBean getData() {
        return data;
    }

    public void setData(PhoneCodeDataBean data) {
        this.data = data;
    }

    public BaseStatusBean getStatus() {
        return status;
    }

    public void setStatus(BaseStatusBean status) {
        this.status = status;
    }

    public class PhoneCodeDataBean {
        private String verify;

        public String getVerify() {
            return verify;
        }

        public void setVerify(String verify) {
            this.verify = verify;
        }
    }
}
