package com.lly.app.liveshow.utils;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewHolder
{
	private SparseArray<View> mViews;
	private int mPosition;
	private View mConvertView;

	public View getConvertView()
	{
		return mConvertView;
	}

	public ViewHolder(Context context, ViewGroup parent, int layoutId, int position)
	{
		this.mViews = new SparseArray<View>();
		this.mPosition = position;
		this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
		this.mConvertView.setTag(this);
	}
	
	public static ViewHolder get(Context context, View convertView, 
			ViewGroup parent, int layoutId, int position)
	{
		if (null == convertView)
		{
			return new ViewHolder(context, parent, layoutId, position);
		}
		else
		{
			ViewHolder holder = (ViewHolder) convertView.getTag();
			holder.mPosition = position;
			
			return holder;
		}
	}
	

	public <T extends View>T getView(int viewId)
	{
		View view = mViews.get(viewId);
		
		if (null == view)
		{
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		
		return (T) view;
	}
	
	public ViewHolder setText(int viewId, String text)
	{
		TextView tv = getView(viewId);
		tv.setText(text);
		
		return this;
	} 
}