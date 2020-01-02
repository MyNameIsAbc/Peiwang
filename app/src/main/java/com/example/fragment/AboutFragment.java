package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.base.BaseFragment;
import com.example.peiwang.AboutActivity;
import com.example.peiwang.AddDeviceActivity;
import com.example.peiwang.PeiWangActivity;
import com.example.peiwang.R;
import com.example.utils.SystemUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AboutFragment extends BaseFragment {
    @BindView(R.id.fragment_tv_appname)
    TextView fragmentTvAppname;
    @BindView(R.id.fragment_tv_appversion)
    TextView fragmentTvAppversion;
    @BindView(R.id.fragment_ll_add)
    LinearLayout fragmentLlAdd;
    @BindView(R.id.fragment_ll_wifi)
    LinearLayout fragmentLlWifi;
    @BindView(R.id.fragment_ll_heart)
    LinearLayout fragmentLlHeart;


    @Override
    public int getContentViewId() {
        return R.layout.fragment_about;
    }

    @Override
    public void getUnbinder() {
        unbinder = ButterKnife.bind(this, rootView);
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
//        fragmentTvAppname.setText(SystemUtils.getVersionName(getContext()));
//        fragmentTvAppversion.setText(SystemUtils.getVersionCode(getContext()));
    }

    @Override
    protected void disarmState() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    @OnClick({R.id.fragment_ll_add, R.id.fragment_ll_wifi, R.id.fragment_ll_heart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_ll_add:
                gotoActivity(AddDeviceActivity.class);
                break;
            case R.id.fragment_ll_wifi:
                gotoActivity(PeiWangActivity.class);
                break;
            case R.id.fragment_ll_heart:
                gotoActivity(AboutActivity.class);
                break;
        }
    }
}
