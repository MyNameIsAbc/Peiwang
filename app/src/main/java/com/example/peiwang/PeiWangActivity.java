package com.example.peiwang;

import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.et_passward)
    EditText etPassward;
    @BindView(R.id.btn_start)
    Button btnStart;
    private List<String> list = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    String ssid;

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
        final List<ScanResult> wifis = WifiUtils.scanWifiInfo(this);
        for (ScanResult s : wifis) {
            list.add(s.SSID);
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ssid = adapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        tvTitle.setText("配网");
    }

    @Override
    protected void disarmState() {
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

    @OnClick({R.id.ll_return, R.id.tv_do,R.id.btn_start})
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
        }
    }
}
