package com.example.presenter;


import com.example.CallBack.MvpCallback;
import com.example.bean.LoginFailBean;
import com.example.bean.LoginSuccessBean;
import com.example.bean.ValidateCodeBean;
import com.example.model.LoginModel;
import com.example.model.RegisterModel;
import com.example.view.RegisterView;

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
                mView.showMessage(validateCodeBean.getMsg());
                mView.hideLoading();
            }
        });
    }

    public void register(String phone ,String code,String passward) {
        mView.showLoading();
        RegisterModel.register(phone, passward,code, new MvpCallback<LoginSuccessBean>() {
            @Override
            public void onSuccess(LoginSuccessBean o) {
                mView.showRegisterData(o.getMsg());
                mView.hideLoading();
            }

            @Override
            public void onFailure(LoginSuccessBean o) {
                mView.showMessage(o.getMsg());
                mView.hideLoading();
            }
        });
    }
}
