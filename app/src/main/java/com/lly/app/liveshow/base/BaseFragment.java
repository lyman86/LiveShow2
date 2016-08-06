package com.lly.app.liveshow.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lly.app.liveshow.R;
import com.lly.app.liveshow.custom.CustomDialog;
import com.lly.app.liveshow.utils.window.MyWindow;
import com.lly.app.liveshow.utils.window.WindowUtil;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment implements OnClickListener{
	private CustomDialog.Builder builder;
	private Toast mToast;
	public View view;
	public int layoutId;

	public BaseFragment() {

	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		setLayout();
		return inflater.inflate(layoutId,null);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		this.view = view;
		builder = new CustomDialog.Builder(getActivity());
		ButterKnife.bind(this,view);
		init();

	}

	private void init() {
		initView();
		initListener();
		processLogic();
	}

	@Override
	public void onClick(View v) {
		 onViewClick(v);
	}
	public abstract void onViewClick(View v);
	public abstract void setLayout();
	public void initView(){}
	public void initListener(){}
	public void processLogic(){}
	public void showProgressDialog() {
		MyWindow window = WindowUtil.getWindow(BaseFragment.this.getActivity());
		builder.setDialogLoading(window.winth * 2 / 3, window.height * 1 / 8);
	}

	public void dismissDialog() {
		builder.dismissDialog();
	}

	public void showToast(final String string) {
		if (mToast == null) {
			mToast = Toast.makeText(BaseFragment.this.getActivity(), string,
					Toast.LENGTH_LONG);
		} else {
			mToast.setText(string);
		}
		mToast.show();
	}

	public void configRefreshLayout(SwipeRefreshLayout swipeRefreshLayout){
		swipeRefreshLayout.setColorSchemeResources(
				R.color.colorPrimary,
				R.color.colorAccent,
				R.color.green);
		swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
	}

}
