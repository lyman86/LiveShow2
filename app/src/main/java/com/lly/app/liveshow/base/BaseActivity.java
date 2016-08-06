package com.lly.app.liveshow.base;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.lly.app.liveshow.config.Config;
import com.lly.app.liveshow.custom.CustomDialog;
import com.lly.app.liveshow.myinterface.GetPictureListener;
import com.lly.app.liveshow.utils.vagueimage.ImageUtils;
import com.lly.app.liveshow.utils.window.MyWindow;
import com.lly.app.liveshow.utils.window.WindowUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;


public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener, DialogInterface.OnKeyListener {

    public String TAG_CLASS_NAME = "";
    // 防暴力点击
    protected long lastClickTime = 0;
    private Context context;
    private CustomDialog.Builder builder = null;
    private MyWindow window;
    private Toast mToast;

    private GetPictureListener listener;
    private int CODE_FOR_WRITE_PERMISSION = 0;

    private File tempFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "myyll.jpg");
    private String cutPicUrl="";


    public void setPictureListener(GetPictureListener listener){
        this.listener = listener;
    }
    public BaseActivity() {
    }

    /**
     * Android生命周期回调方法-创建
     */
    @Override
    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        TAG_CLASS_NAME = getClass().getSimpleName();
        context = this;

        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        builder = new CustomDialog.Builder(this);
        window = WindowUtil.getWindow(this);
        initBundle(paramBundle);
        init();
    }

    protected void initBundle(Bundle paramBundle) {
    }

    // 使用系统当前日期加以调整作为照片的名称
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }

    /**
     * 初始化
     */
    private void init() {
        initLayout();
        ButterKnife.bind(this);
        EventBusSetting();
        initListener();
        processLogic();

    }



    public void showProgressDialog() {
        builder.setDialogLoading(window.winth * 2 / 3, window.height * 1 / 8, "");
        builder.setOnkeyListener(this);
    }

    public void clossProgressDialog(){
        builder.dismissDialog();
    }

    public void showDialogAlert(String title, String message) {
        builder.setDialogAlert(window.winth * 2 / 3, window.height * 1 / 6, title, message);
        builder.setOnkeyListener(this);
    }

    @Override
    protected void onDestroy() {
//        builder.dismissDialog();
        super.onDestroy();
    }


    /**
     * 防暴力点击 上次点击时间, lastClickTime 本次点击时间, time 时间差, timeD 多长时间内点击无效, timelong
     */
    protected boolean isFastDoubleClick(int timelong) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (timeD > timelong) {
            lastClickTime = time;
            return true;
        }
        return false;
    }

    /**
     * 点击屏幕收起键盘
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager manager;
        manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.onTouchEvent(event);
    }

    public void showToast(final String string) {
        if (mToast == null) {
            mToast = Toast.makeText(this, string, Toast.LENGTH_LONG);
            mToast.setGravity(Gravity.CENTER,0,0);
        } else {
            mToast.setText(string);
        }
        mToast.show();
    }
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            dialog.dismiss();
        }
        return false;
    }

    public void hideSoftInput(){
        //隐藏软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            //取消
            case CustomDialog.BUTTON_NEGATIVE:
                dialog.dismiss();
                break;
            case CustomDialog.BUTTON_POSITIVE:
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap;
        if (resultCode==RESULT_OK){
            switch (requestCode){
                case Config.GALLERY:
                        clipPhoto(data.getData());
                    break;
                case Config.CAMERA:
                        clipPhoto(Uri.fromFile(tempFile));
                    break;
                case Config.CUT_PIC:
                    if (listener!=null){
                        bitmap = ImageUtils.comp(Config.FILE_ROOT_PATH,600);
                        listener.getPicture(bitmap,Config.FILE_ROOT_PATH);
                    }
                    break;
            }

        }
    }



    /**
     * 裁剪图片方法实现
     *
     * @param uri 图片来源
     */
    public void clipPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例，这里设置的是正方形（长宽比为1:1）
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 500);
        intent.putExtra("outputY", 500);

        intent.putExtra("return-data", false);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
//        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(intent, Config.CUT_PIC);
    }

    public Uri compressImage(Uri uri) throws IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 获取这个图片的宽和高
        Bitmap bitmap = BitmapFactory.decodeFile(getRealFilePath(this, uri), options); //此时返回bm为空
        options.inJustDecodeBounds = false;
        //计算缩放比
        int be = (int) (options.outHeight / (float) 500);
        if (be <= 0)
            be = 1;
        options.inSampleSize = be;
        //重新读入图片，注意这次要把options.inJustDecodeBounds 设为 false哦
        bitmap = BitmapFactory.decodeFile(getRealFilePath(this, uri), options);
        // int w = bitmap.getWidth();
        // int h = bitmap.getHeight();
        // System.out.println(w+"   "+h);

        Uri uriN = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null, null));
        return uriN;
    }

    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri)
            return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
    @Override
    public void onClick(View v) {
        onClickEvent(v);
    }

    /**
     * 设置监听事件
     *
     * @param paramView
     */
    protected abstract void onClickEvent(View paramView);

    /**
     * 初始化布局文件
     */
    protected abstract void initLayout();

    /**
     * 关联监听
     */
    protected void initListener(){};

    /**
     * 逻辑处理
     */
    protected void processLogic(){};

    protected void EventBusSetting(){};

}
