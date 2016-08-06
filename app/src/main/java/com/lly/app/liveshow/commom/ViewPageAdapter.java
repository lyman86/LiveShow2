package com.lly.app.liveshow.commom;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

public class ViewPageAdapter extends PagerAdapter
    {
 
        List<ImageView> list = null;//我们用一个list存放所有的imageview
        public ViewPageAdapter (List<ImageView> _list)
        {
            list = _list;
        }
         
        @Override
        public void destroyItem(View container, int position, Object object) {
            // TODO Auto-generated method stub
            ((ViewPager)container).removeView(list.get(position));
             
        }
 
        @Override
        public Object instantiateItem(View container, int position) {
            // TODO Auto-generated method stub
            ((ViewPager) container).addView(list.get(position));
            return list.get(position);
        }
 
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.size();//返回数据的个数
        }
 
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return (arg0 == arg1);//这句话，比较重要，加上之后才能正确显示
        }
         
}