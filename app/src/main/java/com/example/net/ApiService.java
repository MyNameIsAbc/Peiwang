package com.example.net;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author:TQX
 * @Date: 2019/7/3
 * @description:
 */
public interface ApiService {
    @FormUrlEncoded
    @POST("getNetConfigWav")
    Observable<String> peiWang(@Field("ssid") String ssid, @Field("password") String passward);

}
