package com.lly.app.liveshow.custom;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lly.app.liveshow.R;

public class CustomDialog extends Dialog {

	public CustomDialog(Context context) {
		super(context);

		// TODO Auto-generated constructor stub
	}

	public CustomDialog(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
	}

	public static class Builder {
		private Context mContext;
		private String mTitle;
		private String mMessage;
		private String mPositiveBtnText;
		private String mNagetiveBtnText;

		private TextView messageTv;
		private TextView titleTv;

		private Button sureBtn;
		private Button cancleBtn;
		private LinearLayout btnLayou;
//		private ProgressBar progressBar;
		private ImageView progressImg;
		private CustomDialog dialog;
		private View layout;
		private View viewTitleBottom;
		private View lineBottom;
		private LinearLayout rootView;

		private static final int GONE = 0;
		private static final int VISIBLE = 1;

		public  int mWidth = 600;
		private  int mHeight = 300;

		private OnClickListener mPositiveBtnOnclickListener;
		private OnClickListener mNagetiveBtnOncclickListener;

		public Builder(Context context) {
			mContext = context;

		}

		/**
		 * 设置消息
		 * @param msg
		 * @return
		 */
		public Builder setMessage(String msg) {
			mMessage = msg;
			return this;
		}

		/**
		 * 设置消息 @string
		 * @param msg
		 * @return
		 */
		public Builder setMessage(int msg) {
			mMessage = (String) mContext.getText(msg);
			return this;
		}

		/**
		 * 设置标题
		 * @return
		 */
		public Builder setTitle(String title) {
			mTitle = title;
			return this;
		}

		/**
		 * 设置标题 @string
		 * @return
		 */
		public Builder setTitle(int title) {
			mTitle = (String) mContext.getText(title);
			return this;
		}

		/**
		 * 设置positiveButton按钮
		 *
		 * @param positiveBtnText
		 * @param positiveBtnListener
		 * @return
		 */
		public Builder setPositiveButton(String positiveBtnText, OnClickListener positiveBtnListener) {
			mPositiveBtnOnclickListener = positiveBtnListener;
			mPositiveBtnText = positiveBtnText;
			return this;
		}

		/**
		 * 设置positiveButton按钮
		 * @param positiveBtnRes
		 * @param positiveBtnListener
         * @return
         */
		public Builder setPositiveButton(int positiveBtnRes, OnClickListener positiveBtnListener) {
			mPositiveBtnOnclickListener = positiveBtnListener;
			mPositiveBtnText = (String) mContext.getText(positiveBtnRes);
			return this;
		}

		/**
		 * 设置nagetiveButton按钮
		 * @param nagetiveBtnText
		 * @param nagetiveBtnListener
         * @return
         */
		public Builder setNagetiveButton(String nagetiveBtnText, OnClickListener nagetiveBtnListener) {
			mNagetiveBtnOncclickListener = nagetiveBtnListener;
			mNagetiveBtnText = nagetiveBtnText;
			return this;
		}

		/**
		 * 设置nagetiveButton按钮
		 * @param nagetiveBtnRes
		 * @param nagetiveBtnListener
         * @return
         */
		public Builder setNagetiveButton(int nagetiveBtnRes, OnClickListener nagetiveBtnListener) {
			mNagetiveBtnOncclickListener = nagetiveBtnListener;
			mNagetiveBtnText = (String) mContext.getText(nagetiveBtnRes);
			return this;
		}

		/**
		 * 设置dialog的具体宽高
		 * @param width
		 * @param height
         * @return
         */
		public Builder setDialogWidthAndHeight(int width, int height) {
			mWidth = width;
			mHeight = height;
			return this;
		}

		public CustomDialog create() {
			setDialogVIew();
			return dialog;
		}
       public void dismissDialog(){
    	      dialog.dismiss();
		      progressImg.clearAnimation();
       }
		public void setDialogLoading(int width, int height) {
			setMessage("加载中..");
			init();
			viewTitleBottom.setVisibility(View.GONE);
			setProgressbarState(VISIBLE);
			setDialogWidthAndHeight(width, height);
			create().show();
		}

		public void setDialogLoading(int width, int height,String message) {
			setMessage(message);
			setDialogWidthAndHeight(width, height);
			mPositiveBtnText = null;
			mNagetiveBtnText = null;
			mTitle = null;
			init();
			viewTitleBottom.setVisibility(View.GONE);
			setProgressbarState(VISIBLE);
			create().show();
		}

		public void setDialogAlert(int width, int height, String title, String message) {
			setDialogWidthAndHeight(width, height);
			setMessage(message);
			setTitle(title);
			setPositiveButton("确定", (OnClickListener) mContext);
			setNagetiveButton("取消", (OnClickListener) mContext);
			init();
			viewTitleBottom.setVisibility(View.VISIBLE);
			setProgressbarState(GONE);
			create().show();
		}
		Animation animation;
		public void setProgressbarState(int state) {
			switch (state) {
			case GONE:
				rootView.setBackgroundColor(Color.WHITE);
				progressImg.setVisibility(View.GONE);
				lineBottom.setVisibility(View.VISIBLE);
				break;
			case VISIBLE:
				rootView.setBackgroundColor(Color.TRANSPARENT);
				animation = AnimationUtils.loadAnimation(mContext, R.anim.progress);
				progressImg.startAnimation(animation);
				lineBottom.setVisibility(View.GONE);
				progressImg.setVisibility(View.VISIBLE);
				break;

			}
		}

		private void setDialogVIew() {
			dialog.getWindow().setLayout(mWidth, mHeight);
			dialog.setContentView(layout);
		}

		private void initView() {

			messageTv = (TextView) layout.findViewById(R.id.tv_message);
			titleTv = (TextView) layout.findViewById(R.id.tv_title);

			sureBtn = ((Button) layout.findViewById(R.id.bt_sure));
			cancleBtn = ((Button) layout.findViewById(R.id.bt_cancle));
			btnLayou = (LinearLayout) layout.findViewById(R.id.btn_llayout);
			progressImg = (ImageView) layout.findViewById(R.id.progress_img);
//			progressBar = (ProgressBar) layout.findViewById(R.id.progressbar);
			viewTitleBottom = layout.findViewById(R.id.title_bottom_view);
			rootView = (LinearLayout) layout.findViewById(R.id.bg);
			lineBottom = layout.findViewById(R.id.line_bottom);
			if (mMessage == null) {
				messageTv.setVisibility(View.GONE);
			} else {
				messageTv.setText(mMessage);
			}

			if (mTitle == null) {
				titleTv.setVisibility(View.GONE);
			} else {
				titleTv.setText(mTitle);
			}
			if (mPositiveBtnText == null) {
				sureBtn.setVisibility(View.GONE);
			} else {
				sureBtn.setText(mPositiveBtnText);
			}

			if (mNagetiveBtnText == null) {
				cancleBtn.setVisibility(View.GONE);
			} else {
				cancleBtn.setText(mNagetiveBtnText);
			}

			if (mPositiveBtnText == null && mNagetiveBtnText == null) {
				btnLayou.setVisibility(View.GONE);
			}

			initEvent();

		}

		private void initEvent() {
			if (mPositiveBtnOnclickListener != null) {
				sureBtn.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						mPositiveBtnOnclickListener.onClick(dialog,
								DialogInterface.BUTTON_POSITIVE);
					}
				});
			}
			if (mNagetiveBtnOncclickListener != null) {
				cancleBtn.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						mNagetiveBtnOncclickListener.onClick(dialog,
								DialogInterface.BUTTON_NEGATIVE);
					}
				});
			}
		}

		private void init() {
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			dialog = new CustomDialog(mContext, R.style.Dialog);
			layout = inflater.inflate(R.layout.dialog_custom, null);
			dialog.addContentView(layout, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			dialog.setCanceledOnTouchOutside(false);
			initView();

		}

		public void setOnkeyListener(OnKeyListener listener){
			dialog.setOnKeyListener(listener);
		}

	}






}
