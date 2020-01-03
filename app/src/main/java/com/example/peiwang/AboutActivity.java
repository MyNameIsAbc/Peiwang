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
import butterknife.OnClick;

public class AboutActivity extends BaseActivity {


    @BindView(R.id.about_iv_back)
    ImageView aboutIvBack;
    @BindView(R.id.about_iv_appicon)
    ImageView aboutIvAppicon;
    @BindView(R.id.about_tv_appname)
    TextView aboutTvAppname;
    @BindView(R.id.about_tv_appversion)
    TextView aboutTvAppversion;
    @BindView(R.id.about_rl_history)
    RelativeLayout aboutRlHistory;
    @BindView(R.id.about_rl_update)
    RelativeLayout aboutRlUpdate;
    @BindView(R.id.about_tv_protocol_1)
    TextView aboutTvProtocol1;
    @BindView(R.id.about_tv_protocol_2)
    TextView aboutTvProtocol2;

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

        aboutIvAppicon.setImageBitmap(AppUtils.getBitmap(this));
        aboutTvAppname.setText(AppUtils.getAppName(this));
        aboutTvAppversion.setText(AppUtils.getVersionName(this));


    }

    @Override
    protected void disarmState() {

    }




    @OnClick({R.id.about_iv_back, R.id.about_rl_history, R.id.about_rl_update, R.id.about_tv_protocol_1, R.id.about_tv_protocol_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
