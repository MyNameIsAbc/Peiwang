package com.example.peiwang;


import android.Manifest;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.base.App;
import com.example.base.BaseActivity;
import com.example.base.Constant;
import com.example.bean.DeviceBean;
import com.example.bean.LanguageBean;
import com.example.bean.MessageEvent;
import com.example.bean.MessageWaper;
import com.example.db.Device;
import com.example.db.DeviceDaoUtils;
import com.example.fragment.AboutFragment;
import com.example.fragment.HomeFragment;
import com.example.receiver.BluetoothReceiver;
import com.example.receiver.MusicIntentReceiver;
import com.example.utils.AudioFocusHelper;
import com.example.utils.BluetoothUtils;
import com.example.utils.JsonUtils;
import com.google.gson.reflect.TypeToken;
import com.jachatcloud.nativemethod.JaChatCloud;
import com.jachatcloud.nativemethod.JaChatCloudListener;
import com.jachatcloud.nativemethod.NativeJNI;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity  implements JaChatCloudListener {

    @BindView(R.id.packpage_vPager)
    ViewPager packpageVPager;
    @BindView(R.id.main_navi_msg_records)
    RadioButton mainNaviMsgRecords;
    @BindView(R.id.main_navi_contact)
    RadioButton mainNaviContact;
    @BindView(R.id.main_navi_radiogroup)
    RadioGroup mainNaviRadiogroup;
    private List<Fragment> fragments = new ArrayList<>();

    BluetoothReceiver mBluetoothReceiver;
    AudioManager mAudioManager;
    ComponentName mComponent;
    public static JaChatCloud mJaChatCloudClass;
    public static boolean isBluetooth = true;

    //译家平台
    public static final String APP_KEY = "fWDz5tmEwDHp8pgGT=";
    public static final String APP_ID = "1561707525030020";
    String bluetoothMac = null;

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

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        EventBus.getDefault().register(this);
        registerReceiver();
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


        if (mAudioManager.isBluetoothScoOn()) {
            mAudioManager.stopBluetoothSco();
        }
        mAudioManager.setMode(AudioManager.MODE_NORMAL);
        mAudioManager.setSpeakerphoneOn(true);
        mAudioManager.setBluetoothScoOn(false);
        // Request audio focus for playback
        Logger.d("mAudioManager.isMusicActive():" + mAudioManager.isMusicActive());

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
        unregisterReceiver(mBluetoothReceiver);
        EventBus.getDefault().unregister(this);
        //注销方法
        mAudioManager.unregisterMediaButtonEventReceiver(mComponent);
        AudioFocusHelper.getInstance(this).stopFocus();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        switch (messageEvent.getType()) {
            case Constant.EVENT_BLUETOOTH_CONNECTED:
                bluetoothMac = (String) messageEvent.getObject();
                if (BluetoothUtils.devices.size() <= 0) {
                    isBluetooth = false;
                } else {
                    isBluetooth = true;
                }
                Logger.d("bluetooth connected:" + bluetoothMac);
                initJachat(bluetoothMac);
                //

                break;
            case Constant.EVENT_BLUETOOTH_CONNECTED_DEVICE:
                bluetoothMac = (String) messageEvent.getObject();
                if (BluetoothUtils.devices.size() <= 0) {
                    isBluetooth = false;
                } else {
                    isBluetooth = true;
                }
                Logger.d("bluetooth connected device:" + bluetoothMac);
                initJachat(bluetoothMac);
                break;
            case Constant.EVENT_BLUETOOTH_ABOUTTOHOME:
                switchFragment(0);
                break;

            case Constant.EVENT_BLUETOOTH_SETTING:
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intent, Constant.BLUETOOTH_RESPONSE);
                startActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));
                break;
            case Constant.EVENT_BLUETOOTH_SETTING2:
                startActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));
                break;


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constant.BLUETOOTH_RESPONSE:
                if (resultCode == RESULT_OK) {
                    //获得蓝牙权限成功
                    //需要再写一次，oncreate里的下面的方法无法执行
                    BluetoothUtils.findBluetoothDevice();
                } else if (resultCode == RESULT_CANCELED) {
                    //获得蓝牙权限失败
                    Logger.e("蓝牙权限获取失败，请打开蓝牙！");
                    Toast.makeText(MainActivity.this, "蓝牙权限获取失败，请打开蓝牙！", Toast.LENGTH_SHORT).show();
                }
        }
    }


    /**
     * 注册广播
     **/
    private void registerReceiver() {
        mBluetoothReceiver = new BluetoothReceiver();
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED);
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        filter.addAction(AudioManager.ACTION_SCO_AUDIO_STATE_UPDATED);
        registerReceiver(mBluetoothReceiver, filter);
        //构造一个ComponentName，指向MediaoButtonReceiver类
        mComponent = new ComponentName(getPackageName(), MusicIntentReceiver.class.getName());
        //注册一个MediaButtonReceiver广播监听
        mAudioManager.registerMediaButtonEventReceiver(mComponent);
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


    private void initJachat(String bluetoothMac) {
        Logger.d("initJachat:" + bluetoothMac);
        String tempMac = "00:00:00:00:01:AF";
        mJaChatCloudClass = new JaChatCloud(MainActivity.this, bluetoothMac, APP_KEY, APP_ID, MainActivity.this);
        mJaChatCloudClass.setVersion(NativeJNI.REAL_TIME);

    }

    private void setSize(RadioButton rb) {
        Rect rect = new Rect();
        rect.set(0, 0, 60, 60);    //距离父窗体的距离，可以理解为左上和右下的坐标
        Drawable[] drawables = rb.getCompoundDrawables();
        drawables[1].setBounds(rect);  //取出上边的图片设置大小
        rb.setCompoundDrawables(null, drawables[1], null, null);  //把这张图片放在上边，这四个参表示图片放在左、上、有、下
    }

    @Override
    public void onState(int i, String s) {
        Logger.d("onState" + "i" + i + "   " + s);
    }

    @Override
    public void receiveMessage(int i, String s) {
        Logger.d("receiveMessage" + "i" + i + "   " + s);
        if (i == 101) {
            if (mJaChatCloudClass != null) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        SavingBlueTooth(MainActivity.this);

                        showToast("初始化成功");
                    }
                });
                EventBus.getDefault().post(new MessageEvent(Constant.EVENT_INIT_SUCCESS, null));
                ArrayList<LanguageBean> languageBeans = JsonUtils.fromJson(s, new TypeToken<ArrayList<LanguageBean>>() {});
                App.languageBeans.clear();
                App.languageBeans.addAll(languageBeans);
            }
        }
    }

    @Override
    public void onUpdate(String s) {

    }

    @Override
    public void onError(String s, String s1) {
        Logger.d("onError" + s + "   " + s);
    }


    /**
     *
     */
    public static void SavingBlueTooth(Context mContext){
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        Class<BluetoothAdapter> bluetoothAdapterClass = BluetoothAdapter.class;//得到BluetoothAdapter的Class对象
        if (!adapter.isEnabled()) {
            //通过这个方法来请求打开我们的蓝牙设备
            EventBus.getDefault().post(new MessageEvent(Constant.EVENT_BLUETOOTH_SETTING,""));
        } else {
            Logger.e("蓝牙已打开");
            try {//得到连接状态的方法
                Method method = bluetoothAdapterClass.getDeclaredMethod("getConnectionState", (Class[]) null);
                //打开权限
                method.setAccessible(true);
                int state = (int) method.invoke(adapter, (Object[]) null);
                if(state == BluetoothAdapter.STATE_CONNECTED){
                    Logger.i("BLUETOOTH","BluetoothAdapter.STATE_CONNECTED");
                    Set<BluetoothDevice> devices = adapter.getBondedDevices();
                    Logger.i("BLUETOOTH","devices:"+devices.size());
                    for(BluetoothDevice device : devices){
                        Method isConnectedMethod = BluetoothDevice.class.getDeclaredMethod("isConnected", (Class[]) null);
                        method.setAccessible(true);
                        boolean isConnected = (boolean) isConnectedMethod.invoke(device, (Object[]) null);
                        if(isConnected){
                            Logger.i("BLUETOOTH","connected:"+device.getName());
                            String mBlueAdress = device.getAddress();
                            DeviceDaoUtils deviceDaoUtils = new DeviceDaoUtils(mContext);
                            List<Device> Buledevices = deviceDaoUtils.queryDeviceByQueryBuilder(mBlueAdress);
                            if (Buledevices.isEmpty()) {
                                Device Buledevice = new Device();
                                Buledevice.setSn(mBlueAdress);
                                Buledevice.setSource("type_2");
                                deviceDaoUtils.insertDevice(Buledevice);
                                DeviceBean deviceBean = new DeviceBean(mBlueAdress, -1, -1, -1,
                                        -1, "", "");

                                EventBus.getDefault().post(new MessageEvent(Constant.EVENT_BLUETOOTH_ABOUTTOHOME, deviceBean));
                            }
                        }
                    }
                }else{
                    Logger.e("蓝牙已打开，但未连接");
                    EventBus.getDefault().post(new MessageEvent(Constant.EVENT_BLUETOOTH_SETTING2,""));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
