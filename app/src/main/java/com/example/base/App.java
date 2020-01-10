package com.example.base;


import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.example.bean.TuYaTokenBean;
import com.example.bean.TuyaInfoBean;
import com.example.db.LangDaoUtils;
import com.example.db.Language;
import com.example.net.ApiService;
import com.example.net.RetrofitManager;
import com.example.peiwang.R;
import com.example.utils.AppUtils;
import com.example.utils.LangService;
import com.example.utils.MD5Utils;
import com.example.utils.SharePreferencesUtils;
import com.example.utils.SystemUtils;
import com.kongzue.dialog.util.DialogSettings;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.sahooz.library.Country;
import com.sahooz.library.ExceptionCallback;
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
import com.tuya.smart.home.sdk.TuyaHomeSdk;
import com.tuya.smart.sdk.api.INeedLoginListener;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class App extends MultiDexApplication {


    public static String getSysText(int textType) {
        return SystemText.getChineseText(textType);
    }

    public static String Appname, AppCode;
    public static List<Country> countries = new ArrayList<>();
    public static TuyaInfoBean tuyaInfoBean;
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
            }
            Logger.d("language size:" + languages.size());
        }
        Bugly.init(getApplicationContext(), "205c860c87", true);
        Appname = AppUtils.getAppName(this);
        AppCode = SystemUtils.getVersionName(this) + "";
        TuyaHomeSdk.init(this);
        TuyaHomeSdk.setDebugMode(true);

        initDialog(this);

        countries.clear();
        countries.addAll(Country.getAll(this, new ExceptionCallback() {
            @Override
            public void onIOException(IOException e) {

            }

            @Override
            public void onJSONException(JSONException e) {

            }
        }));

        TuyaHomeSdk.setOnNeedLoginListener(new INeedLoginListener() {
            @Override
            public void onNeedLogin(Context context) {
                Logger.d("needLogin:");
            }
        });
    }

    private void LoginTuya() {
        RetrofitManager.getInstance().getRetrofit()
                //动态代理创建GithubAPI对象
                .create(ApiService.class)
                .getTuyaClientInfo(SharePreferencesUtils.getString(getApplicationContext(), "accesstoken", ""))
                //指定上游发送事件线程
                .subscribeOn(Schedulers.computation())
                //指定下游接收事件线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TuyaInfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TuyaInfoBean s) {
                        Logger.d("getTuyaClientInfo:"+s);
                        tuyaInfoBean=s;
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    protected void setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }


    /**
     * Kongzue Dialog V3  需要进行一些预先配置，诸如对话框组件整体的风格、主题和字体等
     */
    public static void initDialog(Context context) {

        DialogSettings.init();                                        //初始化清空 BaseDialog 队列
        DialogSettings.checkRenderscriptSupport(context);             //检查 Renderscript 兼容性，若设备不支持，DialogSettings.isUseBlur 会自动关闭；
        DialogSettings.DEBUGMODE = true;                              //是否允许打印日志
        DialogSettings.isUseBlur = true;                              //是否开启模糊效果，默认关闭
        DialogSettings.autoShowInputKeyboard = true;                  //设置 InputDialog 是否自动弹出输入法
        //DialogSettings.backgroundColor = Color.BLUE;
        //DialogSettings.titleTextInfo = new TextInfo().setFontSize(50);
        //DialogSettings.buttonPositiveTextInfo = new TextInfo().setFontColor(Color.GREEN);
        DialogSettings.style = DialogSettings.STYLE.STYLE_IOS;        //全局主题风格，提供三种可选风格，STYLE_MATERIAL, STYLE_KONGZUE, STYLE_IOS
        DialogSettings.theme = DialogSettings.THEME.LIGHT;            //全局对话框明暗风格，提供两种可选主题，LIGHT, DARK

    }


}
