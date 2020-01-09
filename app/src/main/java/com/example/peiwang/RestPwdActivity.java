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
import com.example.presenter.ResetPresenter;
import com.example.utils.SharePreferencesUtils;
import com.example.view.ResetView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RestPwdActivity extends BaseActivity implements ResetView {
    @BindView(R.id.et_reset_phone)
    EditText etResetPhone;
    @BindView(R.id.ll_reset_phone)
    LinearLayout llResetPhone;
    @BindView(R.id.et_register_vcode)
    EditText etRegisterVcode;
    @BindView(R.id.tv_getVcode)
    TextView tvGetVcode;
    @BindView(R.id.ll_register_vcode)
    LinearLayout llRegisterVcode;
    @BindView(R.id.et_reset_password)
    EditText etResetPassword;
    @BindView(R.id.ll_reset_password)
    LinearLayout llResetPassword;
    @BindView(R.id.et_reset_password2)
    EditText etResetPassword2;
    @BindView(R.id.ll_reset_password2)
    LinearLayout llResetPassword2;
    @BindView(R.id.bt_login)
    Button btLogin;
    private String userName = "";
    private String userPwd = "", userPwd2 = "", vercode = "";
    private Vibrator vibrator;
    ResetPresenter resetPresenter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_reset_passward;
    }

    @Override
    protected void getUnbinder() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        resetPresenter = new ResetPresenter(this);
    }

    @Override
    protected void disarmState() {

    }

    @OnClick({R.id.tv_getVcode, R.id.bt_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_getVcode:
                userName = etResetPhone.getText().toString().trim();
                if (TextUtils.isEmpty(userName)) {
                    showToast("手机号码不能为空");
                    Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
                    etResetPhone.startAnimation(shake);
                    vibrator.vibrate(300);

                } else {
                    timer.start();
                    tvGetVcode.setClickable(false);
                    resetPresenter.getValidateCode(userName);
                }
                break;
            case R.id.bt_login:
                if (checkValidity()) {
                    resetPresenter.resetPassward(userName, userPwd, vercode);
                }
                break;
        }
    }



    private boolean checkValidity() {
        getUserInfo();
        if (TextUtils.isEmpty(userName)) {
            etResetPhone.requestFocus();
            showToast("手机号码不能为空");
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            etResetPhone.startAnimation(shake);
            vibrator.vibrate(300);
            return false;
        }
        if (TextUtils.isEmpty(userPwd)) {
            etResetPassword.requestFocus();
            showToast("密码不能为空");
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            etResetPassword.startAnimation(shake);
            vibrator.vibrate(300);
            return false;
        }
        if (userPwd.length() < 6) {
            etResetPassword.requestFocus();
            showToast("密码格式不正确");
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            etResetPassword.startAnimation(shake);
            vibrator.vibrate(300);
            return false;
        }

        if (TextUtils.isEmpty(userPwd2)) {
            etResetPassword.requestFocus();
            showToast("确认密码不能为空");
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            etResetPassword2.startAnimation(shake);
            vibrator.vibrate(300);
            return false;
        }
        if (userPwd2.length() < 6) {
            etResetPassword2.requestFocus();
            showToast("密码格式不正确");
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            etResetPassword2.startAnimation(shake);
            vibrator.vibrate(300);
            return false;
        }

        if (!userPwd2.equals(userPwd)) {
            showToast("密码不一致");
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            etResetPassword2.startAnimation(shake);
            vibrator.vibrate(300);
        }
        if (TextUtils.isEmpty(vercode)) {
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
        userName = etResetPhone.getText().toString().trim();
        userPwd = etResetPassword.getText().toString().trim();
        userPwd2 = etResetPassword2.getText().toString().trim();
        vercode = etRegisterVcode.getText().toString().trim();
    }

    @Override
    public void getValidateCode(String data) {
        showToast(data);
    }

    @Override
    public void resetSuccess(String data) {
        showToast(data);
        SharePreferencesUtils.setString(this, "phone", userName);
        SharePreferencesUtils.setString(this, "passward", userPwd);
        finish();
        gotoActivity(MainActivity.class);
    }

    CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {
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

    }

    @Override
    public void showMessage(String msg) {

    }
}
