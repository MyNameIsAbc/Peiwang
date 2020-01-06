package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.base.App;
import com.example.base.BaseFragment;
import com.example.peiwang.AboutActivity;
import com.example.peiwang.AddDeviceActivity;
import com.example.peiwang.PeiWangActivity;
import com.example.peiwang.R;
import com.example.utils.ChooseDeviceDialog;
import com.example.utils.SystemUtils;
import com.orhanobut.logger.Logger;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AboutFragment extends BaseFragment {

    @BindView(R.id.fragment_ll_add)
    LinearLayout fragmentLlAdd;
    @BindView(R.id.fragment_ll_wifi)
    LinearLayout fragmentLlWifi;
    @BindView(R.id.fragment_ll_heart)
    LinearLayout fragmentLlHeart;
    @BindView(R.id.fragment_tv_appname)
    TextView fragmentTvAppname;
    @BindView(R.id.fragment_tv_appversion)
    TextView fragmentTvAppversion;
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
        fragmentTvAppname.setText(App.Appname);
        fragmentTvAppversion.setText(App.AppCode);
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
                Logger.d("fragment_ll_add:");
                ChooseDeviceDialog chooseDeviceDialog=new ChooseDeviceDialog(getActivity());
                chooseDeviceDialog.setData(1);
                chooseDeviceDialog.setListener(new ChooseDeviceDialog.OnSelectClickListener() {
                    @Override
                    public void onSelectClick(int selectType) {
                        if (selectType==1){
                            gotoActivity(AddDeviceActivity.class);
                        }
                    }
                });
                chooseDeviceDialog.show();
                break;
            case R.id.fragment_ll_wifi:
                gotoActivity(PeiWangActivity.class);
                break;
            case R.id.fragment_ll_heart:
                gotoActivity(AboutActivity.class);
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
