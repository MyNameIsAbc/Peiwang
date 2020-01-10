package com.example.peiwang;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.base.BaseActivity;
import com.example.base.Constant;
import com.example.bean.LoginSuccessBean;
import com.example.bean.MessageWaper;
import com.example.presenter.RegisterPresenter;
import com.example.utils.SharePreferencesUtils;
import com.example.view.RegisterView;
import com.sahooz.library.Country;
import com.sahooz.library.PickActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.base.App.countries;

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
    @BindView(R.id.tv_country)
    TextView tvCountry;
    @BindView(R.id.tv_country_code)
    TextView tvCountryCode;
    @BindView(R.id.iv_select_country)
    ImageView ivSelectCountry;
    @BindView(R.id.iv_country)
    ImageView ivCountry;
    @BindView(R.id.ll_login_country)
    ConstraintLayout llLoginCountry;
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
        ButterKnife.bind(this);
        initCountry();
        registerPresenter = new RegisterPresenter(this);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        userName = SharePreferencesUtils.getString(this, "phone", "");
        userPwd = SharePreferencesUtils.getString(this, "passward", "");

    }

    @Override
    protected void disarmState() {

    }

    @OnClick({R.id.tv_getVcode, R.id.bt_login,R.id.ll_login_country})
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
                registerPresenter.getValidateCode(tvCountryCode.getText()+"-"+userName);
                break;
            case R.id.bt_login:
                if (checkValidity()) {
                    registerPresenter.register(tvCountryCode.getText()+"-"+userName, userVercode, userPwd);
                }
                break;
            case R.id.ll_login_country:
                startActivityForResult(new Intent(getApplicationContext(), PickActivity.class), 111);
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
        LoginSuccessBean loginSuccessBean = (LoginSuccessBean) data;
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

    public void initCountry() {
        //获取 Locale  对象的正确姿势：
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = getResources().getConfiguration().locale;
        }
        String counrty = locale.getCountry();

        for (Country c:countries) {
            if (c.locale.equalsIgnoreCase(counrty)){
                ivCountry.setImageResource(c.flag);
                tvCountryCode.setText(c.code+"");
                tvCountry.setText(c.name);
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 111 && resultCode == Activity.RESULT_OK) {
            Country country = Country.fromJson(data.getStringExtra("country"));
            if(country.flag != 0) ivCountry.setImageResource(country.flag);
            tvCountry.setText(country.name);
            tvCountryCode.setText("+" + country.code);
        }
    }
}
