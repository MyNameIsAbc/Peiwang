package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.base.BaseFragment;
import com.example.peiwang.AddDeviceActivity;
import com.example.peiwang.PeiWangActivity;
import com.example.peiwang.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AboutFragment extends BaseFragment {
    @BindView(R.id.btn_adddevice)
    Button btnAdddevice;
    @BindView(R.id.btn_peiwang)
    Button btnPeiwang;
    Unbinder unbinder;

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

    }

    @Override
    protected void disarmState() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_adddevice, R.id.btn_peiwang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_adddevice:
                gotoActivity(AddDeviceActivity.class);
                break;
            case R.id.btn_peiwang:
                gotoActivity(PeiWangActivity.class);
                break;
        }
    }
}
