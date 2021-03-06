package com.example.peiwang;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.base.BaseActivity;
import com.example.bean.ShengboBean;
import com.example.bean.TuYaDeviceBean;
import com.example.bean.TuYaTokenBean;
import com.example.bean.TuyaInfoBean;
import com.example.net.ApiService;
import com.example.net.RetrofitManager;
import com.example.utils.MediaPlayerUtils;
import com.example.utils.SharePreferencesUtils;
import com.example.utils.WifiUtils;
import com.orhanobut.logger.Logger;
import com.sahooz.library.Country;
import com.sahooz.library.ExceptionCallback;
import com.tuya.smart.android.user.api.ILoginCallback;
import com.tuya.smart.android.user.bean.User;
import com.tuya.smart.config.TuyaAPConfig;
import com.tuya.smart.config.TuyaConfig;
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.home.sdk.builder.ActivatorBuilder;
import com.tuya.smart.sdk.api.ITuyaActivator;
import com.tuya.smart.sdk.api.ITuyaActivatorGetToken;
import com.tuya.smart.sdk.api.ITuyaSmartActivatorListener;
import com.tuya.smart.sdk.bean.DeviceBean;
import com.tuya.smart.sdk.enums.ActivatorModelEnum;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PeiWangActivity extends BaseActivity {
    @BindView(R.id.ll_return)
    LinearLayout llReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_do)
    TextView tvDo;
    @BindView(R.id.et_passward)
    EditText etPassward;
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.et_id)
    EditText etId;
    @BindView(R.id.peiwang_tv_change)
    TextView peiwangTvChange;
    @BindView(R.id.peiwang_iv_show)
    ImageView peiwangIvShow;
    private List<String> list = new ArrayList<>();
    String ssid;
    NetworkConnectChangedReceiver networkConnectChangedReceiver = null;
    int type;

    CountDownTimer timer;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_peiwang;
    }

    @Override
    protected void getUnbinder() {

    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        type = getIntent().getIntExtra("type", -1);
        list.clear();
        etId.setText(new WifiUtils(this).getWifiName());
        if (type == 1)
            tvTitle.setText("声波配网");
        else
            tvTitle.setText("涂鸦配网");
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        networkConnectChangedReceiver = new NetworkConnectChangedReceiver();
        registerReceiver(networkConnectChangedReceiver, filter);
    }

    @Override
    protected void disarmState() {
        unregisterReceiver(networkConnectChangedReceiver);
    }


    public void startPeiWang(String ssid, String passward) {
        showLoading();
        if (type == 1) {
            RetrofitManager.getInstance().getRetrofit()
                    //动态代理创建GithubAPI对象
                    .create(ApiService.class)
                    .peiWang(ssid, passward)
                    //指定上游发送事件线程
                    .subscribeOn(Schedulers.computation())
                    //指定下游接收事件线程
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ShengboBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ShengboBean s) {
                            hideLoading();
                            Logger.d("peiwnag :" + s.toString());
                            MediaPlayerUtils.getInstance().startPlay(s.getData());
                        }

                        @Override
                        public void onError(Throwable e) {
                            hideLoading();
                            Logger.d("peiwnag :" + e.toString());
                            Logger.d("onError:" + e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else {
            RetrofitManager.getInstance().getRetrofit()
                    //动态代理创建GithubAPI对象
                    .create(ApiService.class)
                    .getTuYaToken(SharePreferencesUtils.getString(this, "accesstoken", ""))
                    //指定上游发送事件线程
                    .subscribeOn(Schedulers.computation())
                    //指定下游接收事件线程
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<TuYaTokenBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(TuYaTokenBean s) {
                            hideLoading();
                            TuyaPeiWang(s.getData().getDeviceTokenToApp(), s.getData().getToken());
                        }

                        @Override
                        public void onError(Throwable e) {
                            hideLoading();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

    }

    @OnClick({R.id.ll_return, R.id.tv_do, R.id.btn_start, R.id.peiwang_tv_change, R.id.peiwang_iv_show})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_return:
                finish();
                break;
            case R.id.tv_do:
                break;
            case R.id.btn_start:
                if (TextUtils.isEmpty(etPassward.getText().toString())) {
                    Toast.makeText(this, "请输入密码！", Toast.LENGTH_SHORT).show();
                    return;
                }
                startPeiWang(ssid, etPassward.getText().toString());
                break;
            case R.id.peiwang_tv_change:
                WifiUtils.changeWifi(this);
                break;
            case R.id.peiwang_iv_show:

                break;
        }
    }


    class NetworkConnectChangedReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == WifiManager.NETWORK_STATE_CHANGED_ACTION) {
                NetworkInfo networkInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                if (networkInfo != null) {
                    boolean isWifiConnected = networkInfo.isConnected();
                    if (isWifiConnected) {
                        Log.i("MainActivity", "WIFI is connected");
                        //do something
                        WifiUtils wifiUtils = new WifiUtils(PeiWangActivity.this);
                        etId.setText(wifiUtils.getWifiName());
                        ssid = wifiUtils.getWifiName();
                    } else {
                        Log.i("MainActivity", "WIFI is disconnected");
                        //do another thing
                    }
                }
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    public void TuyaPeiWang(String peiWangtoken, String token) {
        TuyaConfig.getEZInstance().startConfig(ssid, etPassward.getText().toString().trim(), peiWangtoken);
        long millisInFuture = 60 * 1000;
        long countDownInterval = 2000;
        timer = new CountDownTimer(millisInFuture, countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                //millisUntilFinished  剩余时间回调，这个是实时的（以countDownInterval为单位）
                RetrofitManager.getInstance().getRetrofit()
                        //动态代理创建GithubAPI对象
                        .create(ApiService.class)
                        .getDevicesByToken(SharePreferencesUtils.getString(getApplicationContext(), "accesstoken", ""), token)
                        //指定上游发送事件线程
                        .subscribeOn(Schedulers.computation())
                        //指定下游接收事件线程
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<TuYaDeviceBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(TuYaDeviceBean s) {
                                if (!s.getData().getSuccessDevices().isEmpty()) {
                                    timer.cancel();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }

            @Override
            public void onFinish() {
                //结束时的回调
            }
        };
        timer.start();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
