package com.lly.app.liveshow.activity.login.model;

import com.lly.app.liveshow.base.BaseStatusBean;

/**
 * Created by ly on 2016/7/30.
 */
public class LoginModel extends BaseStatusBean{
    /**
     * userid : 10929
     * name : 15001965903
     * token : 4f6dc3a61c5bfc3ba1f0d9d34d0e1b0a9d08e65d
     */

    private LoginDataBean data;
    /**
     * succeed : 1
     */

    private BaseStatusBean status;

    public LoginDataBean getData() {
        return data;
    }

    public void setData(LoginDataBean data) {
        this.data = data;
    }

    public BaseStatusBean getStatus() {
        return status;
    }

    public void setStatus(BaseStatusBean status) {
        this.status = status;
    }

    public class LoginDataBean {
        private String userId = "";
        private String name = "";//手机号
        private String token = "";
        private String IMToken;
        private String avatar;
        private String nickname;

        public String getUserId() {
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

        public String getIMToken() {
            return IMToken;
        }

        public void setIMToken(String IMToken) {
            this.IMToken = IMToken;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }


}
