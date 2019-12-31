package com.example.peiwang;


import android.Manifest;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.base.BaseActivity;
import com.example.fragment.AboutFragment;
import com.example.fragment.HomeFragment;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.packpage_vPager)
    ViewPager packpageVPager;
    @BindView(R.id.main_navi_msg_records)
    RadioButton mainNaviMsgRecords;
    @BindView(R.id.main_navi_contact)
    RadioButton mainNaviContact;
    @BindView(R.id.main_navi_radiogroup)
    RadioGroup mainNaviRadiogroup;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void getUnbinder() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        fragments.clear();
        fragments.add(new HomeFragment());
        fragments.add(new AboutFragment());

        packpageVPager.setAdapter(new FragmentPagerAdapter(this.getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return fragments.get(arg0);
            }
        });

        packpageVPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switchFragment(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        switchFragment(0);

        mainNaviRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_navi_msg_records:
                        switchFragment(0);
                        break;
                    case R.id.main_navi_contact:
                        switchFragment(1);
                        break;
                }
            }
        });

        //实例化Location应用管理类
        Acp.getInstance(this).request(new AcpOptions.Builder()
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE
                                , Manifest.permission.READ_PHONE_STATE, Manifest.permission.CAMERA
                        , Manifest.permission.ACCESS_COARSE_LOCATION)
                        .setDeniedMessage("需要权限启动")
                        /*以下为自定义提示语、按钮文字
                        .setDeniedMessage()
                        .setDeniedCloseBtn()
                        .setDeniedSettingBtn()
                        .setRationalMessage()
                        .setRationalBtn()*/
                        .build(),
                new AcpListener() {
                    @Override
                    public void onGranted(){
                    }

                    @Override
                    public void onDenied(List<String> permissions) {
                        finish();
                    }
                });
    }

    public void switchFragment(int index) {
        switch (index) {
            case 0:
                packpageVPager.setCurrentItem(0, false);
                Drawable drawableFirst = getResources().getDrawable(R.mipmap.cgt_tabbar_weixin_sel);
                mainNaviMsgRecords.setCompoundDrawablesRelativeWithIntrinsicBounds(null, drawableFirst, null, null);//只放上面

                Drawable drawableSecond = getResources().getDrawable(R.mipmap.cgt_tabbar_me_nor);
                mainNaviContact.setCompoundDrawablesRelativeWithIntrinsicBounds(null, drawableSecond, null, null);//只放上面
                break;
            case 1:
                //点击第一个radiobutton,显示viewpager的第一页
                packpageVPager.setCurrentItem(1, false);

                Drawable drawableFirst2 = getResources().getDrawable(R.mipmap.cgt_tabbar_weixin_nor);
                mainNaviMsgRecords.setCompoundDrawablesRelativeWithIntrinsicBounds(null, drawableFirst2, null, null);//只放上面

                Drawable drawableSecond2 = getResources().getDrawable(R.mipmap.cgt_tabbar_me_sel);
                mainNaviContact.setCompoundDrawablesRelativeWithIntrinsicBounds(null, drawableSecond2, null, null);//只放上面
                break;
        }
    }

    @Override
    protected void disarmState() {

    }

    @OnClick({R.id.main_navi_msg_records, R.id.main_navi_contact})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_navi_msg_records:
                mainNaviMsgRecords.setChecked(true);
                mainNaviContact.setChecked(false);
                break;
            case R.id.main_navi_contact:
                mainNaviMsgRecords.setChecked(false);
                mainNaviContact.setChecked(true);
                break;
        }
    }

}
