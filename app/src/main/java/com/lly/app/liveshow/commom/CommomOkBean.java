package com.lly.app.liveshow.commom;

/**
 * Created by ly on 2016/7/30.
 */
public class CommomOkBean {
    /**
     * action : OK
     */

    private  CommonOkData data;
    /**
     * succeed : 1
     */

    private CommonOkDataStatusBean status;

    public CommonOkData getData() {
        return data;
    }

    public void setData(CommonOkData data) {
        this.data = data;
    }

    public CommonOkDataStatusBean getStatus() {
        return status;
    }

    public void setStatus(CommonOkDataStatusBean status) {
        this.status = status;
    }

    public class CommonOkData {
        private String action;

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }
    }

    public class CommonOkDataStatusBean {
        private int succeed;
        private int error_code;
        private String error_desc;

        public int getError_code() {
            return error_code;
        }

        public void setError_code(int error_code) {
            this.error_code = error_code;
        }

        public String getError_desc() {
            return error_desc;
        }

        public void setError_desc(String error_desc) {
            this.error_desc = error_desc;
        }
        public int getSucceed() {
            return succeed;
        }

        public void setSucceed(int succeed) {
            this.succeed = succeed;
        }
    }
}
