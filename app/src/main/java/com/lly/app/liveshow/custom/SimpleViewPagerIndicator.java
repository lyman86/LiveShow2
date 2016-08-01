package com.lly.app.liveshow.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SimpleViewPagerIndicator extends LinearLayout
{

	private static final int COLOR_TEXT_NORMAL = Color.parseColor("#808080");
	private static final int COLOR_INDICATOR_COLOR = Color.parseColor("#ff2d47");
	private String[] mTitles;
	private int mTabCount;
	private int mIndicatorColor = COLOR_INDICATOR_COLOR;
	private float mTranslationX;
	private Paint mPaint = new Paint();
	private int mTabWidth;
	private int selectPosition;
	private int colorNomal = Color.parseColor("#808080");
	private int colorSelect = Color.parseColor("#ff2d47");
	private OnViewPagerChangeListener mListener;
	private OnSimpleItemClickListener onSimpleItemClickListener;
	public interface OnSimpleItemClickListener{
		void onSimpleItemClick(int position);
	}

	public void setOnSimpleItemClickListener(OnSimpleItemClickListener onSimpleItemClickListener){
		this.onSimpleItemClickListener = onSimpleItemClickListener;
	}

	public interface OnViewPagerChangeListener{
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);


		public void onPageSelected(int position);


		public void onPageScrollStateChanged(int state);
	}
	public void setOnViewPagerChangeListener(OnViewPagerChangeListener listener){
		this.mListener = listener;
	}
	public SimpleViewPagerIndicator(Context context)
	{
		this(context, null);
	}

	public SimpleViewPagerIndicator(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		mPaint.setColor(mIndicatorColor);
		mPaint.setStrokeWidth(9.0F);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		super.onSizeChanged(w, h, oldw, oldh);
		mTabWidth = w / mTabCount;
	}

	public void setTitles(String[] titles,int selectPosition)
	{
		mTitles = titles;
		mTabCount = titles.length;
		generateTitleView();
		this.selectPosition = selectPosition;

	}

	public void setIndicatorColor(int indicatorColor)
	{
		this.mIndicatorColor = indicatorColor;
	}

	@Override
	protected void dispatchDraw(Canvas canvas)
	{
		super.dispatchDraw(canvas);
		canvas.save();
		canvas.translate(mTranslationX, getHeight());
		canvas.drawLine(0, 0, mTabWidth, 0, mPaint);
		canvas.restore();
	}

	public void scroll(int position, float offset)
	{
		/**
		 * <pre>
		 *  0-1:position=0 ;1-0:postion=0;
		 * </pre>
		 */
		mTranslationX = getWidth() / mTabCount * (position + offset);
		invalidate();
	}


	@Override
	public boolean dispatchTouchEvent(MotionEvent ev)
	{
		return super.dispatchTouchEvent(ev);
	}

	private void generateTitleView()
	{
		if (getChildCount() > 0)
			this.removeAllViews();
		int count = mTitles.length;

		setWeightSum(count);
		for (int i = 0; i < count; i++)
		{
			final int pos = i;
			TextView tv = new TextView(getContext());
			LayoutParams lp = new LayoutParams(0,
					LayoutParams.MATCH_PARENT);
			lp.weight = 1;
			tv.setGravity(Gravity.CENTER);
			if (i==selectPosition){
				tv.setTextColor(COLOR_INDICATOR_COLOR);
			}else{
				tv.setTextColor(COLOR_TEXT_NORMAL);
			}

			tv.setText(mTitles[i]);
			tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
			tv.setLayoutParams(lp);
			tv.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (onSimpleItemClickListener!=null){
						onSimpleItemClickListener.onSimpleItemClick(pos);
					}
				}
			});
			addView(tv);
		}
	}

	public void setViewPageChange(ViewPager viewPager){
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				if (mListener!=null){
					mListener.onPageScrolled(position,positionOffset,positionOffsetPixels);
				}
				scroll(position,positionOffset);
			}

			@Override
			public void onPageSelected(int position) {
				if (mListener != null) {
					mListener.onPageSelected(position);
				}
				setSelecttitle(position);
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				if (mListener!=null){
					mListener.onPageScrollStateChanged(state);
				}

			}
		});


	}

	private void setSelecttitle(int position) {
		int count = getChildCount();
		for (int i =0;i<count;i++){
			TextView tv = (TextView) getChildAt(i);
			if (i==position){
				tv.setTextColor(colorSelect);
			}else{
				tv.setTextColor(colorNomal);
			}
		}

	}

}
