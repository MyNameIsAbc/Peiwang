package com.example.net;


import com.example.bean.ShengboBean;
import com.example.bean.StatusBean;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @author:TQX
 * @Date: 2019/7/3
 * @description:
 */
public interface ApiService {

    @FormUrlEncoded
    @POST("api/getNetConfigWav")
    Observable<ShengboBean> peiWang(@Field("ssid") String ssid, @Field("password") String passward);

    @FormUrlEncoded
    @POST("device/getDeviceStatus")
    Observable<String> getStatus(@Field("userId") String userId, @Field("device") String device);

    @GET
    Observable<ResponseBody> getLang(@Url String url);  //返回值使用 ResponseBody 之后会对ResponseBody 进行读取

    @FormUrlEncoded
    @POST("device/setVolume")
    Observable<StatusBean> changeVolume(@Field("userId") String userId, @Field("device") String device, @Field("volume") int volume);

    @FormUrlEncoded
    @POST("device/setLanguage")
    Observable<StatusBean> changeLanguage(@Field("userId") String userId, @Field("device") String device,
                                      @Field("from") String from, @Field("to") String to, @Field("auto") int auto);
}
