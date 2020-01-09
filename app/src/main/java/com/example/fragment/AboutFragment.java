package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.base.App;
import com.example.base.BaseFragment;
import com.example.peiwang.AboutActivity;
import com.example.peiwang.AddDeviceActivity;
import com.example.peiwang.PeiWangActivity;
import com.example.peiwang.R;
import com.example.utils.ChooseDeviceDialog;
import com.example.utils.SystemUtils;
import com.kongzue.dialog.interfaces.OnDialogButtonClickListener;
import com.kongzue.dialog.interfaces.OnMenuItemClickListener;
import com.kongzue.dialog.util.BaseDialog;
import com.kongzue.dialog.v3.BottomMenu;
import com.kongzue.dialog.v3.MessageDialog;
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
                BottomMenu.show((AppCompatActivity) getContext(),
                        new String[]{getResources().getString (R.string.fragment_about_add_one),
                                getResources().getString (R.string.fragment_about_add_two)}, new OnMenuItemClickListener() {
                            @Override
                            public void onClick(String text, int index) {
                                switch (index){
                                    case 0:
                                        gotoActivity(AddDeviceActivity.class);
                                        break;
                                    case 1:
                                        Intent intent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
                                        startActivity(intent);
                                        break;
                                }
                            }
                        }).setTitle(getResources().getString (R.string.fragment_about_add_title));

                break;
            case R.id.fragment_ll_wifi:
                BottomMenu.show((AppCompatActivity) getContext(),
                        new String[]{getResources().getString (R.string.fragment_about_peiwang_one),
                                getResources().getString (R.string.fragment_about_peiwang_two)}, new OnMenuItemClickListener() {
                    @Override
                    public void onClick(String text, int index) {
                        switch (index){
                            case 0:
                                gotoActivity(PeiWangActivity.class);
                                break;
                            case 1:
                                MessageDialog.build((AppCompatActivity) getContext())
                                        .setTitle(getResources().getString (R.string.fragment_about_peiwang2_title))
                                        .setMessage(getResources().getString (R.string.fragment_about_peiwang2_message))
                                        .setOkButton(getResources().getString (R.string.fragment_about_peiwang2_confirm), new OnDialogButtonClickListener() {
                                            @Override
                                            public boolean onClick(BaseDialog baseDialog, View v) {
                                                toast("进入登陆画面");
                                                return false;
                                            }
                                        })
                                        .setCancelButton(getResources().getString (R.string.fragment_about_peiwang2_cancel),new OnDialogButtonClickListener() {
                                            @Override
                                            public boolean onClick(BaseDialog baseDialog, View v) {
                                                return false;
                                            }
                                        })
                                        .show();
                                break;
                        }
                    }
                }).setTitle(getResources().getString (R.string.fragment_about_peiwang_title));

//                gotoActivity(PeiWangActivity.class);
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


    public void toast(final Object obj) {
        Toast.makeText(getContext(), obj.toString(), Toast.LENGTH_SHORT).show();
    }
}
