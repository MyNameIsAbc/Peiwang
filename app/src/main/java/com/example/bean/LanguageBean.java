package com.example.bean;

import java.io.Serializable;

public class LanguageBean implements Serializable {

    /**
     * langcode : fa_ir
     * memo : 波斯语（伊朗）
     */

    private String langcode;
    private String memo;

    public String getLangcode() {
        return langcode;
    }

    public void setLangcode(String langcode) {
        this.langcode = langcode;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
