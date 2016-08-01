package com.lly.app.liveshow.event;

/**
 * Created by ly on 2016/6/15.
 */
public class EventMessage {
    public String msg;
    public int flag;
    public EventMessage(String msg,int flag) {
        this.msg = msg;
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public int getFlag() {
        return flag;
    }
}
