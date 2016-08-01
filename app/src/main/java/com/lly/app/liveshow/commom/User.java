package com.lly.app.liveshow.commom;

/**
 * Created by luoyan on 16/7/31.
 */
public class User {
    public String userId;
    public String name = "";
    public String pwd = "";
    public String token = "";
    public int id;
    public String avartar = "";
    //用户获取验证码
    public String code;

    public User(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public User(String name, String pwd, String code) {
        this.pwd = pwd;
        this.name = name;
        this.code = code;
    }

    public User(){}
}
