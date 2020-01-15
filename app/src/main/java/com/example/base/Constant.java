package com.example.base;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.peiwang.R;


public class Constant {
    public static final String FIRST_START_KEY="firstStart";
    public int a=100;
    public static final int EVENT_BLUETOOTH_CONNECTED = 1000;

    public static final int EVENT_BLUETOOTH_CONNECTED_DEVICE = 1001;

    public static final int EVENT_BLUETOOTH_RECORDING_EVENT = 1002;

    public static final int EVENT_INIT_SUCCESS = 1003;

    public static final int EVENT_BLUETOOTH_SCO_CONNECTED = 1004;

    public static final int EVENT_BLUETOOTH_SCO_DISCONNECTED = 1005;

    public static final int EVENT_SET_MOTHER_LANGUAGE=1006;
    public static final int EVENT_SET_FOREIGN_LANGUAGE=1007;

    public static final int EVENT_LOGIN_SUCCESS=1008;

    public static final int TEXT_TYPE_LNG_CHINESE_CN = 0x100001;
    public static final int TEXT_TYPE_LNG_CHINESE_TW = 0x100002;
    public static final int TEXT_TYPE_LNG_CHINESE_HK = 0x100003;
    public static final int TEXT_TYPE_LNG_JAPANESE = 0x100004;
    public static final int TEXT_TYPE_LNG_KOREAN = 0x100005;
    public static final int TEXT_TYPE_LNG_THAI = 0x100006;
    public static final int TEXT_TYPE_LNG_ARABIAN = 0x100007;
    public static final int TEXT_TYPE_LNG_RUSSIAN = 0x100008;
    public static final int TEXT_TYPE_LNG_SPANISH = 0x100009;
    public static final int TEXT_TYPE_LNG_FRENCH = 0x10000a;

    public static final int TEXT_TYPE_LNG_GERMAN = 0x10000b;
    public static final int TEXT_TYPE_LNG_PORTUGAL_PT = 0x10000c;
    public static final int TEXT_TYPE_LNG_ITALIAN = 0x10000d;
    public static final int TEXT_TYPE_LNG_HOLLAND = 0x10000e;
    public static final int TEXT_TYPE_LNG_GREEK = 0x10000f;
    public static final int TEXT_TYPE_LNG_INDONESIA = 0x100010;
    public static final int TEXT_TYPE_LNG_HINDI = 0x100011;
    public static final int TEXT_TYPE_LNG_SERBIAN = 0x100012;
    public static final int TEXT_TYPE_LNG_SWEDISH = 0x100013;
    public static final int TEXT_TYPE_LNG_TAGALOG = 0x100014;

    public static final int TEXT_TYPE_LNG_TURKISH = 0x100015;
    public static final int TEXT_TYPE_LNG_URDU = 0x100016;
    public static final int TEXT_TYPE_LNG_VIETNAMESE = 0x100018;
    public static final int TEXT_TYPE_LNG_ENGLISH_EN = 0x100019;
    public static final int TEXT_TYPE_LNG_ENGLISH_US = 0x10001a;
    public static final int TEXT_TYPE_LNG_ENGLISH_AU = 0x10001b;
    public static final int TEXT_TYPE_LNG_ENGLISH_CA = 0x10001c;
    public static final int TEXT_TYPE_LNG_ENGLISH_PH = 0x10001d;
    public static final int TEXT_TYPE_LNG_ENGLISH_IE = 0x10001e;
    public static final int TEXT_TYPE_LNG_HEBREW = 0x10001f;

    public static final int TEXT_TYPE_LNG_HUNGARIAN = 0x100020;
    public static final int TEXT_TYPE_LNG_CAMBODIA = 0x100021;
    public static final int TEXT_TYPE_LNG_BENGAL = 0x100022;
    public static final int TEXT_TYPE_LNG_NORWEGIAN_NB = 0x100023;
    public static final int TEXT_TYPE_LNG_NORWEGIAN_NN = 0x100024;
    public static final int TEXT_TYPE_LNG_POLISH = 0x100025;
    public static final int TEXT_TYPE_LNG_ROMANIAN = 0x100026;
    public static final int TEXT_TYPE_LNG_BULGARIAN = 0x100027;
    public static final int TEXT_TYPE_LNG_CZECH = 0x100028;
    public static final int TEXT_TYPE_LNG_DANISH = 0x100029;

    public static final int TEXT_TYPE_LNG_FINNISH = 0x10002a;
    public static final int TEXT_TYPE_LNG_PORTUGAL_BR = 0x10002b;
    public static final int TEXT_TYPE_LNG_MALAYSIA = 0x10002c;
    public static final int TEXT_TYPE_LNG_UKRAINIAN = 0x10002d;
    public static final int TEXT_TYPE_LNG_ENGLISH_ZA = 0x10002e;
    public static final int TEXT_TYPE_LNG_SPANISH_AR = 0x10002f;
    public static final int TEXT_TYPE_LNG_SPANISH_MX = 0x100030;
    public static final int TEXT_TYPE_LNG_ARABIC_EG = 0x100031;
    public static final int TEXT_TYPE_LNG_ARABIC_SA = 0x100032;
    public static final int TEXT_TYPE_LNG_ARABIC_KW = 0x100033;

    public static final int TEXT_TYPE_LNG_ARABIC_IQ = 0x100034;
    public static final int TEXT_TYPE_LNG_NEPALESE = 0x100035;
    public static final int TEXT_TYPE_LNG_BURMESE = 0x100036;

    public static final int TEXT_TYPE_AR_LB = 0x100037;
    public static final int TEXT_TYPE_AR_MA = 0x100038;
    public static final int TEXT_TYPE_EN_NZ = 0x100039;
    public static final int TEXT_TYPE_AR_DZ = 0x10003a;
    public static final int TEXT_TYPE_ES_BO = 0x10003b;
    public static final int TEXT_TYPE_ES_CL = 0x10003c;
    public static final int TEXT_TYPE_ES_CO = 0x10003d;
    public static final int TEXT_TYPE_ES_CR = 0x10003e;
    public static final int TEXT_TYPE_ES_EC = 0x10003f;
    public static final int TEXT_TYPE_ES_GT = 0x100040;
    public static final int TEXT_TYPE_ES_HN = 0x100041;
    public static final int TEXT_TYPE_ES_NI = 0x100042;
    public static final int TEXT_TYPE_ES_PA = 0x100043;
    public static final int TEXT_TYPE_ES_PE = 0x100044;
    public static final int TEXT_TYPE_ES_PY = 0x100045;
    public static final int TEXT_TYPE_ES_UY = 0x100046;
    public static final int TEXT_TYPE_FR_CA = 0x100047;

    public static final int TEXT_TYPE_LO_LA = 0x100048;
    public static final int TEXT_TYPE_JV_ID = 0x100049;
    public static final int TEXT_TYPE_HR_HR = 0x100050;
    public static final int TEXT_TYPE_LT_LT = 0x100051;
    public static final int TEXT_TYPE_LV_LV = 0x100052;
    public static final int TEXT_TYPE_MN_MO = 0x100053;
    public static final int TEXT_TYPE_SK_SK = 0x100054;
    public static final int TEXT_TYPE_SL_SI = 0x100055;
    public static final int TEXT_TYPE_SW_KE = 0x100056;
    public static final int TEXT_TYPE_TA_IN = 0x100057;
    public static final int TEXT_TYPE_FA_IR = 0x100058;
    public static final int TEXT_TYPE_CA_ES = 0x100059;

    public static final int BLUETOOTH_RESPONSE = 110;
    public static final int EVENT_BLUETOOTH_ABOUTTOHOME =1009;
    public static final int EVENT_BLUETOOTH_SETTING =1010;
    public static final int EVENT_BLUETOOTH_SETTING2 =1011;



    public static final int TEXT_TYPE_ADD_DEVICE = 1024;

    public static void setLngUI(String lng, TextView textView, ImageView imageView) {
        String str = "";
        int res = 0;
        switch (lng) {
            case LngConstant.LANGUAGE_JAPANESE:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_JAPANESE);
                res = R.drawable.jp;
                break;
            case LngConstant.LANGUAGE_CHINESE_CN:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_CHINESE_CN);
                res = R.drawable.cn;
                break;
            case LngConstant.LANGUAGE_CHINESE_HK:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_CHINESE_HK);
                res = R.drawable.hk;
                break;
            case LngConstant.LANGUAGE_CHINESE_TW:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_CHINESE_TW);
                res = R.drawable.tw;
                break;
            case LngConstant.LANGUAGE_ENGLISH_US:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_ENGLISH_US);
                res = R.drawable.us;
                break;
            case LngConstant.LANGUAGE_ENGLISH_EN:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_ENGLISH_EN);
                res = R.drawable.gb;
                break;
            case LngConstant.LANGUAGE_ENGLISH_AU:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_ENGLISH_AU);
                res = R.drawable.au;
                break;
            case LngConstant.LANGUAGE_ENGLISH_CA:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_ENGLISH_CA);
                res = R.drawable.ca;
                break;
            case LngConstant.LANGUAGE_ENGLISH_PH:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_ENGLISH_PH);
                res = R.drawable.ph;
                break;
            case LngConstant.LANGUAGE_ENGLISH_IE:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_ENGLISH_IE);
                res = R.drawable.ie;
                break;

            case LngConstant.LANGUAGE_KOREAN:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_KOREAN);
                res = R.drawable.kr;
                break;
            case LngConstant.LANGUAGE_THAI:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_THAI);
                res = R.drawable.th;
                break;
            case LngConstant.LANGUAGE_RUSSIAN:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_RUSSIAN);
                res = R.drawable.ru;
                break;
            case LngConstant.LANGUAGE_SPANISH:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_SPANISH);
                res = R.drawable.es;
                break;
            case LngConstant.LANGUAGE_VIETNAMESE:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_VIETNAMESE);
                res = R.drawable.vn;
                break;
            case LngConstant.LANGUAGE_FRENCH:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_FRENCH);
                res = R.drawable.fr;
                break;
            case LngConstant.LANGUAGE_GERMAN:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_GERMAN);
                res = R.drawable.de;
                break;
            case LngConstant.LANGUAGE_PORTUGAL_PT:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_PORTUGAL_PT);
                res = R.drawable.pt;
                break;
            case LngConstant.LANGUAGE_ITALIAN:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_ITALIAN);
                res = R.drawable.it;
                break;
            case LngConstant.LANGUAGE_HOLLAND:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_HOLLAND);
                res = R.drawable.nl;
                break;
            case LngConstant.LANGUAGE_GREEK:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_GREEK);
                res = R.drawable.gr;
                break;
            case LngConstant.LANGUAGE_HINDI:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_HINDI);
                res = R.drawable.in;
                break;
            case LngConstant.LANGUAGE_INDONESIA:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_INDONESIA);
                res = R.drawable.id;
                break;
            case LngConstant.LANGUAGE_SERBIAN:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_SERBIAN);
                res = R.drawable.rp;
                break;
            case LngConstant.LANGUAGE_SWEDISH:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_SWEDISH);
                res = R.drawable.se;
                break;
            case LngConstant.LANGUAGE_TAGALOG:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_TAGALOG);
                res = R.drawable.ph;
                break;
            case LngConstant.LANGUAGE_TURKISH:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_TURKISH);
                res = R.drawable.tr;
                break;
            case LngConstant.LANGUAGE_URDU:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_URDU);
                res = R.drawable.pk;
                break;
            case LngConstant.LANGUAGE_ARABIC:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_ARABIAN);
                res = R.drawable.ae;
                break;
            case LngConstant.LANGUAGE_HEBREW:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_HEBREW);
                res = R.drawable.il;
                break;
            case LngConstant.LANGUAGE_ROMANIAN:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_ROMANIAN);
                res = R.drawable.ro;
                break;
            case LngConstant.LANGUAGE_UKRAINIAN:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_UKRAINIAN);
                res = R.drawable.ua;
                break;
            case LngConstant.LANGUAGE_HUNGARIAN:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_HUNGARIAN);
                res = R.drawable.hu;
                break;
            case LngConstant.LANGUAGE_CAMBODIA:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_CAMBODIA);
                res = R.drawable.kh;
                break;
            case LngConstant.LANGUAGE_BENGAL:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_BENGAL);
                res = R.drawable.bd;
                break;
            case LngConstant.LANGUAGE_NORWEGIAN_NB:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_NORWEGIAN_NB);
                res = R.drawable.no;
                break;
            case LngConstant.LANGUAGE_NORWEGIAN_NN:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_NORWEGIAN_NN);
                res = R.drawable.no;
                break;
            case LngConstant.LANGUAGE_POLISH:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_POLISH);
                res = R.drawable.pl;
                break;
            case LngConstant.LANGUAGE_BULGARIAN:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_BULGARIAN);
                res = R.drawable.bg;
                break;
            case LngConstant.LANGUAGE_CZECH:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_CZECH);
                res = R.drawable.cz;
                break;
            case LngConstant.LANGUAGE_DANISH:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_DANISH);
                res = R.drawable.dk;
                break;
            case LngConstant.LANGUAGE_FINNISH:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_FINNISH);
                res = R.drawable.fi;
                break;
            case LngConstant.LANGUAGE_PORTUGAL_BR:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_PORTUGAL_BR);
                res = R.drawable.br;
                break;
            case LngConstant.LANGUAGE_MALAYSIA:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_MALAYSIA);
                res = R.drawable.my;
                break;
            case LngConstant.LANGUAGE_SPANISH_AR:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_SPANISH_AR);
                res = R.drawable.ar;
                break;
            case LngConstant.LANGUAGE_SPANISH_MX:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_SPANISH_MX);
                res = R.drawable.mx;
                break;
            case LngConstant.LANGUAGE_ARABIC_EG:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_ARABIC_EG);
                res = R.drawable.eg;
                break;
            case LngConstant.LANGUAGE_ARABIC_SA:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_ARABIC_SA);
                res = R.drawable.sa;
                break;
            case LngConstant.LANGUAGE_ARABIC_KW:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_ARABIC_KW);
                res = R.drawable.kw;
                break;
            case LngConstant.LANGUAGE_ARABIC_IQ:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_ARABIC_IQ);
                res = R.drawable.id;
                break;
            case LngConstant.LANGUAGE_NEPALESE:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_NEPALESE);
                res = R.drawable.np;
                break;
            case LngConstant.LANGUAGE_ENGLISH_ZA:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_ENGLISH_ZA);
                res = R.drawable.za;
                break;
            case LngConstant.LANGUAGE_BURMESE:
                str = App.getSysText(Constant.TEXT_TYPE_LNG_BURMESE);
                res = R.drawable.my_my;
                break;
            case LngConstant.AR_LB:
                str = App.getSysText(Constant.TEXT_TYPE_AR_LB);
                res = R.drawable.ar_lb;
                break;
            case LngConstant.AR_MA:
                str = App.getSysText(Constant.TEXT_TYPE_AR_MA);
                res = R.drawable.ar_ma;
                break;
            case LngConstant.EN_NZ:
                str = App.getSysText(Constant.TEXT_TYPE_EN_NZ);
                res = R.drawable.en_nz;
                break;
            case LngConstant.AR_DZ:
                str = App.getSysText(Constant.TEXT_TYPE_AR_DZ);
                res = R.drawable.ar_dz;
                break;
            case LngConstant.ES_BO:
                str = App.getSysText(Constant.TEXT_TYPE_ES_BO);
                res = R.drawable.es_bo;
                break;
            case LngConstant.ES_CL:
                str = App.getSysText(Constant.TEXT_TYPE_ES_CL);
                res = R.drawable.es_cl;
                break;
            case LngConstant.ES_CO:
                str = App.getSysText(Constant.TEXT_TYPE_ES_CO);
                res = R.drawable.es_co;
                break;
            case LngConstant.ES_CR:
                str = App.getSysText(Constant.TEXT_TYPE_ES_CR);
                res = R.drawable.es_cr;
                break;
            case LngConstant.ES_EC:
                str = App.getSysText(Constant.TEXT_TYPE_ES_EC);
                res = R.drawable.es_ec;
                break;
            case LngConstant.ES_GT:
                str = App.getSysText(Constant.TEXT_TYPE_ES_GT);
                res = R.drawable.es_gt;
                break;
            case LngConstant.ES_HN:
                str = App.getSysText(Constant.TEXT_TYPE_ES_HN);
                res = R.drawable.es_hn;
                break;
            case LngConstant.ES_NI:
                str = App.getSysText(Constant.TEXT_TYPE_ES_NI);
                res = R.drawable.es_ni;
                break;
            case LngConstant.ES_PA:
                str = App.getSysText(Constant.TEXT_TYPE_ES_PA);
                res = R.drawable.es_pa;
                break;
            case LngConstant.ES_PE:
                str = App.getSysText(Constant.TEXT_TYPE_ES_PE);
                res = R.drawable.es_pe;
                break;
            case LngConstant.ES_PY:
                str = App.getSysText(Constant.TEXT_TYPE_ES_PY);
                res = R.drawable.es_py;
                break;
            case LngConstant.ES_UY:
                str = App.getSysText(Constant.TEXT_TYPE_ES_UY);
                res = R.drawable.es_uy;
                break;
            case LngConstant.FR_CA:
                str = App.getSysText(Constant.TEXT_TYPE_FR_CA);
                res = R.drawable.es_ca;
                break;

            case LngConstant.LO_LA:
                str = App.getSysText(Constant.TEXT_TYPE_LO_LA);
                res = R.drawable.lo_la;
                break;
            case LngConstant.JV_ID:
                str = App.getSysText(Constant.TEXT_TYPE_JV_ID);
                res = R.drawable.id;
                break;
            case LngConstant.HR_HR:
                str = App.getSysText(Constant.TEXT_TYPE_HR_HR);
                res = R.drawable.hr_hr;
                break;
            case LngConstant.LT_LT:
                str = App.getSysText(Constant.TEXT_TYPE_LT_LT);
                res = R.drawable.lt_lt;
                break;
            case LngConstant.LV_LV:
                str = App.getSysText(Constant.TEXT_TYPE_LV_LV);
                res = R.drawable.lv_lv;
                break;
            case LngConstant.MN_MO:
                str = App.getSysText(Constant.TEXT_TYPE_MN_MO);
                res = R.drawable.mn_mo;
                break;
            case LngConstant.SK_SK:
                str = App.getSysText(Constant.TEXT_TYPE_SK_SK);
                res = R.drawable.sk_sk;
                break;
            case LngConstant.SL_SI:
                str = App.getSysText(Constant.TEXT_TYPE_SL_SI);
                res = R.drawable.sl_si;
                break;
            case LngConstant.SW_KE:
                str = App.getSysText(Constant.TEXT_TYPE_SW_KE);
                res = R.drawable.sw_ke;
                break;
            case LngConstant.TA_IN:
                str = App.getSysText(Constant.TEXT_TYPE_TA_IN);
                res = R.drawable.ta_in;
                break;
            case LngConstant.FA_IR:
                str = App.getSysText(Constant.TEXT_TYPE_FA_IR);
                res = R.drawable.fa_ir;
                break;
            case LngConstant.CA_ES:
                str = App.getSysText(Constant.TEXT_TYPE_CA_ES);
                res = R.drawable.ca_es;
                break;
        }
        if (textView!=null)
        textView.setText(str);
        if (imageView!=null)
        imageView.setImageResource(res);
    }
}
