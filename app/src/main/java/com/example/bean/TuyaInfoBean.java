package com.example.bean;

public class TuyaInfoBean {

    /**
     * success : 1
     * code : 1001
     * msg : Operation Success
     * data : {"uid":"ay1578568459281anoP2","username":"3b7e7a7a-709f-41cf-a54f-67f9b9f00870","password":"0000000dqtv03dtf1t7bjslvmsjjjkfp","jachatId":4,"createTime":"2020-01-09 19:14:19"}
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
         * uid : ay1578568459281anoP2
         * username : 3b7e7a7a-709f-41cf-a54f-67f9b9f00870
         * password : 0000000dqtv03dtf1t7bjslvmsjjjkfp
         * jachatId : 4
         * createTime : 2020-01-09 19:14:19
         */

        private String uid;
        private String username;
        private String password;
        private int jachatId;
        private String createTime;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getJachatId() {
            return jachatId;
        }

        public void setJachatId(int jachatId) {
            this.jachatId = jachatId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
