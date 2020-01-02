package com.example.peiwang;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.base.BaseActivity;
import com.example.utils.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.about_iv_back)
     ImageView mIvBack;
    @BindView(R.id.about_iv_appicon)
     ImageView mAppIcon;
    @BindView(R.id.about_tv_appname)
     TextView mAppName;
    @BindView(R.id.about_tv_appversion)
     TextView mAppVersion;
    @BindView(R.id.about_rl_history)
     RelativeLayout mHistory;
    @BindView(R.id.about_rl_update)
     RelativeLayout mUpdate;
    @BindView(R.id.about_tv_protocol_1)
     RelativeLayout mProtocol1;
    @BindView(R.id.about_tv_protocol_2)
     RelativeLayout mProtocol2;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_about;
    }

    @Override
    protected void getUnbinder() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {

        mAppIcon.setImageBitmap(AppUtils.getBitmap(this));
        mAppName.setText(AppUtils.getAppName(this));
        mAppVersion.setText(AppUtils.getVersionName(this));

        mIvBack.setOnClickListener(this);
        mHistory.setOnClickListener(this);
        mUpdate.setOnClickListener(this);
        mProtocol1.setOnClickListener(this);
        mProtocol2.setOnClickListener(this);

    }

    @Override
    protected void disarmState() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.about_iv_back:
                finish();
                break;
            case R.id.about_rl_history:
                break;
            case R.id.about_rl_update:
                break;
            case R.id.about_tv_protocol_1:
                break;
            case R.id.about_tv_protocol_2:
                break;
        }
    }
}
