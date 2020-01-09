package com.example.bean;

public class LoginSuccessBean {

    /**
     * success : 1
     * code : 1001
     * msg : 查询成功
     * data : {"password":"kbIfnsNiaYk7nhZRdPXdRW","ip":"47.111.100.87","port":5322,"loginid":1570630109883}
     */

    private int success;
    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * password : kbIfnsNiaYk7nhZRdPXdRW
         * ip : 47.111.100.87
         * port : 5322
         * loginid : 1570630109883
         */

        private String password;
        private String ip;
        private int port;
        private long loginid;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public long getLoginid() {
            return loginid;
        }

        public void setLoginid(long loginid) {
            this.loginid = loginid;
        }
    }
}
