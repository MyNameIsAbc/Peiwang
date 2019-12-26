package com.example.peiwang;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.base.BaseActivity;
import com.example.bean.DeviceBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeviceSettingActivity extends BaseActivity {
    @BindView(R.id.ll_return)
    LinearLayout llReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_do)
    TextView tvDo;
    @BindView(R.id.iv_fromlng)
    ImageView ivFromlng;
    @BindView(R.id.tv_fromlng)
    TextView tvFromlng;
    @BindView(R.id.iv_switch)
    ImageView ivSwitch;
    @BindView(R.id.iv_tolng)
    ImageView ivTolng;
    @BindView(R.id.tv_tolng)
    TextView tvTolng;
    @BindView(R.id.lin_top)
    LinearLayout linTop;

    DeviceBean deviceBean;
    @Override
    protected int getContentViewId() {
        return R.layout.activity_device;
    }

    @Override
    protected void getUnbinder() {

    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        tvDo.setVisibility(View.VISIBLE);
        tvTitle.setText("设置设备");
        deviceBean= (DeviceBean) getIntent().getSerializableExtra("bean");
    }

    @Override
    protected void disarmState() {

    }


    @OnClick({R.id.ll_return, R.id.iv_fromlng, R.id.tv_fromlng, R.id.iv_switch, R.id.iv_tolng, R.id.tv_tolng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_return:
                finish();
                break;
            case R.id.iv_fromlng:
                break;
            case R.id.tv_fromlng:
                break;
            case R.id.iv_switch:
                break;
            case R.id.iv_tolng:
                break;
            case R.id.tv_tolng:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
