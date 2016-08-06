package com.lly.app.liveshow.activity.regist;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lly.app.liveshow.R;
import com.lly.app.liveshow.activity.login.LoginActivity;
import com.lly.app.liveshow.activity.regist.presenter.RegistPresenter;
import com.lly.app.liveshow.activity.regist.presenter.RegistPresenterImpl;
import com.lly.app.liveshow.activity.regist.view.RegistView;
import com.lly.app.liveshow.base.BaseActivity;
import com.lly.app.liveshow.custom.CustomSelectItem;
import com.lly.app.liveshow.http.url.RequestUrl;
import com.lly.app.liveshow.utils.TimeCount;

import butterknife.Bind;
import butterknife.BindDimen;
import butterknife.ButterKnife;

/**
 * Created by luoyan on 16/7/31.
 */
public class RegistActivity extends BaseActivity implements RegistView{
    @Bind(R.id.edt_name)
    EditText edtName;

    @Bind(R.id.edt_password)
    EditText edtPwd;

    @Bind(R.id.edt_code)
    EditText edtCode;

    @Bind(R.id.titleBar)
    CustomSelectItem titleBar;

    @Bind(R.id.btn_code)
    Button btnCode;

    @Bind(R.id.btn_regist)
    Button btnRegist;

    private TimeCount timeCount;
    private RegistPresenter presenter;

    //设置请求的url是注册还是忘记密码连接
    private String url = RequestUrl.YLL_REGISY;
    ///区分是注册还是忘记密码验证码
    private String code = RequestUrl.YLL_PHONE_CODE;
    //区分是注册还是忘记密码
    private int type;
    @Override
    protected void onClickEvent(View paramView) {
        switch (paramView.getId()){
            case R.id.btn_regist:
                presenter.regist(type);
                break;
            case R.id.btn_code:
                presenter.getPhoneCode(type);
                break;
        }
    }

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_regist);
    }

    @Override
    protected void initListener() {
        btnCode.setOnClickListener(this);
        btnRegist.setOnClickListener(this);
    }

    @Override
    protected void processLogic() {
        timeCount = new TimeCount(60000, 1000,btnCode);
        Intent intent = getIntent();
        type = intent.getIntExtra("type", LoginActivity.REGIST);
        presenter = new RegistPresenterImpl(this,timeCount,type);
    }

    @Override
    public EditText getEdtName() {
        return edtName;
    }

    @Override
    public EditText getEdtPwd() {
        return edtPwd;
    }

    @Override
    public EditText getEdtCode() {
        return edtCode;
    }

    @Override
    public Button getBtnCode() {
        return btnCode;
    }

    @Override
    public Button getBtnRegist() {
        return btnRegist;
    }

    @Override
    public CustomSelectItem getTitleBar() {
        return titleBar;
    }

    @Override
    public RegistActivity getRegistActivity() {
        return this;
    }
}
