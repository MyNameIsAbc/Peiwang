package com.example.bean;

public class TuYaTokenBean {

    /**
     * success : 1
     * code : 1001
     * msg : Operation Success
     * data : {"region":"AY","token":"eb4RriqR","secret":"AxUs","deviceTokenToApp":"AYeb4RriqRAxUs"}
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
         * region : AY
         * token : eb4RriqR
         * secret : AxUs
         * deviceTokenToApp : AYeb4RriqRAxUs
         */

        private String region;
        private String token;
        private String secret;
        private String deviceTokenToApp;

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public String getDeviceTokenToApp() {
            return deviceTokenToApp;
        }

        public void setDeviceTokenToApp(String deviceTokenToApp) {
            this.deviceTokenToApp = deviceTokenToApp;
        }
    }
}
