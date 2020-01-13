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
import com.example.presenter.LoginPresenter;
import com.example.utils.SharePreferencesUtils;
import com.example.view.MvpView;
import com.sahooz.library.Country;
import com.sahooz.library.PickActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.base.App.countries;


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
        initCountry();
    }

    @Override
    protected void disarmState() {

    }


    @OnClick({R.id.tv_getVcode, R.id.bt_login, R.id.tv_vcode_login, R.id.tv_register,R.id.ll_login_country,R.id.iv_act_loginvecode_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_getVcode:
                if (checkValidityPhone()) {
                    timer.start();
                    tvGetVcode.setClickable(false);
                    loginPresenter.getVocode(userName);
                }
                break;
            case R.id.bt_login:
            case R.id.tv_vcode_login:
                if (checkValidity())
                    loginPresenter.loginVocode(tvCountryCode.getText()+"-"+userName, userVercode);
                break;
            case R.id.tv_register:
                gotoActivity(RegisterActivity.class);
                break;
            case R.id.ll_login_country:
                startActivityForResult(new Intent(getApplicationContext(), PickActivity.class), 111);
                break;
            case R.id.iv_act_loginvecode_back:
                finish();
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
        EventBus.getDefault().post(new MessageWaper(null, Constant.EVENT_LOGIN_SUCCESS));
        LoginSuccessBean loginSuccessBean = (LoginSuccessBean) data;
        SharePreferencesUtils.setString(getApplicationContext(), "accesstoken", loginSuccessBean.getData().getToken());
        SharePreferencesUtils.setString(getApplicationContext(), "phone", userName);
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
