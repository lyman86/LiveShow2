package com.lly.app.liveshow.custom;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.lly.app.liveshow.R;
import com.lly.app.liveshow.utils.DensityUtils;


public class CustomSelectItem extends View implements View.OnClickListener{
    private Context context;
    /**
     * 定义左边，右边，和右边旁边的类型
     */
    private int leftType;
    private int rightType;
    private int centerType;
    private int rightSideType;
    private int leftSideType;
    /**
     * 左边文字的属性
     */
    private String leftText = "heh";
    private int leftTextColor = Color.BLACK;
    private float leftTextSize = 30;
    private float leftTextPaddingLeft;
    private float leftTextPaddingTop;
    private int leftTextWidth;

    /**
     * 左边图片的属性
     */
    private int leftImageScource;
    private float leftImagePaddingLeft;
    private int leftImageWidth;
    private float leftImagePaddingTop;

    /**
     * 左边旁边文字的属性
     */
    private String leftSideText = "gg";
    private int leftSideTextColor = Color.BLACK;

    private float leftSideTextSize = 30;
    private float leftSideTextPaddingLeft;
    private float leftSideTextPaddingTop;
    private int leftSideTextWidth;
    /**
     * 右边文字的属性
     */
    private String rightText = "";
    private int rightTextColor;
    private float rightTextSize;
    private float rightTextPaddingRight;
    private float rightTextPaddingTop;
    private int rightTextWidth;
    /**
     * 右边旁边文字的属性
     */
    private String rightSideText = "";
    private int rightSideTextColor = Color.BLACK;

    private float rightSideTextSize = 30;
    private float rightSideTextPaddingRight;
    private float rightSideTextPaddingTop;
    private int rightSideTextWidth;
    /**
     * 右边图片的属性
     */
    private int rightImageScource;
    private float rightImagePaddingRight;
    private int rightImageWidth;
    private float rightImagePaddingTop;
    private boolean rightImageScale = false;

    /**
     * 中间文字的属性
     */
    private String centerText = "";
    private int centerTextColor;
    private float centerTextSize;
    private float centerTextPaddingTop;
    private int centerTextWidth;
    /**
     * 中间图片的属性
     */
    private int centerImageScource;
    private float centerImagePaddingTop;
    private int centerImageWidth;
    /**
     * 整个View的宽高
     */
    private int viewWidth;
    private int viewHeight;
    /**
     * 定义画笔
     */
    private Paint paint;
    /**
     * 定义设置文字的矩形
     */
    private Rect bounds;
    private final int LEFT_TYPE_TEXT = 0;
    private final int LEFT_TYPE_IMAGE = 1;
    private final int RIGHT_TYPE_TEXT = 0;
    private final int RIGHT_TYPE_IMAGE = 1;
    private final int CENTER_TYPE_TEXT = 0;
    private final int CENTER_TYPE_IMAGE = 1;
    private final int RIGHT_SIDE_TYPE_TEXT = 0;
    private final int RIGHT_SIDE_TYPE_IMAGE = 1;
    private final int LEFT_SIDE_TYPE_TEXT = 0;
    private final int LEFT_SIDE_TYPE_IMAGE = 1;

    public static final int LEFT_VIEW = 100;
    public static final int RIGHT_VIEW = 200;
    public static final int CENTER_VIEW = 300;
    /**
     * 获取手指点击抬起后的位置
     */
    private float clickX;

    private OnBarViewClickListener mListener;

    @Override
    public void onClick(View v) {
        if (mListener!=null){
            //判断是否点击左边
            if (clickX>0&&clickX<=leftTextWidth+leftTextPaddingLeft+leftSideTextWidth+leftSideTextPaddingLeft + leftImageWidth + leftImagePaddingLeft+100){
                mListener.onBarViewClick(this,LEFT_VIEW);
                //判断是否点击右边
            }else if (clickX>=viewWidth-100-(rightTextWidth+rightTextPaddingRight+rightSideTextWidth+rightSideTextPaddingRight + rightImageWidth + rightImagePaddingRight)&&clickX<=viewWidth){
                mListener.onBarViewClick(this,RIGHT_VIEW);
                //判断是否点击中间
            }else if (clickX>=(viewWidth-centerImageWidth-centerTextWidth)/2&&clickX<=(viewWidth+centerImageWidth+centerTextWidth)/2){
                mListener.onBarViewClick(this,CENTER_VIEW);
            }
        }

    }

    public interface OnBarViewClickListener{
         void onBarViewClick(View v, int whitch);
    }
    public void setOnBarViewClickListener(OnBarViewClickListener listener){
        this.mListener = listener;
    }
    public CustomSelectItem(Context context) {
        this(context, null);
    }

    public CustomSelectItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSelectItem(Context context, AttributeSet attrs,
                            int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        setOnClickListener(this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.itemView, defStyleAttr, 0);
        //初始化类型
        initType(ta);
        ta.recycle();
    }

    /**
     * 初始化类型
     */
    private void initType(TypedArray ta) {
        leftType = ta.getInt(R.styleable.itemView_leftTypeSelect, -1);
        leftSideType = ta.getInt(R.styleable.itemView_leftSideTypeSelect, -1);
        rightType = ta.getInt(R.styleable.itemView_rightTypeSelect, -1);
        rightSideType = ta.getInt(R.styleable.itemView_rightSideTypeSelect, -1);
        centerType = ta.getInt(R.styleable.itemView_centerTypeSelect, -1);
        initAttribute(ta);
    }

    /**
     * 初始化各个属性
     *
     * @param ta
     */
    private void initAttribute(TypedArray ta) {
        switch (leftType) {
            case LEFT_TYPE_TEXT:
                getLeftTextFromXml(ta);
                break;
            case LEFT_TYPE_IMAGE:
                basePaintInit();
                getLeftImageFromXml(ta);
                break;
        }
        switch (leftSideType) {
            case LEFT_SIDE_TYPE_TEXT:
                getLeftSideTextFromXml(ta);
                break;
            case LEFT_SIDE_TYPE_IMAGE:
                //TODO
                break;
        }
        switch (rightType) {
            case RIGHT_TYPE_TEXT:
                getRightTextFromXml(ta);
                break;
            case RIGHT_TYPE_IMAGE:
                basePaintInit();
                getRightImageFromXml(ta);
                break;
        }
        switch (rightSideType) {
            case RIGHT_SIDE_TYPE_TEXT:
                getRightSideTextFromXml(ta);
                break;
            case RIGHT_SIDE_TYPE_IMAGE:
                //TODO
                break;
        }
        switch (centerType) {
            case CENTER_TYPE_TEXT:
                getCenterTextFromXml(ta);
                break;
            case CENTER_TYPE_IMAGE:
                getCenterImageFromXml(ta);
                break;
        }
    }

    private void basePaintInit() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    /**
     * 定义文字时候所初始化的画笔
     *
     * @param text
     * @param textColor
     * @param textSize
     */
    private void initPaint(String text, int textColor, float textSize) {
        basePaintInit();
        bounds = new Rect();
        paint.setColor(textColor);
        paint.setTextSize(DensityUtils.sp2px(context, textSize));
        paint.getTextBounds(text, 0, text.length(), bounds);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = getMeasuredWidth();
        viewHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (leftType) {
            case LEFT_TYPE_TEXT:
                initPaint(leftText, leftTextColor, leftTextSize);
                drawLeftText(canvas);
                break;
            case LEFT_TYPE_IMAGE:
                drawLeftImage(canvas);
                break;
        }
        switch (leftSideType) {
            case LEFT_SIDE_TYPE_TEXT:
                Log.d("leftSideType","   >>>leftSideType");
                initPaint(leftSideText, leftSideTextColor, leftSideTextSize);
                drawLeftSideText(canvas);
                break;
            case LEFT_SIDE_TYPE_IMAGE:
                //TODO
                break;
        }
        switch (rightType) {
            case RIGHT_TYPE_TEXT:
                initPaint(rightText, rightTextColor, rightTextSize);
                drawRightText(canvas);
                break;
            case RIGHT_TYPE_IMAGE:
                drawRightImage(canvas);
                break;
        }
        switch (rightSideType) {
            case RIGHT_SIDE_TYPE_TEXT:
                initPaint(rightSideText, rightSideTextColor, rightSideTextSize);
                drawRightSideText(canvas);
                break;
            case RIGHT_SIDE_TYPE_IMAGE:
                //TODO
                break;
        }
        switch (centerType) {
            case CENTER_TYPE_TEXT:
                initPaint(centerText, centerTextColor, centerTextSize);
                drawCenterText(canvas);
                break;
            case CENTER_TYPE_IMAGE:
                drawCenterImage(canvas);
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                clickX = event.getX();
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 画右边的图片
     *
     * @param canvas
     */
    private void drawRightImage(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), rightImageScource);
        int imageWidth = bitmap.getWidth();
        int imageHeight = bitmap.getHeight();
        Matrix matrix = new Matrix();
        if (rightImageScale){
            float scaleWidth = viewWidth / imageWidth;
            float scaleHeight = viewHeight *1.0f/ imageHeight;
            float scale = Math.min(scaleWidth, scaleHeight);
            matrix.postScale(scale / 2, scale / 2);
        }else{
            matrix.postScale(1, 1);
        }
        Bitmap res = Bitmap.createBitmap(bitmap, 0, 0, imageWidth, imageHeight, matrix, true);
        canvas.drawBitmap(res, viewWidth - res.getWidth() - rightImagePaddingRight, viewHeight / 2 - res.getHeight() / 2+rightImagePaddingTop, paint);
        rightImageWidth = res.getWidth();
    }

    /**
     * 画右边的文字
     *
     * @param canvas
     */
    private void drawRightText(Canvas canvas) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        float textCenterVerticalBaselineY = viewHeight / 2 - fm.descent + (fm.descent - fm.ascent) / 2;
        rightTextWidth = bounds.width();
        canvas.drawText(rightText, viewWidth - bounds.width() - rightTextPaddingRight, textCenterVerticalBaselineY + rightTextPaddingTop, paint);
    }

    /**
     * 画右边旁边的文字
     *
     * @param canvas
     */
    private void drawRightSideText(Canvas canvas) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        float textCenterVerticalBaselineY = viewHeight / 2 - fm.descent + (fm.descent - fm.ascent) / 2;
        rightSideTextWidth = bounds.width();
        canvas.drawText(rightSideText, viewWidth - bounds.width() - rightSideTextPaddingRight - rightImageWidth - rightImagePaddingRight, textCenterVerticalBaselineY+rightSideTextPaddingTop, paint);
    }

    /**
     * 画中间的文字
     *
     * @param canvas
     */
    private void drawCenterText(Canvas canvas) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        float textCenterVerticalBaselineY = viewHeight / 2 - fm.descent + (fm.descent - fm.ascent) / 2;
        centerTextWidth = bounds.width();
        canvas.drawText(centerText, (viewWidth - bounds.width())/2, textCenterVerticalBaselineY + centerTextPaddingTop, paint);
    }
    /**
     * 画中间的图片
     *
     * @param canvas
     */
    private void drawCenterImage(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), centerImageScource);
        int imageWidth = bitmap.getWidth();
        int imageHeight = bitmap.getHeight();
        float scaleWidth = viewWidth / imageWidth;
        float scaleHeight =viewHeight *1.0f/ imageHeight;
        float scale = Math.min(scaleWidth, scaleHeight);
        Matrix matrix = new Matrix();
        matrix.postScale(scale/2, scale/2);
        Bitmap res = Bitmap.createBitmap(bitmap, 0, 0, imageWidth, imageHeight, matrix, true);
        canvas.drawBitmap(res, (viewWidth-res.getWidth())/2, viewHeight / 2 - res.getHeight() / 2+centerImagePaddingTop, paint);
        centerImageWidth = res.getWidth();
    }
    /**
     * 画左边的文字
     *
     * @param canvas
     */
    private void drawLeftText(Canvas canvas) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        float textCenterVerticalBaselineY = viewHeight / 2 - fm.descent + (fm.descent - fm.ascent) / 2;
        leftTextWidth = bounds.width();
        canvas.drawText(leftText, leftTextPaddingLeft, textCenterVerticalBaselineY+leftTextPaddingTop, paint);
    }

    /**
     * 画左边的图片
     *
     * @param canvas
     */
    private void drawLeftImage(Canvas canvas) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), leftImageScource);
        int imageWidth = bitmap.getWidth();
        int imageHeight = bitmap.getHeight();
        float scaleWidth = viewWidth / imageWidth;
        float scaleHeight = viewHeight *1.0f/ imageHeight;
        float scale = Math.min(scaleWidth, scaleHeight);
        Matrix matrix = new Matrix();
        matrix.postScale(1, 1);
        Bitmap res = Bitmap.createBitmap(bitmap, 0, 0, imageWidth, imageHeight, matrix, true);
        canvas.drawBitmap(res, leftImagePaddingLeft, viewHeight / 2 - res.getHeight() / 2+leftImagePaddingTop, paint);
        leftImageWidth = res.getWidth();
    }

    /**
     * 画左边旁边的文字
     *
     * @param canvas
     */
    private void drawLeftSideText(Canvas canvas) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        float textCenterVerticalBaselineY = viewHeight / 2 - fm.descent + (fm.descent - fm.ascent) / 2;
        leftSideTextWidth = bounds.width();
        canvas.drawText(leftSideText, leftSideTextPaddingLeft + leftImageWidth + leftImagePaddingLeft, textCenterVerticalBaselineY+leftSideTextPaddingTop, paint);
    }

    /**
     * 从xml文件中获取左边文字的属性
     *
     * @param ta
     */
    private void getLeftTextFromXml(TypedArray ta) {
        leftText = ta.getString(R.styleable.itemView_leftTextSelect);
        leftTextColor = ta.getColor(R.styleable.itemView_leftTextColorSelect, Color.BLACK);
        leftTextSize = ta.getDimension(R.styleable.itemView_leftTextSizeSelect, 14.0f);
        if (leftTextSize != 14.0f) {
            leftTextSize = DensityUtils.px2sp(context, leftTextSize);
        }
        // 默认为0
        leftTextPaddingLeft = ta.getDimension(R.styleable.itemView_leftTextPaddingLeftSelect, 0);
        leftTextPaddingTop = ta.getDimension(R.styleable.itemView_leftTextPaddingTopSelect, 0);
    }

    /**
     * 从xml文件中获取左边边旁边文字的属性
     *
     * @param ta
     */
    private void getLeftSideTextFromXml(TypedArray ta) {
        leftSideText = ta.getString(R.styleable.itemView_leftSideTextSelect);
        leftSideTextColor = ta.getColor(R.styleable.itemView_leftSideTextColorSelect, Color.BLACK);
        leftSideTextSize = ta.getDimension(R.styleable.itemView_leftSideTextSizeSelect, 14.0f);
        if (leftSideTextSize != 14.0f) {
            leftSideTextSize = DensityUtils.px2sp(context, leftSideTextSize);
        }

        // 默认为0
        leftSideTextPaddingLeft = ta.getDimension(R.styleable.itemView_leftSideTextPaddingLeftSelect, 0);
        leftSideTextPaddingTop = ta.getDimension(R.styleable.itemView_leftSideTextPaddingTopSelect, 0);
        Log.d("getLeftSideTextFromXml", String.valueOf(leftSideTextPaddingLeft));

    }
    /**
     * 从xml文件中获取左边图片的属性
     *
     * @param ta
     */
    private void getLeftImageFromXml(TypedArray ta) {
        leftImageScource = ta.getResourceId(R.styleable.itemView_leftImageSourceSelect, R.mipmap.ic_launcher);
        leftImagePaddingLeft = ta.getDimension(R.styleable.itemView_leftImagePaddingLeftSelect, 0);
        leftImagePaddingTop = ta.getDimension(R.styleable.itemView_leftImagePaddingTopSelect, 0);
    }
    /**
     * 从xml文件中获取右边文字的属性
     *
     * @param ta
     */
    private void getRightTextFromXml(TypedArray ta) {
        rightText = ta.getString(R.styleable.itemView_rightTextSelect);
        rightTextColor = ta.getColor(R.styleable.itemView_rightTextColorSelect, Color.BLACK);
        rightTextSize = ta.getDimension(R.styleable.itemView_rightTextSizeSelect, 14.0f);
        Log.d("getLeftSideTextFromXml", String.valueOf(rightTextSize));
        if (rightTextSize != 14.0f) {
            rightTextSize = DensityUtils.px2sp(context, rightTextSize);
        }
        // 默认为0
        rightTextPaddingRight = ta.getDimension(R.styleable.itemView_rightTextPaddingRightSelect, 0);
        rightTextPaddingTop = ta.getDimension(R.styleable.itemView_rightTextPaddingTopSelect, 0);
    }

    /**
     * 从xml文件中获取中间文字的属性
     *
     * @param ta
     */
    private void getCenterTextFromXml(TypedArray ta) {
        centerText = ta.getString(R.styleable.itemView_centerTextSelect);
        centerTextColor = ta.getColor(R.styleable.itemView_centerTextColorSelect, Color.BLACK);
        centerTextSize = ta.getDimension(R.styleable.itemView_centerTextSizeSelect, 14.0f);
        if (centerTextSize != 14.0f) {
            centerTextSize = DensityUtils.px2sp(context, centerTextSize);
        }
        // 默认为0
        centerTextPaddingTop = ta.getDimension(R.styleable.itemView_centerTextPaddingTopSelect, 0);
    }
    /**
     * 从xml文件中获取中间图片的属性
     *
     * @param ta
     */
    private void getCenterImageFromXml(TypedArray ta) {
        centerImageScource = ta.getResourceId(R.styleable.itemView_centerImageSourceSelect, R.mipmap.ic_launcher);
        centerImagePaddingTop = ta.getDimension(R.styleable.itemView_centerImagePaddingTopSelect, 0);
    }

    /**
     * 从xml文件中获取右边旁边文字的属性
     *
     * @param ta
     */
    private void getRightSideTextFromXml(TypedArray ta) {
        rightSideText = ta.getString(R.styleable.itemView_rightSideTextSelect);
        rightSideTextColor = ta.getColor(R.styleable.itemView_rightSideTextColorSelect, Color.BLACK);
        rightSideTextSize = ta.getDimension(R.styleable.itemView_rightSideTextSizeSelect, 14.0f);
        if (rightSideTextSize != 14.0f) {
            rightSideTextSize = DensityUtils.px2sp(context, rightSideTextSize);
        }
        // 默认为0
        rightSideTextPaddingRight = ta.getDimension(R.styleable.itemView_rightSideTextPaddingRightSelect, 0);
        rightSideTextPaddingTop = ta.getDimension(R.styleable.itemView_rightSideTextPaddingTopSelect, 0);

    }

    /**
     * 从xml文件中获取右边图片的属性
     *
     * @param ta
     */
    private void getRightImageFromXml(TypedArray ta) {
        rightImageScource = ta.getResourceId(R.styleable.itemView_rightImageSourceSelect, R.mipmap.ic_launcher);
        rightImagePaddingRight = ta.getDimension(R.styleable.itemView_rightImagePaddingRightSelect, 0);
        rightImagePaddingTop = ta.getDimension(R.styleable.itemView_rightImagePaddingTopSelect, 0);

    }

    /**
     * 设置右边的文字,前提是xml文件已经定义了该文字属性
     *
     * @param rightText
     */
    public void setRightText(String rightText) {
        this.rightSideText = rightText;
        postInvalidate();
    }

    public void setCenterText(String centerText) {
        this.centerText = centerText;
        postInvalidate();
    }

    /**
     * 设置右边旁边的文字，前提是xml文件已经定义了该文字属性
     *
     * @param rightSideText
     */
    public void setRightSideText(String rightSideText) {
        this.rightSideText = rightSideText;
        postInvalidate();
    }

    public String getRightSideText(){
        return rightSideText;
    }


    /**
     * 设置右边的图片，xml文件没有定义该图片的属性
     *
     * @param imageScouce
     */
    public void setRightImageScouce(int imageScouce, int paddingRight) {
        this.rightImageScource = imageScouce;
        rightType = RIGHT_TYPE_IMAGE;
        rightImagePaddingRight = paddingRight;
        postInvalidate();
    }

    /**
     * 设置右边无类型
     */
    public void setRightTypeNo() {
        rightType = -1;
        postInvalidate();
    }

    public void setLeftText(String leftText, int leftTextColor, int leftTextSize, int leftTextPaddingLeft) {
        leftType = LEFT_TYPE_TEXT;
        this.leftText = leftText;
        this.leftTextColor = leftTextColor;
        this.leftTextSize = DensityUtils.sp2px(context, leftTextSize);
        ;
        this.leftTextPaddingLeft = DensityUtils.dp2px(context, leftTextPaddingLeft);
        ;
        postInvalidate();
    }

}
