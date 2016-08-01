package com.lly.app.liveshow.base;

/**
 * Created by luoyan on 16/7/31.
 */
public class BaseStatusBean {
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
