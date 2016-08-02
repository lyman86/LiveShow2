package com.lly.app.liveshow.base;

import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import io.rong.eventbus.EventBus;

/**
 * Created by luoyan on 16/8/1.
 */
public abstract class EventBusActivity extends BaseActivity {

    @Override
    protected void EventBusSetting() {
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
