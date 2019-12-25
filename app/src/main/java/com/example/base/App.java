package com.example.base;




import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.squareup.leakcanary.LeakCanary;

import java.util.concurrent.CopyOnWriteArrayList;


public class App extends MultiDexApplication {


    public static String getSysText(int textType) {
      return   SystemText.getChineseText(textType);
    }
    public static double latitude,longitude;
    public static String AoiName,Address,City;
    @Override
    public void onCreate() {
        super.onCreate();
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // 是否显示线程信息，默认为ture
                .methodCount(0)         // 显示的方法行数，默认为2
                .methodOffset(7)        // 隐藏内部方法调用到偏移量，默认为5// 更改要打印的日志策略。
                .tag("PRETTY_LOGGER")   // 每个日志的全局标记。默认PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        MultiDex.install(this);
        setupLeakCanary();
    }

    protected void setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }


}
