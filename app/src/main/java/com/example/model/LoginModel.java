package com.example.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.CallBack.MvpCallback;
import com.example.bean.LoginFailBean;
import com.example.bean.LoginSuccessBean;
import com.example.bean.ValidateCodeBean;
import com.example.net.ApiService;
import com.example.net.RetrofitManager;
import com.example.utils.MD5Utils;
import com.orhanobut.logger.Logger;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
public class LoginModel {

    public static void Login(final String phone, String passward, final MvpCallback<Object> callback) {
        RetrofitManager.getInstance().getRetrofit()
                //动态代理创建GithubAPI对象
                .create(ApiService.class)
                .login(phone, MD5Utils.stringToMD5(passward))
                //指定上游发送事件线程
                .subscribeOn(Schedulers.computation())
                //指定下游接收事件线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Logger.e("login reponse:" + s);
                        JSONObject json = JSON.parseObject(s);
                        if (json != null) {
                            int success = json.getIntValue("success");
                            if (success == 1) {
                                LoginSuccessBean loginSuccessBean = null;
                                try {
                                    loginSuccessBean = JSON.parseObject(s, LoginSuccessBean.class);
                                } catch (Exception e) {
                                    loginSuccessBean = new LoginSuccessBean();
                                    loginSuccessBean.setMsg("解析失败");
                                    callback.onFailure(loginSuccessBean);
                                }
                                callback.onSuccess(loginSuccessBean);
                            } else {
                                LoginFailBean loginFailBean = null;
                                try {
                                    loginFailBean = JSON.parseObject(s, LoginFailBean.class);
                                } catch (Exception e) {
                                    loginFailBean = new LoginFailBean();
                                    loginFailBean.setMsg("解析失败");
                                    callback.onFailure(loginFailBean);
                                }
                                callback.onFailure(loginFailBean);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LoginFailBean loginFailBean = new LoginFailBean();;
                        loginFailBean.setMsg(e.toString());
                        callback.onFailure(loginFailBean);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    public static void LoginVecode(final String phone, String vecode, final MvpCallback<Object> callback) {
        RetrofitManager.getInstance().getRetrofit()
                //动态代理创建GithubAPI对象
                .create(ApiService.class)
                .loginCode(phone,vecode)
                //指定上游发送事件线程
                .subscribeOn(Schedulers.computation())
                //指定下游接收事件线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Logger.e("login reponse:" + s);
                        JSONObject json = JSON.parseObject(s);
                        if (json != null) {
                            int success = json.getIntValue("success");
                            if (success == 1) {
                                LoginSuccessBean loginSuccessBean = null;
                                try {
                                    loginSuccessBean = JSON.parseObject(s, LoginSuccessBean.class);
                                } catch (Exception e) {
                                    loginSuccessBean = new LoginSuccessBean();
                                    loginSuccessBean.setMsg("解析失败");
                                    callback.onFailure(loginSuccessBean);
                                }
                                callback.onSuccess(loginSuccessBean);
                            } else {
                                LoginFailBean loginFailBean = null;
                                try {
                                    loginFailBean = JSON.parseObject(s, LoginFailBean.class);
                                } catch (Exception e) {
                                    loginFailBean = new LoginFailBean();
                                    loginFailBean.setMsg("解析失败");
                                    callback.onFailure(loginFailBean);
                                }
                                callback.onFailure(loginFailBean);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LoginFailBean loginFailBean = new LoginFailBean();;
                        loginFailBean.setMsg(e.toString());
                        callback.onFailure(loginFailBean);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    public static void getValidateCode(final String phone, final MvpCallback<ValidateCodeBean> callback) {
        RetrofitManager.getInstance().getRetrofit()
                //动态代理创建GithubAPI对象
                .create(ApiService.class)
                .getValidateCode(phone)
                //指定上游发送事件线程
                .subscribeOn(Schedulers.computation())
                //指定下游接收事件线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ValidateCodeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ValidateCodeBean validateCodeBean) {
                        if (validateCodeBean.getSuccess() == 1) callback.onSuccess(validateCodeBean);
                        else callback.onFailure(validateCodeBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        ValidateCodeBean validateCodeBean = new ValidateCodeBean();
                        validateCodeBean.setMsg(e.toString());
                        callback.onFailure(validateCodeBean);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
