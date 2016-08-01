package com.lly.app.liveshow.activity.regist.model;

import com.lly.app.liveshow.activity.login.model.LoginModel;
import com.lly.app.liveshow.base.BaseStatusBean;

/**
 * Created by ly on 2016/6/2.
 */
public class RegistModel extends BaseStatusBean{


    /**
     * userid : 10929
     * name : 15001965903
     * token : 4f6dc3a61c5bfc3ba1f0d9d34d0e1b0a9d08e65d
     */

    private RegistDataBean data;
    /**
     * succeed : 1
     */

    private BaseStatusBean status;

    public RegistDataBean getData() {
        return data;
    }

    public void setData(RegistDataBean data) {
        this.data = data;
    }

    public BaseStatusBean getStatus() {
        return status;
    }

    public void setStatus(BaseStatusBean status) {
        this.status = status;
    }

    public class RegistDataBean {
        private String userId;
        private String name;
        private String token;

        public String getUserid() {
            return userId;
        }

        public void setUserid(String userid) {
            this.userId = userid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }


}
