package com.example.receiver;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.text.TextUtils;


import com.example.base.Constant;
import com.example.bean.MessageEvent;
import com.example.utils.BluetoothUtils;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;


public class BluetoothReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Logger.i("=========蓝牙接收处理广播========" + intent.getAction());
        BluetoothDevice device;
        switch (intent.getAction()) {
            case BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED:
                switch (intent.getIntExtra(BluetoothA2dp.EXTRA_STATE, -1)) {
                    case BluetoothA2dp.STATE_CONNECTING:
                        device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                        Logger.i("device: " + device.getName() + " connecting");
                        break;
                    case BluetoothA2dp.STATE_CONNECTED:
                        device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                        Logger.i("device: " + device.getName() + " " + device.getAddress() + " connected");
                        int deviceClassType = device.getBluetoothClass().getDeviceClass();
                        if (deviceClassType == BluetoothClass.Device.AUDIO_VIDEO_WEARABLE_HEADSET
                                || deviceClassType == BluetoothClass.Device.AUDIO_VIDEO_HEADPHONES) {
                            BluetoothUtils.devices.clear();
                            BluetoothUtils.devices.add(device);
                            EventBus.getDefault().post(new MessageEvent(Constant.EVENT_BLUETOOTH_CONNECTED, device.getAddress()));
                        }
                        break;
                    case BluetoothA2dp.STATE_DISCONNECTING:
                        device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                        Logger.i("device: " + device.getName() + " disconnecting");
                        break;
                    case BluetoothA2dp.STATE_DISCONNECTED:
                        device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                        BluetoothUtils.devices.remove(device);
                        Logger.i("device: " + device.getName() + " disconnected");
                        break;
                    default:
                        break;
                }
                break;
            case BluetoothA2dp.ACTION_PLAYING_STATE_CHANGED:
                int state = intent.getIntExtra(BluetoothA2dp.EXTRA_STATE, -1);
                switch (state) {
                    case BluetoothA2dp.STATE_PLAYING:
                        Logger.i("state: playing.");
                        break;
                    case BluetoothA2dp.STATE_NOT_PLAYING:
                        Logger.i("state: not playing");
                        break;
                    default:
                        Logger.i("state: unkown");
                        break;
                }
                break;
            case BluetoothDevice.ACTION_FOUND:
                device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                int deviceClassType = device.getBluetoothClass().getDeviceClass();
                //找到指定的蓝牙设备
                if (deviceClassType == BluetoothClass.Device.AUDIO_VIDEO_WEARABLE_HEADSET
                        || deviceClassType == BluetoothClass.Device.AUDIO_VIDEO_HEADPHONES) {
                    Logger.i("Found device:" + device.getName() + "   address:" + device.getAddress() + "type:" + deviceClassType);
                    if (!TextUtils.isEmpty(device.getName())) {
                        //添加到设备列表
                    }
                } else {//找到可用蓝牙
                    if (!TextUtils.isEmpty(device.getName())) {
                        Logger.i("=====Found device====11===" + device.getName() + "   address:" + device.getAddress());
                        //添加到设备列表
                    }
                }
                break;
            case BluetoothDevice.ACTION_BOND_STATE_CHANGED:
                int bondState = intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, BluetoothDevice.BOND_NONE);
                device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                switch (bondState) {
                    case BluetoothDevice.BOND_BONDED:  //配对成功
                        Logger.i("Device:" + device.getName() + " bonded.");
                        //取消搜索，连接蓝牙设备
                        break;
                    case BluetoothDevice.BOND_BONDING:
                        Logger.i("Device:" + device.getName() + " bonding.");
                        break;
                    case BluetoothDevice.BOND_NONE:
                        Logger.i("Device:" + device.getName() + " not bonded.");
                        //不知道是蓝牙耳机的关系还是什么原因，经常配对不成功
                        //配对不成功的话，重新尝试配对
                        break;
                    default:
                        break;

                }
                break;
            case BluetoothAdapter.ACTION_STATE_CHANGED:
                state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1);
                switch (state) {
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Logger.i("BluetoothAdapter is turning on.");
                        break;
                    case BluetoothAdapter.STATE_ON:
                        Logger.i("BluetoothAdapter is on.");
//                        //蓝牙已打开，开始搜索并连接service
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Logger.i("BluetoothAdapter is turning off.");
                        BluetoothUtils.devices.clear();
                        break;
                    case BluetoothAdapter.STATE_OFF:
                        Logger.i("BluetoothAdapter is off.");
                        BluetoothUtils.devices.clear();
                        break;
                }
                break;
            case AudioManager.ACTION_SCO_AUDIO_STATE_UPDATED:
                state = intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", 0);
                switch (state) {
                    case -1:
                        //Bluetooth SCO device error
                        Logger.d("Bluetooth SCO device error");
                        break;
                    case 0:
                        //Bluetooth SCO device disconnected
                        Logger.d("Bluetooth SCO device disconnected");
                        EventBus.getDefault().post(new MessageEvent(Constant.EVENT_BLUETOOTH_SCO_DISCONNECTED,null));
                        break;
                    case 1:
                        //Bluetooth SCO device connected
                        EventBus.getDefault().post(new MessageEvent(Constant.EVENT_BLUETOOTH_SCO_CONNECTED,null));
                        Logger.d("Bluetooth SCO device connected");
                        break;
                    case 2:
                        //Bluetooth SCO device connecting
                        Logger.d("Bluetooth SCO device connecting");
                        break;
                    default:
                        Logger.d("Bluetooth SCO device unknown event");
                        //Bluetooth SCO device unknown event;
                }
            default:
                break;
        }
    }
}
