package com.example.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;

import com.orhanobut.logger.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
     *
     * @return
     */
    public String getWifiName() {
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
        checkLocation(context);
        WifiManager mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> mWifiList = new CopyOnWriteArrayList<>();
        mWifiList.clear();
        if (!mWifiManager.isWifiEnabled()) {
            mWifiManager.setWifiEnabled(true);
        }

        mWifiList.addAll(mWifiManager.getScanResults()) ;
        for (ScanResult scanResult:mWifiList){
            if (scanResult.frequency > 4900 && scanResult.frequency < 5900){
                mWifiList.remove(scanResult);
            }
        }
        Logger.d("mWifiList size  :" + mWifiList.size());
        return mWifiList;
    }

    /**
     * 手机是否开启位置服务，如果没有开启那么所有app将不能使用定位功能
     */
    public static boolean isLocServiceEnable(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (gps || network) {
                return true;
            }
            return false;
        } else
            return true;
    }

    public static void checkLocation(final Context context) {
        if (!isLocServiceEnable(context))
            new AlertDialog.Builder(context).setTitle("手机未开启位置服务 ")
                    .setMessage("配网需要打开位置服务 请在 设置-位置信息 (将位置服务打开))")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            try {
                                context.startActivity(intent);
                            } catch (ActivityNotFoundException ex) {
                                intent.setAction(Settings.ACTION_SETTINGS);
                                try {
                                    context.startActivity(intent);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            dialog.dismiss();
                        }
                    }).show();
    }
}
