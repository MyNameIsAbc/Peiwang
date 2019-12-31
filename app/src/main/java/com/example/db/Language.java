package com.example.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class Language {
    @Id(autoincrement = true)//设置自增长
    private Long id;
    @Index(unique = true)//设置唯一性
    private String langid;//
    private String langcode;//
    private String momo;//
    @Generated(hash = 838553061)
    public Language(Long id, String langid, String langcode, String momo) {
        this.id = id;
        this.langid = langid;
        this.langcode = langcode;
        this.momo = momo;
    }
    @Generated(hash = 1478671802)
    public Language() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getLangid() {
        return this.langid;
    }
    public void setLangid(String langid) {
        this.langid = langid;
    }
    public String getLangcode() {
        return this.langcode;
    }
    public void setLangcode(String langcode) {
        this.langcode = langcode;
    }
    public String getMomo() {
        return this.momo;
    }
    public void setMomo(String momo) {
        this.momo = momo;
    }

    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", langid='" + langid + '\'' +
                ", langcode='" + langcode + '\'' +
                ", momo='" + momo + '\'' +
                '}';
    }
}
