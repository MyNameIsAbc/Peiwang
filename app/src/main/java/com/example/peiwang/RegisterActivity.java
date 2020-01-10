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
import com.example.base.Constant;
import com.example.bean.LoginSuccessBean;
import com.example.bean.MessageWaper;
import com.example.presenter.RegisterPresenter;
import com.example.utils.SharePreferencesUtils;
import com.example.view.RegisterView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements RegisterView {
    @BindView(R.id.et_login_phone)
    EditText etLoginPhone;
    @BindView(R.id.ll_register_phone)
    LinearLayout llRegisterPhone;
    @BindView(R.id.et_register_vcode)
    EditText etRegisterVcode;
    @BindView(R.id.tv_getVcode)
    TextView tvGetVcode;
    @BindView(R.id.ll_register_vcode)
    LinearLayout llRegisterVcode;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.ll_login_password)
    LinearLayout llLoginPassword;
    @BindView(R.id.bt_login)
    Button btLogin;

    RegisterPresenter registerPresenter;
    private String userName = "";
    private String userPwd = "";
    private String userVercode = "";
    private Vibrator vibrator;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected void getUnbinder() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        registerPresenter = new RegisterPresenter(this);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        userName = SharePreferencesUtils.getString(this, "phone", "");
        userPwd = SharePreferencesUtils.getString(this, "passward", "");

    }

    @Override
    protected void disarmState() {

    }

    @OnClick({R.id.tv_getVcode, R.id.bt_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_getVcode:
                getUserInfo();
                if (TextUtils.isEmpty(userName)) {
                    etLoginPhone.requestFocus();
                    showToast("手机号码不能为空");
                    Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
                    etLoginPhone.startAnimation(shake);
                    vibrator.vibrate(300);
                    return;
                }
                timer.start();
                tvGetVcode.setClickable(false);
                registerPresenter.getValidateCode(userName);
                break;
            case R.id.bt_login:
                if (checkValidity()) {
                    registerPresenter.register(userName,userVercode, userPwd);
                }
                break;
        }
    }

    @Override
    public void getValidateCode(String data) {
        showToast(data);
    }

    @Override
    public void showMessage(String msg) {
        showToast(msg);
    }

    @Override
    public void showRegisterData(Object data) {
        EventBus.getDefault().post(new MessageWaper(null, Constant.EVENT_LOGIN_SUCCESS));
        LoginSuccessBean loginSuccessBean=(LoginSuccessBean)data;
        SharePreferencesUtils.setString(getApplicationContext(), "accesstoken", loginSuccessBean.getData().getToken());
        SharePreferencesUtils.setString(getApplicationContext(), "phone", userName);
        SharePreferencesUtils.setString(this, "phone", userName);
        SharePreferencesUtils.setString(this, "passward", userPwd);
        finish();
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
        if (TextUtils.isEmpty(userPwd)) {
            etLoginPassword.requestFocus();
            showToast("密码不能为空");
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            etLoginPassword.startAnimation(shake);
            vibrator.vibrate(300);
            return false;
        }
        if (userPwd.length() < 6) {
            etLoginPassword.requestFocus();
            showToast("密码格式不正确");
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            etLoginPassword.startAnimation(shake);
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

    private void getUserInfo() {
        userName = etLoginPhone.getText().toString().trim();
        userPwd = etLoginPassword.getText().toString().trim();
        userVercode = etRegisterVcode.getText().toString().trim();
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
}
