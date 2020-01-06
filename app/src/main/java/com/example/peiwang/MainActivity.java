package com.example.peiwang;


import android.Manifest;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.base.BaseActivity;
import com.example.base.Constant;
import com.example.bean.MessageWaper;
import com.example.fragment.AboutFragment;
import com.example.fragment.HomeFragment;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
        EventBus.getDefault().register(this);
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
                    public void onGranted() {
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
                Drawable drawableFirst = getResources().getDrawable(R.mipmap.cgt_tabbar_weixin_sel_1);
                mainNaviMsgRecords.setCompoundDrawablesRelativeWithIntrinsicBounds(null, drawableFirst, null, null);//只放上面

                Drawable drawableSecond = getResources().getDrawable(R.mipmap.cgt_tabbar_me_nor_1);
                mainNaviContact.setCompoundDrawablesRelativeWithIntrinsicBounds(null, drawableSecond, null, null);//只放上面

                mainNaviMsgRecords.setChecked(true);
                mainNaviContact.setChecked(false);
                break;
            case 1:
                //点击第一个radiobutton,显示viewpager的第一页
                packpageVPager.setCurrentItem(1, false);

                Drawable drawableFirst2 = getResources().getDrawable(R.mipmap.cgt_tabbar_weixin_nor_1);
                mainNaviMsgRecords.setCompoundDrawablesRelativeWithIntrinsicBounds(null, drawableFirst2, null, null);//只放上面

                Drawable drawableSecond2 = getResources().getDrawable(R.mipmap.cgt_tabbar_me_sel_1);
                mainNaviContact.setCompoundDrawablesRelativeWithIntrinsicBounds(null, drawableSecond2, null, null);//只放上面

                mainNaviMsgRecords.setChecked(false);
                mainNaviContact.setChecked(true);
                break;
        }
    }

    @Override
    protected void disarmState() {
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.main_navi_msg_records, R.id.main_navi_contact})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_navi_msg_records:
                switchFragment(0);
                break;
            case R.id.main_navi_contact:
                switchFragment(1);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageWaper event) {
        switch (event.type) {
            case Constant.TEXT_TYPE_ADD_DEVICE:
                packpageVPager.setCurrentItem(0);
                break;
        }
    }

    private void setSize(RadioButton rb) {
        Rect rect = new Rect();
        rect.set(0, 0, 60, 60);    //距离父窗体的距离，可以理解为左上和右下的坐标
        Drawable[] drawables = rb.getCompoundDrawables();
        drawables[1].setBounds(rect);  //取出上边的图片设置大小
        rb.setCompoundDrawables(null, drawables[1], null, null);  //把这张图片放在上边，这四个参表示图片放在左、上、有、下
    }
}
