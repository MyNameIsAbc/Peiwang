package com.example.peiwang;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.base.BaseActivity;
import com.example.bean.LoginSuccessBean;
import com.example.presenter.LoginPresenter;
import com.example.utils.SharePreferencesUtils;
import com.example.view.MvpView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginVercodeActivity extends BaseActivity implements MvpView {
    @BindView(R.id.et_login_phone)
    EditText etLoginPhone;
    @BindView(R.id.ll_login_phone)
    LinearLayout llLoginPhone;
    @BindView(R.id.et_register_vcode)
    EditText etRegisterVcode;
    @BindView(R.id.tv_getVcode)
    TextView tvGetVcode;
    @BindView(R.id.ll_login_vcode)
    LinearLayout llLoginVcode;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.tv_vcode_login)
    TextView tvVcodeLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;

    private String userName = "";
    private String userVercode = "";
    private Vibrator vibrator;

    LoginPresenter loginPresenter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login_vecode;
    }

    @Override
    protected void getUnbinder() {

    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        userName = SharePreferencesUtils.getString(getApplicationContext(), "phone", "");
        if (!TextUtils.isEmpty(userName)) {
            etLoginPhone.setText(userName);
        }

    }

    @Override
    protected void disarmState() {

    }


    @OnClick({R.id.tv_getVcode, R.id.bt_login, R.id.tv_vcode_login, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_getVcode:
                if (checkValidityPhone()){
                    timer.start();
                    tvGetVcode.setClickable(false);
                    loginPresenter.getVocode(userName);
                }
                break;
            case R.id.bt_login:
            case R.id.tv_vcode_login:
                if (checkValidity())
                    loginPresenter.loginVocode(userName,userVercode);
                break;
            case R.id.tv_register:
                gotoActivity(RegisterActivity.class);
                break;
        }
    }




    private void getUserInfo() {
        userName = etLoginPhone.getText().toString().trim();
        userVercode = etRegisterVcode.getText().toString().trim();
    }

    private boolean checkValidity() {
        getUserInfo();
        if (TextUtils.isEmpty(userName)) {
            etLoginPhone.requestFocus();
            showToast("手机号码不能为空");
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            etLoginPhone.startAnimation(shake);
            vibrator.vibrate(300);
            return false;
        }
        if (TextUtils.isEmpty(userVercode)) {
            etRegisterVcode.requestFocus();
            showToast("验证码不能为空");
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            etRegisterVcode.startAnimation(shake);
            vibrator.vibrate(300);
            return false;
        }
        return true;
    }

    private boolean checkValidityPhone() {
        getUserInfo();
        if (TextUtils.isEmpty(userName)) {
            etLoginPhone.requestFocus();
            showToast("手机号码不能为空");
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            etLoginPhone.startAnimation(shake);
            vibrator.vibrate(300);
            return false;
        }

        return true;
    }


    CountDownTimer timer = new CountDownTimer(120 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tvGetVcode.setText("还剩" + millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            tvGetVcode.setText("获取验证码");
            tvGetVcode.setClickable(true);
        }
    };

    @Override
    public void getData(Object data) {
        LoginSuccessBean loginSuccessBean=(LoginSuccessBean)data;
        SharePreferencesUtils.setString(getApplicationContext(), "accesstoken", loginSuccessBean.getData().getToken());
        SharePreferencesUtils.setString(getApplicationContext(), "phone", userName);
        finish();
    }

    @Override
    public void showMessage(String msg) {
        showToast(msg);
    }
}