package com.example.bean;

import java.util.List;

public class TuYaDeviceBean {

    /**
     * success : 1
     * code : 1001
     * msg : Operation Success
     * data : {"successDevices":[{"id":"15088205cc50e314949f","productId":"DIzuaFCYsNvO3Qg3","name":"万能遥控器","lon":null,"lat":null,"ip":"121.35.180.240","online":null}],"errorDevices":[]}
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
        private List<SuccessDevicesBean> successDevices;
        private List<?> errorDevices;

        public List<SuccessDevicesBean> getSuccessDevices() {
            return successDevices;
        }

        public void setSuccessDevices(List<SuccessDevicesBean> successDevices) {
            this.successDevices = successDevices;
        }

        public List<?> getErrorDevices() {
            return errorDevices;
        }

        public void setErrorDevices(List<?> errorDevices) {
            this.errorDevices = errorDevices;
        }

        public static class SuccessDevicesBean {
            /**
             * id : 15088205cc50e314949f
             * productId : DIzuaFCYsNvO3Qg3
             * name : 万能遥控器
             * lon : null
             * lat : null
             * ip : 121.35.180.240
             * online : null
             */

            private String id;
            private String productId;
            private String name;
            private Object lon;
            private Object lat;
            private String ip;
            private Object online;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getProductId() {
                return productId;
            }

            public void setProductId(String productId) {
                this.productId = productId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getLon() {
                return lon;
            }

            public void setLon(Object lon) {
                this.lon = lon;
            }

            public Object getLat() {
                return lat;
            }

            public void setLat(Object lat) {
                this.lat = lat;
            }

            public String getIp() {
                return ip;
            }

            public void setIp(String ip) {
                this.ip = ip;
            }

            public Object getOnline() {
                return online;
            }

            public void setOnline(Object online) {
                this.online = online;
            }
        }
    }
}
