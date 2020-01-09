package com.example.model;
import com.example.CallBack.MvpCallback;
import com.example.bean.LoginSuccessBean;
import com.example.bean.ValidateCodeBean;
import com.example.net.ApiService;
import com.example.net.RetrofitManager;
import com.example.utils.MD5Utils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterModel {

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

    public static void register(String phone, String passward, String code,final MvpCallback<LoginSuccessBean> callback) {
        RetrofitManager.getInstance().getRetrofit()
                //动态代理创建GithubAPI对象
                .create(ApiService.class)
                .register(phone, MD5Utils.stringToMD5(passward),code)
                //指定上游发送事件线程
                .subscribeOn(Schedulers.computation())
                //指定下游接收事件线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginSuccessBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginSuccessBean loginSuccessBean) {
                        if (loginSuccessBean != null) {
                            if (loginSuccessBean.getSuccess() == 1) callback.onSuccess(loginSuccessBean);
                            else callback.onFailure(loginSuccessBean);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        LoginSuccessBean validateCodeBean = new LoginSuccessBean();
                        validateCodeBean.setMsg(e.toString());
                        callback.onFailure(validateCodeBean);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
