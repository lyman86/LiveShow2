
package com.lly.app.liveshow.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {
    private OnScrollBottomListener mListener;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public interface OnScrollBottomListener {
        void OnSrollBottom();
    }

    public void setOnScrollBottomListener(OnScrollBottomListener mListener) {
        this.mListener = mListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        View view = (View) getChildAt(getChildCount() - 1);
        int d = view.getBottom();
        d -= (getHeight() + getScrollY());
        if (d == 0) {
            if (mListener != null) {
                mListener.OnSrollBottom();
            }
        } else {
            super.onScrollChanged(l, t, oldl, oldt);
        }

    }
}