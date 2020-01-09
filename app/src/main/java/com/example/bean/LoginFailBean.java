package com.example.bean;

public class LoginFailBean {

    /**
     * success : 0
     * code : -1
     * msg : Password Error!
     * data : null
     */

    private int success;
    private int code;
    private String msg;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
