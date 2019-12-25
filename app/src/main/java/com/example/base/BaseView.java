package com.example.base;

import android.content.Context;
import android.view.View;

/**
 * @author:TQX
 * @Date: ${Date}
 * @description:
 */
public interface BaseView {

    /**
     * 显示正在加载view
     */
    void showLoading();

    /**
     * 关闭正在加载view
     */
    void hideLoading();

    /**
     * 显示提示
     *
     * @param msg
     */
    void showToast(String msg);

    /**
     * 显示请求错误提示
     */
    void showError();

    /**
     * 获取上下文
     *
     * @return 上下文
     */
    Context getContext();

    /**
     * log提示
     * @param msg
     */
    void showLog(String msg);

    View getView();

}
