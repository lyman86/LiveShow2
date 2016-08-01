package com.lly.app.liveshow.activity.login.presenter;
import android.content.Intent;
import com.lly.app.liveshow.MyApplication;
import com.lly.app.liveshow.R;
import com.lly.app.liveshow.activity.main.MainActivity;
import com.lly.app.liveshow.activity.login.LoginActivity;
import com.lly.app.liveshow.activity.login.biz.LoginBiz;
import com.lly.app.liveshow.activity.login.biz.LoginBizImpl;
import com.lly.app.liveshow.activity.login.model.LoginModel;
import com.lly.app.liveshow.activity.login.view.LoginView;
import com.lly.app.liveshow.activity.regist.RegistActivity;
import com.lly.app.liveshow.commom.OnLoadListener;
import com.lly.app.liveshow.commom.RequestVo;
import com.lly.app.liveshow.commom.User;
import com.lly.app.liveshow.event.EventMessage;
import com.lly.app.liveshow.utils.JsonUtil;
import com.lly.app.liveshow.utils.SPUtils;
import com.lly.app.liveshow.utils.T;
import io.rong.eventbus.EventBus;

/**
 * Created by ly on 2016/7/30.
 */
public class LoginPresenterImpl implements LoginPresenter,OnLoadListener {
    private LoginBiz loginBiz;
    private LoginView loginView;
    private LoginActivity activity;
    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        loginBiz = new LoginBizImpl(this);
        activity = loginView.getActivity();
    }

    @Override
    public void login() {
        String userName = loginView.getEdtUserName().getText().toString();
        String userPwd = loginView.getEdtUserPwd().getText().toString();
        loginBiz.login(new User(userName,userPwd),activity);
    }

    @Override
    public void goRegist() {
        Intent intent = new Intent(activity,RegistActivity.class);
        intent.putExtra("type",activity.REGIST);
        activity.startActivity(intent);
    }

    @Override
    public void goReset() {
        Intent intent = new Intent(activity, RegistActivity.class);
        intent.putExtra("type", activity.RESET);
        activity.startActivity(intent);
    }

    @Override
    public void loadStatus(RequestVo requestVo) {
        activity.clossProgressDialog();
        if (requestVo.sucess){
            LoginModel model = JsonUtil.jsonToEntity((String) requestVo.resObj,LoginModel.class);
            int status = model.getStatus().getSucceed();
            if (status==1){
                EventBus.getDefault().post(new EventMessage(MainActivity.FINISH_ME,-1));
                SPUtils.put(activity, MyApplication.USER_ID,model.getData().getUserId());
                SPUtils.put(activity, MyApplication.TOKEN,model.getData().getToken());
                SPUtils.put(activity,MyApplication.IM_TOKEN,model.getData().getIMToken());
                SPUtils.put(activity,MyApplication.USER_NAME,model.getData().getNickname());
                SPUtils.put(activity,MyApplication.USER_PHOTO,model.getData().getAvatar());
                activity.startActivity(new Intent(activity, MainActivity.class));
                T.showShort(activity,activity.getString(R.string.toast_login_suces));
                activity.finish();
            }else{
                T.showShort(activity,model.getStatus().getError_desc());
            }
        }else{
            T.showShort(activity,requestVo.erroMsg);
        }
    }
}
