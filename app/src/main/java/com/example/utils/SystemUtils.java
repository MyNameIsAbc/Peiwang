package com.example.utils;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.PowerManager;
import android.provider.Settings;

/**
 * Created by plcgo on 2017/2/14.
 */

public class SystemUtils {

    public static String getVersionName(Context context) {
        PackageInfo info = null;
        PackageManager pm = context.getPackageManager();
        try {
            info = pm.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return info.versionName;

    }

    public static int getVersionCode(Context context) {
        PackageInfo info = null;
        PackageManager pm = context.getPackageManager();
        try {
            info = pm.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return info.versionCode;
    }

    /**
     * 点亮屏幕，解屏
     */
    public static void openScreen(Context context) {
        KeyguardManager km;
        KeyguardManager.KeyguardLock kl;
        PowerManager pm;
        PowerManager.WakeLock wl;
        pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);

        //获取PowerManager.WakeLock对象，后面的参数|表示同时传入两个值，最后的是调试用的Tag
        wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "bright");

        //点亮屏幕
        wl.acquire();

        //得到键盘锁管理器对象
        km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        kl = km.newKeyguardLock("unLock");

        //解锁
        kl.disableKeyguard();
    }

    public static boolean isAirPlaneModeOn(Context context){
        int mode = 0;
        try {
            mode = Settings.Global.getInt(context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON);
        }catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return mode == 1;//为1的时候是飞行模式
    }
}
