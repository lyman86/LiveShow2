package com.lly.app.liveshow.commom;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lly.app.liveshow.R;
import com.lly.app.liveshow.utils.ViewHolder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;


public abstract class CommonAdapter<T> extends BaseAdapter
{
	protected Context mContext;
	protected List<T> mDatas;
	protected LayoutInflater mInflater;
	protected int mlayoutId;
	public static DisplayImageOptions baseOptions = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.mipmap.ic_launcher)
			.bitmapConfig(Bitmap.Config.RGB_565)
			.showImageForEmptyUri(R.mipmap.ic_launcher)
			.showImageOnFail(R.mipmap.ic_launcher).cacheInMemory(false)
			.cacheOnDisk(true).displayer(new FadeInBitmapDisplayer(1000))
			.build();

	public static DisplayImageOptions baseOptions2 = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.mipmap.default_avar)
			.bitmapConfig(Bitmap.Config.RGB_565)
			.showImageForEmptyUri(R.mipmap.default_avar)
			.showImageOnFail(R.mipmap.default_avar).cacheInMemory(false)
			.cacheOnDisk(true).displayer(new FadeInBitmapDisplayer(1000))
			.build();

	public static DisplayImageOptions baseOptions3 = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.mipmap.ic_launcher)
			.bitmapConfig(Bitmap.Config.RGB_565)
			.showImageForEmptyUri(R.mipmap.ic_launcher)
			.showImageOnFail(R.mipmap.ic_launcher).cacheInMemory(false)
			.cacheOnDisk(true).displayer(new FadeInBitmapDisplayer(1000))
			.build();

	public static DisplayImageOptions bannerDefault = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.mipmap.banner_default)
			.bitmapConfig(Bitmap.Config.RGB_565)
			.showImageForEmptyUri(R.mipmap.banner_default)
			.showImageOnFail(R.mipmap.banner_default).cacheInMemory(false)
			.cacheOnDisk(true).displayer(new FadeInBitmapDisplayer(1000))
			.build();

	
	public CommonAdapter(Context context, List<T> datas, int layoutId)
	{
		this.mContext = context;
		if (datas!=null)
		this.mDatas = datas;
		mDatas = new ArrayList<>();
		this.mlayoutId = layoutId;
		mInflater = LayoutInflater.from(context);
		ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));
	}
	

	@Override
	public int getCount()
	{
		Log.d("heh", String.valueOf(mDatas.size()));
		return mDatas.size();
	}


	@Override
	public T getItem(int position)
	{
		return mDatas.get(position);
	}


	@Override
	public long getItemId(int position)
	{
		return position;
	}


//	@Override
//	public abstract View getView(int position, View convertView, ViewGroup parent);
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		Log.d("getView","getView");
		ViewHolder holder = ViewHolder.get(mContext, convertView, parent, mlayoutId, position);
		
		convert(holder, getItem(position),position);
		
		return holder.getConvertView();
	}
	
	public abstract void convert(ViewHolder holder, T t,int position);

	public void setList(List<T>list){
		this.mDatas = list;
		super.notifyDataSetChanged();
	}

	public void addAll(List<T>list){
		this.mDatas.addAll(list);
		super.notifyDataSetChanged();
	}

	public void clearAll(){
		this.mDatas.clear();
		super.notifyDataSetChanged();
	}

	public void delete(T t){
		mDatas.remove(t);
		super.notifyDataSetChanged();
	}

	public void delete(int position){
		mDatas.remove(position);
		super.notifyDataSetChanged();
	}
}