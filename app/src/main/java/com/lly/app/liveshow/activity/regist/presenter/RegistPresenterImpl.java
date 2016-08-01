package com.lly.app.liveshow.activity.regist.presenter;
import android.content.Intent;

import com.lly.app.liveshow.MyApplication;
import com.lly.app.liveshow.R;
import com.lly.app.liveshow.activity.main.MainActivity;
import com.lly.app.liveshow.activity.login.LoginActivity;
import com.lly.app.liveshow.activity.login.model.LoginModel;
import com.lly.app.liveshow.activity.regist.RegistActivity;
import com.lly.app.liveshow.activity.regist.biz.RegistBiz;
import com.lly.app.liveshow.activity.regist.biz.RegistBizImpl;
import com.lly.app.liveshow.activity.regist.model.PhoneCodeModel;
import com.lly.app.liveshow.activity.regist.view.RegistView;
import com.lly.app.liveshow.commom.OnLoadListener;
import com.lly.app.liveshow.commom.RequestVo;
import com.lly.app.liveshow.commom.User;
import com.lly.app.liveshow.event.EventMessage;
import com.lly.app.liveshow.http.url.RequestUrl;
import com.lly.app.liveshow.utils.JsonUtil;
import com.lly.app.liveshow.utils.SPUtils;
import com.lly.app.liveshow.utils.T;
import com.lly.app.liveshow.utils.TimeCount;

import io.rong.eventbus.EventBus;

/**
 * Created by luoyan on 16/7/31.
 */
public class RegistPresenterImpl implements RegistPresenter,OnLoadListener{
    private RegistView registView;
    private RegistBiz registBiz;
    private RegistActivity activity;
    private TimeCount timeCount;

    public RegistPresenterImpl(RegistView registView,TimeCount timeCount,int type) {
        this.registView = registView;
        this.timeCount = timeCount;
        registBiz = new RegistBizImpl(this);
        activity = registView.getRegistActivity();
        init(type);
    }

    private void init(int type) {
        if (type==LoginActivity.RESET){
            registView.getBtnRegist().setText("确定");
            registView.getTitleBar().setCenterText("找回密码");
        }
    }

    /**
     * 获取验证码
     * @param type
     */
    @Override
    public void getPhoneCode(int type) {
        String url = "";
        switch (type){
            case LoginActivity.REGIST:
                url = RequestUrl.YLL_PHONE_CODE;
                break;
            case LoginActivity.RESET:
                url = RequestUrl.YLL_PHONE_CODE_RESET;
                break;
        }
        String phone = registView.getEdtName().getText().toString();
        registBiz.getPhoneCode(phone,url,registView.getRegistActivity());
    }

    /**
     * 注册
     * @param type
     */
    @Override
    public void regist(int type) {
        String name = registView.getEdtName().getText().toString();
        String pwd = registView.getEdtPwd().getText().toString();
        String code = registView.getEdtCode().getText().toString();
        String url = "";
        switch (type){
            case LoginActivity.REGIST:
                url = RequestUrl.YLL_REGISY;
                break;
            case LoginActivity.RESET:
                url = RequestUrl.YLL_RESET;
                break;
        }
        registBiz.regist(new User(name,pwd,code),url,activity);
    }

    @Override
    public void loadStatus(RequestVo requestVo) {
        if (requestVo.sucess){
            if (requestVo.requestUrl.equals(RequestUrl.YLL_PHONE_CODE)||requestVo.requestUrl.equals(RequestUrl.YLL_PHONE_CODE_RESET)){
                PhoneCodeModel model = JsonUtil.jsonToEntity((String) requestVo.resObj,PhoneCodeModel.class);
                int status = model.getStatus().getSucceed();
                if (status==1){
                    T.showShort(activity,activity.getString(R.string.toast_regist_phone_code_success));
                    timeCount.start();
                }else{
                    T.showShort(activity,model.getStatus().getError_desc());
                }
            }else{
                LoginModel model = JsonUtil.jsonToEntity((String)requestVo.resObj,LoginModel.class);
                int status = model.getStatus().getSucceed();
                if (status==1){
                    if (requestVo.requestUrl.equals(RequestUrl.YLL_REGISY)){
                        SPUtils.put(activity, MyApplication.USER_ID,model.getData().getUserId());
                        SPUtils.put(activity,MyApplication.TOKEN,model.getData().getToken());
                        SPUtils.put(activity,MyApplication.IM_TOKEN,model.getData().getIMToken());
                        SPUtils.put(activity,MyApplication.USER_NAME,model.getData().getNickname());
                        SPUtils.put(activity,MyApplication.USER_PHOTO,model.getData().getAvatar());
                        activity.startActivity(new Intent(activity, MainActivity.class));
                        EventBus.getDefault().post(new EventMessage("finish",-1));
                        activity.finish();
                        T.showShort(activity,activity.getString(R.string.regist_sucess));
                    }else{
                        activity.startActivity(new Intent(activity, LoginActivity.class));
                        activity.finish();
                        T.showShort(activity,activity.getString(R.string.modify_sucess));
                    }
                }else{
                    T.showShort(activity,model.getStatus().getError_desc());
                }
            }
        }else{
            T.showShort(activity,requestVo.erroMsg);
        }
        activity.clossProgressDialog();
    }
}
