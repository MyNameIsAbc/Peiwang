package com.example.net;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitManager {
    public static final String BASE_URL = "http://cloud.earchat.com:8080/api";
    private static RetrofitManager sInstance;
    private Retrofit mRetrofit;

    public static RetrofitManager getInstance() {
        if (null == sInstance) {
            synchronized (RetrofitManager.class) {
                if (null == sInstance) {
                    sInstance = new RetrofitManager();
                }
            }
        }
        return sInstance;
    }

    public Retrofit getRetrofit() {
        if (mRetrofit == null) {
            //日志显示级别
            HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
            //新建log拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Logger.d("OkHttp====Message:" + message);
                }
            });
            loggingInterceptor.setLevel(level);
            //初始化一个OkHttpClient
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .connectTimeout(30000, TimeUnit.MILLISECONDS)
                    .readTimeout(30000, TimeUnit.MILLISECONDS)
                    .writeTimeout(30000, TimeUnit.MILLISECONDS)
                    .addInterceptor(loggingInterceptor);
            OkHttpClient okHttpClient = builder.build();

            //使用该OkHttpClient创建一个Retrofit对象
            mRetrofit = new Retrofit.Builder()
                    .addConverterFactory(ScalarsConverterFactory.create())
                    //添加Gson数据格式转换器支持
                    .addConverterFactory(GsonConverterFactory.create())
                    //添加RxJava语言支持
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    //指定网络请求client
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .build();
        }
        return mRetrofit;
    }

    public static RequestBody toRequestBodyOfText (String value) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body ;
    }

    /**
     * 将文件进行转换
     * @param param 为文件类型
     * @return
     */
    public static RequestBody convertToRequestBodyMap(File param){
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), param);
        return requestBody;
    }
}
