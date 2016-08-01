package com.lly.app.liveshow.activity.login;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.lly.app.liveshow.R;
import com.lly.app.liveshow.activity.login.presenter.LoginPresenter;
import com.lly.app.liveshow.activity.login.presenter.LoginPresenterImpl;
import com.lly.app.liveshow.activity.login.view.LoginView;
import com.lly.app.liveshow.base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ly on 2016/7/30.
 */
public class LoginActivity extends BaseActivity implements LoginView{
    @Bind(R.id.edt_name)
    EditText edtUserName;

    @Bind(R.id.edt_password)
    EditText edtUserPwd;

    @Bind(R.id.tv_reset)
    TextView tvReset;

    @Bind(R.id.btn_login)
    Button btnLogin;

    @Bind(R.id.linear_regist)
    LinearLayout layoutRegist;

    public static final int REGIST = 0;
    public static final int RESET = 1;

    private LoginPresenter presenter;

    @Override
    protected void onClickEvent(View paramView) {
        switch (paramView.getId()){
            case R.id.btn_login:
                presenter.login();
                break;
            case R.id.tv_reset:
                presenter.goReset();
                break;
            case R.id.linear_regist:
                presenter.goRegist();
                break;
        }
    }
    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void initListener() {
        btnLogin.setOnClickListener(this);
        layoutRegist.setOnClickListener(this);
        tvReset.setOnClickListener(this);
    }

    @Override
    protected void processLogic() {
        presenter = new LoginPresenterImpl(this);
    }

    @Override
    public EditText getEdtUserName() {
        return edtUserName;
    }

    @Override
    public EditText getEdtUserPwd() {
        return edtUserPwd;
    }

    @Override
    public Button getBtnLogin() {
        return btnLogin;
    }

    @Override
    public TextView getTvReset() {
        return tvReset;
    }

    @Override
    public LinearLayout getLayoutRegist() {
        return layoutRegist;
    }

    @Override
    public LoginActivity getActivity() {
        return this;
    }
}
