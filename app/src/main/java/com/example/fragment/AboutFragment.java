package com.example.fragment;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.App;
import com.example.base.BaseFragment;
import com.example.base.Constant;
import com.example.bean.DeviceBean;
import com.example.bean.MessageEvent;
import com.example.bean.MessageWaper;
import com.example.db.Device;
import com.example.db.DeviceDaoUtils;
import com.example.peiwang.AboutActivity;
import com.example.peiwang.AddDeviceActivity;
import com.example.peiwang.LoginActivity;
import com.example.peiwang.MainActivity;
import com.example.peiwang.PeiWangActivity;
import com.example.peiwang.R;
import com.example.utils.SharePreferencesUtils;
import com.kongzue.dialog.interfaces.OnDialogButtonClickListener;
import com.kongzue.dialog.interfaces.OnMenuItemClickListener;
import com.kongzue.dialog.util.BaseDialog;
import com.kongzue.dialog.v3.BottomMenu;
import com.kongzue.dialog.v3.MessageDialog;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

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
    @BindView(R.id.tv_login)
    TextView tvLogin;

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
        EventBus.getDefault().register(this);
        fragmentTvAppname.setText(App.Appname);
        fragmentTvAppversion.setText(App.AppCode);
    }

    @Override
    protected void disarmState() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.fragment_ll_add, R.id.fragment_ll_wifi, R.id.fragment_ll_heart, R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_ll_add:
                BottomMenu.show((AppCompatActivity) getContext(),
                        new String[]{getResources().getString(R.string.fragment_about_add_one),
                                getResources().getString(R.string.fragment_about_add_two)}, new OnMenuItemClickListener() {
                            @Override
                            public void onClick(String text, int index) {
                                switch (index) {
                                    case 0:
                                        gotoActivity(AddDeviceActivity.class);
                                        break;
                                    case 1:
//                                        Intent intent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
//                                        startActivity(intent);
                                        MainActivity.SavingBlueTooth(getContext());

                                        break;
                                }
                            }
                        }).setTitle(getResources().getString(R.string.fragment_about_add_title));

                break;
            case R.id.fragment_ll_wifi:

                BottomMenu.show((AppCompatActivity) getContext(),
                        new String[]{getResources().getString(R.string.fragment_about_peiwang_one),
                                getResources().getString(R.string.fragment_about_peiwang_two)}, new OnMenuItemClickListener() {
                            @Override
                            public void onClick(String text, int index) {
                                switch (index) {
                                    case 0:
                                        Intent intent = new Intent(getActivity(), PeiWangActivity.class);
                                        intent.putExtra("type", 1);
                                        getActivity().startActivity(intent);
                                        break;
                                    case 1:
                                        String token = SharePreferencesUtils.getString(getContext(), "accesstoken", "");
                                        if (!TextUtils.isEmpty(token)) {
                                            Intent intent2 = new Intent(getActivity(), PeiWangActivity.class);
                                            intent2.putExtra("type", 2);
                                            getActivity().startActivity(intent2);
                                        } else {
                                            MessageDialog.build((AppCompatActivity) getContext())
                                                    .setTitle(getResources().getString(R.string.fragment_about_peiwang2_title))
                                                    .setMessage(getResources().getString(R.string.fragment_about_peiwang2_message))
                                                    .setOkButton(getResources().getString(R.string.fragment_about_peiwang2_confirm), new OnDialogButtonClickListener() {
                                                        @Override
                                                        public boolean onClick(BaseDialog baseDialog, View v) {
                                                                gotoActivity(LoginActivity.class);
                                                            return false;
                                                        }
                                                    })
                                                    .setCancelButton(getResources().getString(R.string.fragment_about_peiwang2_cancel), new OnDialogButtonClickListener() {
                                                        @Override
                                                        public boolean onClick(BaseDialog baseDialog, View v) {
                                                            return false;
                                                        }
                                                    }).show();
                                            break;
                                        }
                                }
                            }
                        }).setTitle(getResources().getString(R.string.fragment_about_peiwang_title));
                break;
            case R.id.fragment_ll_heart:
                gotoActivity(AboutActivity.class);
                break;
            case R.id.tv_login:
                gotoActivity(LoginActivity.class);
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


    public void toast(final Object obj) {
        Toast.makeText(getContext(), obj.toString(), Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageWaper event) {
        switch (event.type) {
            case Constant.EVENT_LOGIN_SUCCESS:
                tvLogin.setVisibility(View.GONE);
                break;
        }
    }
}
