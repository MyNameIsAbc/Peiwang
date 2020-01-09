package com.example.presenter;


import com.example.CallBack.MvpCallback;
import com.example.bean.LoginFailBean;
import com.example.model.LoginModel;
import com.example.view.MvpView;

public class LoginPresenter {
    private MvpView mView;

    public LoginPresenter(MvpView mvpView) {
        this.mView = mvpView;
    }

    /**
     * 获取网络数据
     *
     */
    public void getData(String phone, String passward){
        //显示正在加载进度条
        mView.showLoading();
        // 调用Model请求数据
        LoginModel.Login(phone,passward, new MvpCallback<Object>() {
            @Override
            public void onSuccess(Object data) {
                //调用view接口显示数据
                mView.showData("登陆成功");
                mView.hideLoading();
            }
            @Override
            public void onFailure(Object msg) {
                //调用view接口提示失败信息
                LoginFailBean loginFailBean=(LoginFailBean)msg;
                mView.showFailureMessage(loginFailBean.getMsg());
                mView.hideLoading();
            }
        });
    }

}
