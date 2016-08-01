package com.lly.app.liveshow.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ly on 2016/6/2.
 */
public class CheckInfo {
    /**
     * 用来判断是否是电话号码
     * @param mobiles
     * @return
     */
    public static boolean checkPhoneNumber(String mobiles){
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[0-9])|(14[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean checkCode(String code){
        Pattern p = Pattern.compile("[1-9]\\d{5}(?!\\d)");
        Matcher m = p.matcher(code);
        return m.matches();
    }
}
