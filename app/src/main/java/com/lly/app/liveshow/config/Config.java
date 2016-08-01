package com.lly.app.liveshow.config;

import android.os.Environment;

import java.io.File;

public class Config {
	    //用户详情中的五个属性
	    public static final int USER_ATTRS_SIZE = 5;
	    //照相选取
	    public static final int CAMERA = 0X001;
	    //从相册中选取
	    public static final int GALLERY = 0X002;
		public static final int CUT_PIC = 0X003;
	    public static File tempFile=new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/"+System.currentTimeMillis()+".jpg");
	    public static final String FILE_ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/myyll.jpg";
//	    public static final String SEND_PIC_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Trip/send.jpg";
}