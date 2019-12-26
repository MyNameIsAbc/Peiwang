package com.example.utils;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.orhanobut.logger.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WifiUtils implements Serializable {

    /**
     * 声明管理对象
     */
    private WifiInfo wifiInfo;
    private WifiManager wifiManager;


    public WifiUtils(Context context) {
        // 获取Wifi服务
        this.wifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        // 得到Wifi信息
        this.wifiInfo = wifiManager.getConnectionInfo();
    }

    /**
     * 获取本机Mac地址
     *
     * @return
     */
    public String getMac() {
        return (wifiInfo == null) ? "" : wifiInfo.getMacAddress();
    }


    /**
     * 获取当前连接WIFI的名称
     * @return
     */
    public String getWifiName(){
        return (wifiInfo == null) ? null : wifiInfo.getSSID();
    }

    /**
     * 扫描之前需要刷新附近的Wifi列表，这需要使用startScan方法
     */
    public static void scanStart(Context context) {
        WifiManager mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        mWifiManager.startScan();
    }

    /**
     * 扫描附近wifi
     */
    public static List<ScanResult> scanWifiInfo(Context context) {
        WifiManager mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> mWifiList = new ArrayList<>();
        mWifiList.clear();
        if (!mWifiManager.isWifiEnabled()) {
            mWifiManager.setWifiEnabled(true);
        }
        mWifiList = mWifiManager.getScanResults();
        Logger.d( "mWifiList size  :" + mWifiList.size());
        return mWifiList;
    }
}
