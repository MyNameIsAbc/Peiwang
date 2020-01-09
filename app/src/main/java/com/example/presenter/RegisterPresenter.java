package com.example.presenter;

import com.juren.CallBack.MvpCallback;
import com.juren.bean.LoginFailBean;
import com.juren.bean.LoginSuccessBean;
import com.juren.bean.ValidateCodeBean;
import com.juren.model.LoginModel;
import com.juren.model.RegisterModel;
import com.juren.view.RegisterView;


public class RegisterPresenter {
    private RegisterView mView;

    public RegisterPresenter(RegisterView mView) {
        this.mView = mView;
    }

    /**
     * 获取网络数据
     */
    public void getValidateCode(String phone) {
        //显示正在加载进度条
        mView.showLoading();
        // 调用Model请求数据
        RegisterModel.getValidateCode(phone, new MvpCallback<ValidateCodeBean>() {
            @Override
            public void onSuccess(ValidateCodeBean validateCodeBean) {
                mView.getValidateCode("验证码下发成功");
                mView.hideLoading();
            }

            @Override
            public void onFailure(ValidateCodeBean validateCodeBean) {
                mView.showFailureMessage(validateCodeBean.getMsg());
                mView.hideLoading();
            }
        });
    }

    public void checkValidateCode(String code, final String phone, final String passward) {
        mView.showLoading();
        RegisterModel.checkValidateCode(phone, code, new MvpCallback<ValidateCodeBean>() {
            @Override
            public void onSuccess(ValidateCodeBean validateCodeBean) {
                RegisterModel.register(phone, passward, new MvpCallback<ValidateCodeBean>() {
                    @Override
                    public void onSuccess(ValidateCodeBean validateCodeBean) {
                        LoginModel.Login(phone, passward, new MvpCallback<Object>() {
                            @Override
                            public void onSuccess(Object o) {
                                LoginSuccessBean loginSuccessBean=(LoginSuccessBean)o;
                                mView.showRegisterData(loginSuccessBean.getMsg());
                                mView.hideLoading();
                            }

                            @Override
                            public void onFailure(Object o) {
                                LoginFailBean loginFailBean=(LoginFailBean)o;
                                mView.showFailureMessage(loginFailBean.getMsg());
                                mView.hideLoading();
                            }
                        });
                    }

                    @Override
                    public void onFailure(ValidateCodeBean validateCodeBean) {
                        mView.showFailureMessage(validateCodeBean.getMsg());
                        mView.hideLoading();
                    }
                });
            }

            @Override
            public void onFailure(ValidateCodeBean validateCodeBean) {
                mView.showFailureMessage(validateCodeBean.getMsg());
                mView.hideLoading();
            }
        });
    }
}
