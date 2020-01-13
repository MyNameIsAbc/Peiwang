package com.example.peiwang;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
import android.widget.Toast;

import com.example.base.App;
import com.example.base.BaseActivity;
import com.example.base.Constant;
import com.example.bean.LoginSuccessBean;
import com.example.bean.MessageWaper;
import com.example.presenter.LoginPresenter;
import com.example.utils.SharePreferencesUtils;
import com.example.view.MvpView;
import com.orhanobut.logger.Logger;
import com.sahooz.library.Country;
import com.sahooz.library.ExceptionCallback;
import com.sahooz.library.PickActivity;
import com.tuya.smart.android.user.api.ILoginCallback;
import com.tuya.smart.android.user.bean.User;
import com.tuya.smart.home.sdk.TuyaHomeSdk;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.base.App.countries;

public class LoginActivity extends BaseActivity implements MvpView {

    @BindView(R.id.et_login_phone)
    EditText etLoginPhone;
    @BindView(R.id.ll_login_phone)
    LinearLayout llLoginPhone;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.ll_login_password)
    LinearLayout llLoginPassword;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.tv_vcode_login)
    TextView tvVcodeLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_resetpwd)
    TextView tvResetpwd;

    LoginPresenter loginPresenter;
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
    private Vibrator vibrator;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void getUnbinder() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        userName = SharePreferencesUtils.getString(getApplicationContext(), "phone", "");
        userPwd = SharePreferencesUtils.getString(getApplicationContext(), "passward", "");
        if (!TextUtils.isEmpty(userName)) {
            etLoginPhone.setText(userName);
        }
        if (!TextUtils.isEmpty(userPwd)) {
            etLoginPassword.setText(userPwd);
        }
        initCountry();
    }

    @Override
    protected void disarmState() {

    }

    @OnClick({R.id.tv_resetpwd, R.id.bt_login, R.id.tv_vcode_login, R.id.tv_register,R.id.ll_login_country,R.id.iv_act_login_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_resetpwd:
                gotoActivity(RestPwdActivity.class);
                break;
            case R.id.bt_login:
                if (checkValidity()) {
                    loginPresenter.login( tvCountryCode.getText()+"-"+userName, userPwd);

                }
                break;
            case R.id.tv_vcode_login:
                gotoActivity(LoginVercodeActivity.class);
                break;
            case R.id.tv_register:
                gotoActivity(RegisterActivity.class);
                break;
            case R.id.ll_login_country:
                startActivityForResult(new Intent(getApplicationContext(), PickActivity.class), 111);
                break;
            case R.id.iv_act_login_back:
                finish();
                break;
        }
    }


    private boolean checkValidity() {
        getUserInfo();
        if (TextUtils.isEmpty(userName)) {
            etLoginPhone.requestFocus();
            showToast("手机号码不能为空");
            Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
            etLoginPhone.startAnimation(shake);
            vibrator.vibrate(300);
            return false;
        }
        if (TextUtils.isEmpty(userPwd)) {
            etLoginPassword.requestFocus();
            showToast("密码不能为空");
            Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
            etLoginPassword.startAnimation(shake);
            vibrator.vibrate(300);
            return false;
        }
        if (userPwd.length() < 6) {
            etLoginPassword.requestFocus();
            showToast("密码格式不正确");
            Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
            etLoginPassword.startAnimation(shake);
            vibrator.vibrate(300);
            return false;
        }
        return true;
    }

    private void getUserInfo() {
        userName =etLoginPhone.getText().toString().trim();
        userPwd = etLoginPassword.getText().toString().trim();
    }

    @Override
    public void getData(Object data) {
        LoginSuccessBean loginSuccessBean = (LoginSuccessBean) data;
        Logger.d("LoginSuccessBean:" + loginSuccessBean.toString());
        SharePreferencesUtils.setString(getApplicationContext(), "accesstoken", loginSuccessBean.getData().getToken());
        SharePreferencesUtils.setString(getApplicationContext(), "phone", userName);
        SharePreferencesUtils.setString(getApplicationContext(), "passward", userPwd);
        EventBus.getDefault().post(new MessageWaper(null, Constant.EVENT_LOGIN_SUCCESS));
        //uid登陆
//        TuyaHomeSdk.getUserInstance().loginOrRegisterWithUid(tvCountryCode.getText().toString(), App.tuyaInfoBean.getData().getUid(), App.tuyaInfoBean.getData().getPassword(), new ILoginCallback() {
//            @Override
//            public void onSuccess(User user) {
//                Toast.makeText(LoginActivity.this, "登录成功，用户名：" , Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(String code, String error) {
//                Toast.makeText(LoginActivity.this, "code: " + code + "error:" + error, Toast.LENGTH_SHORT).show();
//            }
//        });
        finish();
    }

    @Override
    public void showMessage(String msg) {
        showToast(msg);
    }

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
