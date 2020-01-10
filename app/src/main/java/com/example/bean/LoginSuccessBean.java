package com.example.bean;

public class LoginSuccessBean {


    /**
     * success : 1
     * code : 1001
     * msg : Operation Success
     * data : {"token":"c48a4298-eea2-46eb-a2c7-c4bc60e33e16","t":1578560947999,"expire":3600,"uid":4,"telephone":"13713382371","email":null,"username":null,"type":1}
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
         * token : c48a4298-eea2-46eb-a2c7-c4bc60e33e16
         * t : 1578560947999
         * expire : 3600
         * uid : 4
         * telephone : 13713382371
         * email : null
         * username : null
         * type : 1
         */

        private String token;
        private long t;
        private int expire;
        private int uid;
        private String telephone;
        private Object email;
        private Object username;
        private int type;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public long getT() {
            return t;
        }

        public void setT(long t) {
            this.t = t;
        }

        public int getExpire() {
            return expire;
        }

        public void setExpire(int expire) {
            this.expire = expire;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getUsername() {
            return username;
        }

        public void setUsername(Object username) {
            this.username = username;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "token='" + token + '\'' +
                    ", t=" + t +
                    ", expire=" + expire +
                    ", uid=" + uid +
                    ", telephone='" + telephone + '\'' +
                    ", email=" + email +
                    ", username=" + username +
                    ", type=" + type +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LoginSuccessBean{" +
                "success=" + success +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
