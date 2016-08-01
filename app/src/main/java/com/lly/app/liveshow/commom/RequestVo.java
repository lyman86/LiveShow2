package com.lly.app.liveshow.commom;

/**
 * Created by ly on 2016/7/30.
 */
public class RequestVo {
    /**
     * 网络加载状态 false－失败  true－成功
     */
    public boolean sucess;
    /**
     * 网络返回处理好的数据
     */
    public Object resObj;
    /**
     * 标记位，用来确定是哪个网路请求，一个网络请求则需要
     */
    public int flag;
    public String requestUrl;

    public String erroMsg;


    public int erroNo;
}
