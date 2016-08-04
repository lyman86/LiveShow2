package com.lly.app.liveshow.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;


import com.lly.app.liveshow.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

public class UtilTool {

    public static void getWH(Activity context) {

        int screenWidth;
        int screenHeigh;

        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);

        screenWidth = dm.widthPixels;
        screenHeigh = dm.heightPixels;

        L.e("myW", "" + screenWidth);
        L.e("myH", "" + screenHeigh);

        //存储 当前手机的 高宽
        MyApplication.SHARE.edit().putInt(MyApplication.MYW, screenWidth).commit();
        MyApplication.SHARE.edit().putInt(MyApplication.MYH, screenHeigh).commit();

        //存储 当前手机的 高宽比例值
        MyApplication.SHARE.edit().putInt(MyApplication.MYW_RATIO, screenWidth / MyApplication.W).commit();
        MyApplication.SHARE.edit().putInt(MyApplication.MYH_RATIO, screenHeigh / MyApplication.H).commit();

    }

    /*
     * ViewType 布局类型 0-LinearLayout  1-RelativeLayout  2-FrameLayout
     * WHType 宽高类型 0—宽   1-高 2-宽高
     * view UI控件
     */
    public static void changeWH(final int ViewType, final int WHType, final View view) {

        ViewTreeObserver vto = view.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                if (ViewType == 0) {
                    LinearLayout.LayoutParams lps = (LinearLayout.LayoutParams) view.getLayoutParams(); // 取控件当前的布局参数
                    if (WHType == 0) {
                        lps.width = view.getWidth() * (MyApplication.SHARE.getInt(MyApplication.MYW_RATIO, 1));
                    } else if (WHType == 1) {
                        lps.height = view.getHeight() * (MyApplication.SHARE.getInt(MyApplication.MYH_RATIO, 1));
                    } else {
                        lps.width = view.getWidth() * (MyApplication.SHARE.getInt(MyApplication.MYW_RATIO, 1));
                        lps.height = view.getHeight() * (MyApplication.SHARE.getInt(MyApplication.MYH_RATIO, 1));
                    }
                    view.setLayoutParams(lps); // 使设置好的布局参数应用到控件myGrid
                } else if (ViewType == 1) {
                    RelativeLayout.LayoutParams lps = (RelativeLayout.LayoutParams) view.getLayoutParams(); // 取控件当前的布局参数
                    if (WHType == 0) {
                        lps.width = view.getWidth() * (MyApplication.SHARE.getInt(MyApplication.MYW_RATIO, 1));
                    } else if (WHType == 1) {
                        lps.height = view.getHeight() * (MyApplication.SHARE.getInt(MyApplication.MYH_RATIO, 1));
                    } else {
                        lps.width = view.getWidth() * (MyApplication.SHARE.getInt(MyApplication.MYW_RATIO, 1));
                        lps.height = view.getHeight() * (MyApplication.SHARE.getInt(MyApplication.MYH_RATIO, 1));
                    }

                    view.setLayoutParams(lps); // 使设置好的布局参数应用到控件myGrid
                } else {

                    FrameLayout.LayoutParams lps = (FrameLayout.LayoutParams) view.getLayoutParams(); // 取控件当前的布局参数
                    if (WHType == 0) {
                        lps.width = view.getWidth() * (MyApplication.SHARE.getInt(MyApplication.MYW_RATIO, 1));
                    } else if (WHType == 1) {
                        lps.height = view.getHeight() * (MyApplication.SHARE.getInt(MyApplication.MYH_RATIO, 1));
                    } else {
                        lps.width = view.getWidth() * (MyApplication.SHARE.getInt(MyApplication.MYW_RATIO, 1));
                        lps.height = view.getHeight() * (MyApplication.SHARE.getInt(MyApplication.MYH_RATIO, 1));
                    }
                }

            }
        });

    }

    /*
     * ViewType 布局类型 0-LinearLayout  1-RelativeLayout  2-FrameLayout
     * WHType 宽高类型 0—宽   1-高 2-宽高
     * view UI控件
     * valueW 宽值
     * valueH 高值
     */
    public static void setWH(final int ViewType, final int WHType, final View view, final int valueW, final int valueH) {

        ViewTreeObserver vto = view.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                if (ViewType == 0) {
                    LinearLayout.LayoutParams lps = (LinearLayout.LayoutParams) view.getLayoutParams(); // 取控件当前的布局参数
                    if (WHType == 0) {
                        lps.width = valueW;
                    } else if (WHType == 1) {
                        lps.height = valueH;
                    } else {
                        lps.width = valueW;
                        lps.height = valueH;
                    }
                    view.setLayoutParams(lps); // 使设置好的布局参数应用到控件myGrid
                } else if (ViewType == 1) {
                    RelativeLayout.LayoutParams lps = (RelativeLayout.LayoutParams) view.getLayoutParams(); // 取控件当前的布局参数
                    if (WHType == 0) {
                        lps.width = valueW;
                    } else if (WHType == 1) {
                        lps.height = valueH;
                    } else {
                        lps.width = valueW;
                        lps.height = valueH;
                    }

                    view.setLayoutParams(lps); // 使设置好的布局参数应用到控件myGrid
                } else {

                    FrameLayout.LayoutParams lps = (FrameLayout.LayoutParams) view.getLayoutParams(); // 取控件当前的布局参数
                    if (WHType == 0) {
                        lps.width = valueW;
                    } else if (WHType == 1) {
                        lps.height = valueH;
                    } else {
                        lps.width = valueW;
                        lps.height = valueH;
                    }
                }

            }
        });

    }

    public static JSONObject getJSONO_userAuth(Context context) {

        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("userId", SPUtils.get(context, MyApplication.USER_ID, ""));
            jsonObj.put("token", SPUtils.get(context, MyApplication.TOKEN, ""));
        } catch (JSONException e) {
            Log.e("JSONException", e.getMessage());
        }

        return jsonObj;

    }


    public static void setListViewHeightBasedOnChildren(ListView listView) {
        //获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { //listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); //计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); //统计所有子项的总高度
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        //listView.getDividerHeight()获取子项间分隔符占用的高度
        //params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

}
