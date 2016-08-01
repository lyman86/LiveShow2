package com.lly.app.liveshow.utils.vagueimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;


import java.io.IOException;
import java.lang.annotation.Target;


/**
 * Created by luoyan on 16/4/12.
 */
public class ImageUtils {
    Target target ;
    /**经过研究，发现，Options中有个属性inJustDecodeBounds，研究了一下，终于明白是什么意思了，SDK中的E文是这么说的
    　　If set to true, the decoder will return null (no bitmap), but the out... fields will still be set, allowing the caller to query the bitmap without having to allocate the memory for its pixels.
       意思就是说如果该值设为true那么将不返回实际的bitmap不给其分配内存空间而里面只包括一些解码边界信息即图片大小信息，那么相应的方法也就出来了，
       通过设置inJustDecodeBounds为true，获取到outHeight(图片原始高度)和 outWidth(图片的原始宽度)，然后计算一个inSampleSize(缩放值)，
       然后就可以取图片了，这里要注意的是，inSampleSize 可能小于0，必须做判断
   */
    public static Bitmap comp(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 获取这个图片的宽和高
        Bitmap bitmap = BitmapFactory.decodeFile(path, options); //此时返回bm为空
        options.inJustDecodeBounds = false;
        //计算缩放比
        int be = (int)(options.outHeight / (float)400);
        if (be <= 0)
            be = 1;
        options.inSampleSize = be;
        //重新读入图片，注意这次要把options.inJustDecodeBounds 设为 false哦
        bitmap=BitmapFactory.decodeFile(path,options);

        return  bitmap;
    }

    /**
     * 压缩图片
     * @param path 路径
     * @param value 缩放比
     * @return
     */
    public static Bitmap comp(String path,int value) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 获取这个图片的宽和高
        Bitmap bitmap = BitmapFactory.decodeFile(path, options); //此时返回bm为空

        options.inJustDecodeBounds = false;
        //计算缩放比
        int be = (int)(options.outHeight / (float)value);
        if (be <= 0)
            be = 1;
        options.inSampleSize = be;
        //重新读入图片，注意这次要把options.inJustDecodeBounds 设为 false哦
        bitmap=BitmapFactory.decodeFile(path,options);

        return  bitmap;
    }
}
