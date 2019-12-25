package com.example.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.io.Serializable;

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


}
