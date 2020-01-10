package com.example.net;


import com.example.bean.LoginSuccessBean;
import com.example.bean.ShengboBean;
import com.example.bean.StatusBean;
import com.example.bean.TuYaDeviceBean;
import com.example.bean.TuYaTokenBean;
import com.example.bean.TuyaInfoBean;
import com.example.bean.ValidateCodeBean;
import com.example.bean.VisitorBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
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
    @FormUrlEncoded
    @POST("jachat/login")
    Observable<String>login(@Field("telephone") String username,@Field("password") String password);

    @FormUrlEncoded
    @POST("jachat/login")
    Observable<String>loginCode(@Field("telephone") String telephone, @Field("code") String code);

    @GET("api/getValidateCode")
    Observable<ValidateCodeBean> getValidateCode(@Query("telephone") String telephone);

    @FormUrlEncoded
    @POST("jachat/register")
    Observable<LoginSuccessBean>register(@Field("telephone") String telephone, @Field("password") String password, @Field("code")String code);

    @GET("user/findPassword")
    Observable<ValidateCodeBean>resetPassward(@Query("password") String password, @Query("username") String username,
                                              @Query("code") String code);
    @POST("jachat/visitorLogin")
    Observable<VisitorBean>visitLogin();

    @POST("tuya/getToken")
    Observable<TuYaTokenBean>getTuYaToken(@Header("access_token")String accesstoken);

    @POST("tuya/getTuyaClientInfo")
    Observable<TuyaInfoBean>getTuyaClientInfo(@Header("access_token")String accesstoken);

    @FormUrlEncoded
    @POST("tuya/getDevicesByToken")
    Observable<TuYaDeviceBean>getDevicesByToken(@Header("access_token")String accesstoken, @Field("token")String token );
}
