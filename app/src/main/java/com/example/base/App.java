package com.example.base;


import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import com.example.db.LangDaoUtils;
import com.example.db.Language;
import com.example.peiwang.R;
import com.example.utils.AppUtils;
import com.example.utils.LangService;
import com.example.utils.SharePreferencesUtils;
import com.example.utils.SystemUtils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.Bugly;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.List;


public class App extends MultiDexApplication {


    public static String getSysText(int textType) {
        return SystemText.getChineseText(textType);
    }

    public static String Appname, AppCode;

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)  // 是否显示线程信息，默认为ture
                .methodCount(0)         // 显示的方法行数，默认为2
                .methodOffset(7)        // 隐藏内部方法调用到偏移量，默认为5// 更改要打印的日志策略。
                .tag("PRETTY_LOGGER")   // 每个日志的全局标记。默认PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        MultiDex.install(this);
        setupLeakCanary();
        ZXingLibrary.initDisplayOpinion(this);
        if (!SharePreferencesUtils.getBoolean(getApplicationContext(), "init", false)) {
            Intent intent = new Intent(this, LangService.class);
            startService(intent);
        } else {
            List<Language> languages = new LangDaoUtils(this).queryAllDevice();
            for (Language l : languages) {
                Logger.d("stored language:" + l.toString());
            }
            Logger.d("language size:" + languages.size());
        }
        Bugly.init(getApplicationContext(), "205c860c87", true);
        Appname= AppUtils.getAppName(this);
        AppCode=SystemUtils.getVersionName(this)+"";
    }

    protected void setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }


}
