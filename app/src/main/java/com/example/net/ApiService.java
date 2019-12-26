package com.example.net;


import com.example.bean.ShengboBean;
import com.example.bean.StatusBean;

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
    @POST("api/getNetConfigWav")
    Observable<ShengboBean> peiWang(@Field("ssid") String ssid, @Field("password") String passward);

    @FormUrlEncoded
    @POST("device/getDeviceStatus")
    Observable<String>getStatus(@Field("userId")String userId, @Field("device")String device);
}
