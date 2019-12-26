package com.example.bean;

public class DeviceBean {
    /**
     * volume : 10
     * power : 2
     * network : 3
     * autoLang : 1
     * fromLang : zh_cn
     * toLang : en_us
     */
    private String sn;
    private int volume;
    private int power;
    private int network;
    private int autoLang;
    private String fromLang;
    private String toLang;

    public DeviceBean(String sn, int volume, int power, int network, int autoLang, String fromLang, String toLang) {
        this.sn = sn;
        this.volume = volume;
        this.power = power;
        this.network = network;
        this.autoLang = autoLang;
        this.fromLang = fromLang;
        this.toLang = toLang;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

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



    @Override
    public String toString() {
        return "DeviceBean{" +
                "sn='" + sn + '\'' +
                ", volume=" + volume +
                ", power=" + power +
                ", network=" + network +
                ", autoLang=" + autoLang +
                ", fromLang='" + fromLang + '\'' +
                ", toLang='" + toLang + '\'' +
                '}';
    }
}
