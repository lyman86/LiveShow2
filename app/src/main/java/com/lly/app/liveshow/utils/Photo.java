package com.lly.app.liveshow.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.lly.app.liveshow.config.Config;

import java.io.File;


/**
 * Created by luoyan on 16/4/12.
 */
public class Photo {
    public static Uri photoUri;
    private static File tempFile = new File(Environment.getExternalStorageDirectory(), "myyll.jpg");
    public static void takePhoto(Activity activity){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 指定调用相机拍照后照片的储存路径
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        activity.startActivityForResult(cameraIntent, Config.CAMERA);
    }
    public static String getPicPath(){
        return tempFile.getAbsolutePath();
    }

    public static void getGarllery(Activity activity){
        Intent intent;
        intent = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, Config.GALLERY);
    }
}