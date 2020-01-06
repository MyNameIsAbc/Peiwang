package com.example.peiwang;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.BaseActivity;
import com.example.bean.ShengboBean;
import com.example.net.ApiService;
import com.example.net.RetrofitManager;
import com.example.utils.MediaPlayerUtils;
import com.example.utils.WifiUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @Override
    protected int getContentViewId() {
        return R.layout.activity_peiwang;
    }

    @Override
    protected void getUnbinder() {
        List<ScanResult> wifiLists = WifiUtils.scanWifiInfo(this);
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        list.clear();
         etId.setText(new WifiUtils(this).getWifiName());
        tvTitle.setText("声波配网");
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
    }

    @OnClick({R.id.ll_return, R.id.tv_do, R.id.btn_start,R.id.peiwang_tv_change, R.id.peiwang_iv_show})
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
                    if(isWifiConnected){
                        Log.i("MainActivity","WIFI is connected");
                        //do something
                        etId.setText(new WifiUtils(PeiWangActivity.this).getWifiName());
                    } else {
                        Log.i("MainActivity","WIFI is disconnected");
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

}
