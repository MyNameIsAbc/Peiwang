package com.example.view;

public interface ResetView extends MvpView {
    void getValidateCode(String data);
    void resetSuccess(String data);
}
