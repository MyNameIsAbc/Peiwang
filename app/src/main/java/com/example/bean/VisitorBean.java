package com.example.bean;

public class VisitorBean {

    /**
     * success : 1
     * code : 1001
     * msg : Operation Success
     * data : {"token":{"token":"ddad34e3-aaaf-41d4-9489-523393b96ce2","t":1578543623079,"expire":3600,"uid":3,"telephone":null,"email":null,"username":"5879dcaa-c9d4-4ee4-b1f2-6204017d4045","type":0},"account":{"id":3,"username":"5879dcaa-c9d4-4ee4-b1f2-6204017d4045","telephone":null,"email":null,"password":"0000004vj8vtvlhl325utd34ltmdkqkf","createTime":"2020-01-09 12:20:23","type":0}}
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
         * token : {"token":"ddad34e3-aaaf-41d4-9489-523393b96ce2","t":1578543623079,"expire":3600,"uid":3,"telephone":null,"email":null,"username":"5879dcaa-c9d4-4ee4-b1f2-6204017d4045","type":0}
         * account : {"id":3,"username":"5879dcaa-c9d4-4ee4-b1f2-6204017d4045","telephone":null,"email":null,"password":"0000004vj8vtvlhl325utd34ltmdkqkf","createTime":"2020-01-09 12:20:23","type":0}
         */

        private TokenBean token;
        private AccountBean account;

        public TokenBean getToken() {
            return token;
        }

        public void setToken(TokenBean token) {
            this.token = token;
        }

        public AccountBean getAccount() {
            return account;
        }

        public void setAccount(AccountBean account) {
            this.account = account;
        }

        public static class TokenBean {
            /**
             * token : ddad34e3-aaaf-41d4-9489-523393b96ce2
             * t : 1578543623079
             * expire : 3600
             * uid : 3
             * telephone : null
             * email : null
             * username : 5879dcaa-c9d4-4ee4-b1f2-6204017d4045
             * type : 0
             */

            private String token;
            private long t;
            private int expire;
            private int uid;
            private Object telephone;
            private Object email;
            private String username;
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

            public Object getTelephone() {
                return telephone;
            }

            public void setTelephone(Object telephone) {
                this.telephone = telephone;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }

        public static class AccountBean {
            /**
             * id : 3
             * username : 5879dcaa-c9d4-4ee4-b1f2-6204017d4045
             * telephone : null
             * email : null
             * password : 0000004vj8vtvlhl325utd34ltmdkqkf
             * createTime : 2020-01-09 12:20:23
             * type : 0
             */

            private int id;
            private String username;
            private Object telephone;
            private Object email;
            private String password;
            private String createTime;
            private int type;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public Object getTelephone() {
                return telephone;
            }

            public void setTelephone(Object telephone) {
                this.telephone = telephone;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
