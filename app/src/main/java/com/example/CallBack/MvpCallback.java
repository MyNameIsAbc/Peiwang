package com.example.CallBack;

public interface MvpCallback<T> {
    /**
     * 数据请求成功
     * @param t 请求到的数据
     */
    void onSuccess(T t);
    /**
     *  使用网络API接口请求方式时，虽然已经请求成功但是由
     *  于{@code msg}的原因无法正常返回数据。
     */
    void onFailure(T t);

}
