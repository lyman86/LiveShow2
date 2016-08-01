package com.lly.app.liveshow.custom;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import com.lly.app.liveshow.R;

/**
 * Created by luoyan on 16/6/26.
 */
public class LoadListView extends ListView implements AbsListView.OnScrollListener{
    private View footerView;
    private int totalItemCount;
    private int lastVisibleItem;
    private boolean isLoading;
    private ILoadListener iLoadListener;

    private OnLoadScrollListener onLoadScrollListener;

    public void setOnLoadScrollListener(OnLoadScrollListener onLoadScrollListener){
        this.onLoadScrollListener = onLoadScrollListener;
    }

    public void setILoadListener(ILoadListener iLoadListener){
        this.iLoadListener = iLoadListener;
    }
    public interface ILoadListener{
        public void onLoad();
    }

    public interface OnLoadScrollListener{
        public void onLoadScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount);
        public void onLoadScrollStateChanged(AbsListView view, int scrollState);
    }

    public LoadListView(Context context) {
        this(context,null);
    }

    public LoadListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        footerView = inflater.inflate(R.layout.footer_layout,null);
        footerView.findViewById(R.id.load_layout).setVisibility(View.GONE);
        addFooterView(footerView);
        setOnScrollListener(this);

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {

        if (onLoadScrollListener!=null){
            onLoadScrollListener.onLoadScroll(view,firstVisibleItem,visibleItemCount,totalItemCount);
        }
        // TODO Auto-generated method stub
        this.lastVisibleItem = firstVisibleItem + visibleItemCount;
        this.totalItemCount = totalItemCount;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (onLoadScrollListener!=null){
            onLoadScrollListener.onLoadScrollStateChanged(view,scrollState);
        }

        // TODO Auto-generated method stub
        if (totalItemCount == lastVisibleItem
                && scrollState == SCROLL_STATE_IDLE) {
            if (!isLoading) {
                isLoading = true;
                footerView.findViewById(R.id.load_layout).setVisibility(View.VISIBLE);
                iLoadListener.onLoad();
            }
        }
    }

    public void loadAll(){
        isLoading = true;
    }

    public void loadComplete(){
        isLoading = false;
        footerView.findViewById(R.id.load_layout).setVisibility(View.GONE);
    }

}
