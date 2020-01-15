package com.example.utils;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;


import com.example.base.Constant;
import com.example.bean.MessageEvent;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BluetoothUtils {
    static BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    /**
     * 设备是否支持蓝牙
     **/
    public static boolean isSupportBluetooth() {
        if (mBluetoothAdapter != null) {
            return true;
        }
        return false;
    }

    /**
     * 蓝牙是否已经启动
     **/
    public static boolean isBluetoothOpen() {
        if (mBluetoothAdapter != null) {
            return mBluetoothAdapter.isEnabled();
        }
        return false;
    }

    /**
     * 请求启动蓝牙
     **/
    public static void requestStartBluetooth(int requestCode, Activity activity) {
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        activity.startActivityForResult(enableBtIntent, requestCode);
    }

    /**
     * 强制打开蓝牙
     **/
    public static void openBluetooth() {
        if (isSupportBluetooth()) {
            mBluetoothAdapter.enable();
        }
    }

    /**
     * 查询配对设备
     **/
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static List<BluetoothDevice> checkDevices() {
        List<BluetoothDevice> devices = new ArrayList<>();
        if (mBluetoothAdapter != null) {
            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
            if (pairedDevices != null && pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {
                    Logger.e("配对设备:" + device.getName() + "type:" + device.getType());
                    devices.add(device);
                }
            }
        }
        return devices;
    }

    /**
     * 发现新设备
     **/
    public static void findBluetoothDevice() {
        //其实是启动了一个异步线程，该方法将立即返回一个布尔值，指示发现是否已成功启动。
        // 发现过程通常涉及大约12秒的查询扫描，随后是每个找到的设备的页面扫描以检索其蓝牙名称
        if (mBluetoothAdapter != null && mBluetoothAdapter.isEnabled() && !mBluetoothAdapter.isDiscovering()) {
            if (mBluetoothAdapter.startDiscovery()) {
                Logger.i("=======已成功启动寻找新设备的异步线程=======");
            } else {
                Logger.i("=======启动寻找新设备的异步线程失败=======");
            }
        }
    }

    public static void disableBluetooth() {
//注销蓝牙链接
        mBluetoothAdapter.disable();
    }

    public static List<BluetoothDevice> devices = new ArrayList<>();

    public static void findConnectedDevice(Context context) {
        devices.clear();
        int a2dp = mBluetoothAdapter.getProfileConnectionState(BluetoothProfile.A2DP);
        int headset = mBluetoothAdapter.getProfileConnectionState(BluetoothProfile.HEADSET);
        int health = mBluetoothAdapter.getProfileConnectionState(BluetoothProfile.HEALTH);

        int flag = -1;
        if (a2dp == BluetoothProfile.STATE_CONNECTED) {
            flag = a2dp;
        } else if (headset == BluetoothProfile.STATE_CONNECTED) {
            flag = headset;
        } else if (health == BluetoothProfile.STATE_CONNECTED) {
            flag = health;
        }

        if (flag != -1) {
            mBluetoothAdapter.getProfileProxy(context, new BluetoothProfile.ServiceListener() {
                @Override
                public void onServiceDisconnected(int profile) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onServiceConnected(int profile, BluetoothProfile proxy) {
                    // TODO Auto-generated method stub
                    List<BluetoothDevice> mDevices = proxy.getConnectedDevices();
                    devices.addAll(mDevices);
                    if (mDevices != null && mDevices.size() > 0) {
                        for (BluetoothDevice device : mDevices) {
                            Logger.e("device name: " + device.getName() + "address:" + device.getAddress());
                            EventBus.getDefault().post(new MessageEvent(Constant.EVENT_BLUETOOTH_CONNECTED_DEVICE, device.getAddress()));
                        }
                    } else {
                        Logger.e("mDevices is null");
                    }
                }
            }, flag);
        }
    }
}
