package com.example.bean;

public class ValidateCodeBean {
    /**
     * success : 1
     * code : 1001
     * msg : 查询成功
     * data : 508795
     */

    private int success;
    private int code;
    private String msg;
    private String data;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ValidateCodeBean{" +
                "success=" + success +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
