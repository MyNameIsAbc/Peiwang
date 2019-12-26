package com.example.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Device {
    @Id(autoincrement = true)
    private Long _id;
    private String source;
    @NotNull
    private String sn;
    @Generated(hash = 2083837859)
    public Device(Long _id, String source, @NotNull String sn) {
        this._id = _id;
        this.source = source;
        this.sn = sn;
    }
    @Generated(hash = 1469582394)
    public Device() {
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    public String getSource() {
        return this.source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public String getSn() {
        return this.sn;
    }
    public void setSn(String sn) {
        this.sn = sn;
    }
}
