package com.lly.app.liveshow.utils;

import android.os.Handler;
import android.os.Message;

import com.lly.app.liveshow.custom.InsideViewPager;

public class PicturePlay extends Thread{
        private InsideViewPager viewPager;
        private int count;
        private int position = 1;
        public PicturePlay() {
        }


        public void setViewPagers(InsideViewPager viewPager,int count){
            this.viewPager = viewPager;
            this.count = count;
        }

//        public void setOpen(boolean open){
//            this.open = open;
//        }

        private Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int position = msg.arg1;
                try {
                    viewPager.setCurrentItem(position-1);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        @Override
        public void run() {
            while (true){
                if (position>count){
                    position = 1;
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = Message.obtain();
                message.arg1 = position;
                handler.sendMessage(message);
                position++;
            }

        }
    }