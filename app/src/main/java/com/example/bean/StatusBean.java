package com.example.bean;

public class StatusBean {

    /**
     * success : 1
     * code : 1001
     * msg : Get device information successfully!
     * data : {"volume":10,"power":2,"network":3,"autoLang":1,"fromLang":"zh_cn","toLang":"en_us"}
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
         * volume : 10
         * power : 2
         * network : 3
         * autoLang : 1
         * fromLang : zh_cn
         * toLang : en_us
         */

        private int volume;
        private int power;
        private int network;
        private int autoLang;
        private String fromLang;
        private String toLang;

        public int getVolume() {
            return volume;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }

        public int getPower() {
            return power;
        }

        public void setPower(int power) {
            this.power = power;
        }

        public int getNetwork() {
            return network;
        }

        public void setNetwork(int network) {
            this.network = network;
        }

        public int getAutoLang() {
            return autoLang;
        }

        public void setAutoLang(int autoLang) {
            this.autoLang = autoLang;
        }

        public String getFromLang() {
            return fromLang;
        }

        public void setFromLang(String fromLang) {
            this.fromLang = fromLang;
        }

        public String getToLang() {
            return toLang;
        }

        public void setToLang(String toLang) {
            this.toLang = toLang;
        }
    }
}
