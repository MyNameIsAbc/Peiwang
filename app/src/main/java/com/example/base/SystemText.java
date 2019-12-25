package com.example.base;

public class SystemText {
    public static String getChineseText(int textType) {
        String str = "";
        switch (textType) {
            case Constant.TEXT_TYPE_LNG_CHINESE_CN:
                str = "中文(简体)";
                break;
            case Constant.TEXT_TYPE_LNG_CHINESE_HK:
                str = "中文（粤语）";
                break;
            case Constant.TEXT_TYPE_LNG_CHINESE_TW:
                str = "中文(繁体)";
                break;
            case Constant.TEXT_TYPE_LNG_ENGLISH_EN:
                str = "英国(英语)";
                break;
            case Constant.TEXT_TYPE_LNG_ENGLISH_US:
                str = "美国(英语)";
                break;
            case Constant.TEXT_TYPE_LNG_ENGLISH_AU:
                str = "澳大利亚(英语)";
                break;
            case Constant.TEXT_TYPE_LNG_ENGLISH_CA:
                str = "加拿大(英语)";
                break;
            case Constant.TEXT_TYPE_LNG_ENGLISH_IE:
                str = "爱尔兰(英语)";
                break;
            case Constant.TEXT_TYPE_LNG_ENGLISH_PH:
                str = "菲律宾(英语)";
                break;
            case Constant.TEXT_TYPE_LNG_JAPANESE:
                str = "日语";
                break;
            case Constant.TEXT_TYPE_LNG_KOREAN:
                str = "韩语";
                break;
            case Constant.TEXT_TYPE_LNG_FRENCH:
                str = "法语";
                break;
            case Constant.TEXT_TYPE_LNG_RUSSIAN:
                str = "俄语";
                break;
            case Constant.TEXT_TYPE_LNG_SPANISH:
                str = "国际(西班牙语)";
                break;
            case Constant.TEXT_TYPE_LNG_THAI:
                str = "泰语";
                break;
            case Constant.TEXT_TYPE_LNG_VIETNAMESE:
                str = "越南语";
                break;
            case Constant.TEXT_TYPE_LNG_PORTUGAL_PT:
                str = "葡萄牙(葡萄牙语)";
                break;
            case Constant.TEXT_TYPE_LNG_PORTUGAL_BR:
                str = "巴西(葡萄牙语)";
                break;
            case Constant.TEXT_TYPE_LNG_GERMAN:
                str = "德语";
                break;
            case Constant.TEXT_TYPE_LNG_ITALIAN:
                str = "意大利语";
                break;
            case Constant.TEXT_TYPE_LNG_HOLLAND:
                str = "荷兰语";
                break;
            case Constant.TEXT_TYPE_LNG_GREEK:
                str = "希腊语";
                break;
            case Constant.TEXT_TYPE_LNG_INDONESIA:
                str = "印尼语";
                break;
            case Constant.TEXT_TYPE_LNG_MALAYSIA:
                str = "马来语";
                break;
            case Constant.TEXT_TYPE_LNG_TURKISH:
                str = "土耳其语";
                break;
            case Constant.TEXT_TYPE_LNG_HINDI:
                str = "印地语";
                break;
            case Constant.TEXT_TYPE_LNG_SERBIAN:
                str = "拉丁(塞尔维亚)";
                break;
            case Constant.TEXT_TYPE_LNG_SWEDISH:
                str = "瑞典语";
                break;
            case Constant.TEXT_TYPE_LNG_TAGALOG:
                str = "菲律宾(塔加路语)";
                break;
            case Constant.TEXT_TYPE_LNG_UKRAINIAN:
                str = "乌克兰";
                break;
            case Constant.TEXT_TYPE_LNG_URDU:
                str = "乌尔都语";
                break;
            case Constant.TEXT_TYPE_LNG_ARABIAN:
                str = "阿联酋(阿拉伯语)";
                break;
            case Constant.TEXT_TYPE_LNG_HEBREW:
                str = "希伯来语";
                break;
            case Constant.TEXT_TYPE_LNG_HUNGARIAN:
                str = "匈牙利语";
                break;
            case Constant.TEXT_TYPE_LNG_CAMBODIA:
                str = "柬埔寨(高棉语)";
                break;
            case Constant.TEXT_TYPE_LNG_BENGAL:
                str = "孟加拉语";
                break;
            case Constant.TEXT_TYPE_LNG_NORWEGIAN_NB:
                str = "挪威(布克莫爾语)";
                break;
            case Constant.TEXT_TYPE_LNG_NORWEGIAN_NN:
                str = "挪威(尼諾斯克)";
                break;
            case Constant.TEXT_TYPE_LNG_POLISH:
                str = "波兰语";
                break;
            case Constant.TEXT_TYPE_LNG_ROMANIAN:
                str = "罗马尼亚语";
                break;
            case Constant.TEXT_TYPE_LNG_BULGARIAN:
                str = "保加利亚语";
                break;
            case Constant.TEXT_TYPE_LNG_CZECH:
                str = "捷克语";
                break;
            case Constant.TEXT_TYPE_LNG_DANISH:
                str = "丹麦语";
                break;
            case Constant.TEXT_TYPE_LNG_FINNISH:
                str = "芬兰语";
                break;
            case Constant.TEXT_TYPE_LNG_ENGLISH_ZA:
                str = "南非(英语)";
                break;
            case Constant.TEXT_TYPE_LNG_ARABIC_EG:
                str = "埃及(阿拉伯语)";
                break;
            case Constant.TEXT_TYPE_LNG_ARABIC_SA:
                str = "沙特阿拉伯(阿拉伯语)";
                break;
            case Constant.TEXT_TYPE_LNG_ARABIC_KW:
                str = "科威特(阿拉伯语)";
                break;
            case Constant.TEXT_TYPE_LNG_SPANISH_AR:
                str = "阿根廷(西班牙语)";
                break;
            case Constant.TEXT_TYPE_LNG_SPANISH_MX:
                str = "墨西哥(西班牙语)";
                break;
            case Constant.TEXT_TYPE_LNG_ARABIC_IQ:
                str = "伊拉克(阿拉伯语)";
                break;
            case Constant.TEXT_TYPE_LNG_NEPALESE:
                str = "尼泊尔语";
                break;
            case Constant.TEXT_TYPE_LNG_BURMESE:
                str = "缅甸语";
                break;
            case Constant.TEXT_TYPE_AR_LB:
                str = "黎巴嫩(阿拉伯语)";
                break;
            case Constant.TEXT_TYPE_AR_MA:
                str = "摩洛哥(阿拉伯语)";
                break;
            case Constant.TEXT_TYPE_EN_NZ:
                str = "新西兰(英语)";
                break;
            case Constant.TEXT_TYPE_AR_DZ:
                str = "阿尔及利亚(阿拉伯语)";
                break;
            case Constant.TEXT_TYPE_ES_BO:
                str = "玻利维亚(西班牙语)";
                break;
            case Constant.TEXT_TYPE_ES_CL:
                str = "智利(西班牙语)";
                break;
            case Constant.TEXT_TYPE_ES_CO:
                str = "哥伦比亚(西班牙语)";
                break;
            case Constant.TEXT_TYPE_ES_CR:
                str = "哥斯达黎加(西班牙语)";
                break;
            case Constant.TEXT_TYPE_ES_EC:
                str = "厄瓜多尔(西班牙语)";
                break;
            case Constant.TEXT_TYPE_ES_GT:
                str = "危地马拉(西班牙语)";
                break;
            case Constant.TEXT_TYPE_ES_HN:
                str = "洪都拉斯(西班牙语)";
                break;
            case Constant.TEXT_TYPE_ES_NI:
                str = "尼加拉瓜(西班牙语)";
                break;
            case Constant.TEXT_TYPE_ES_PA:
                str = "巴拿马(西班牙语)";
                break;
            case Constant.TEXT_TYPE_ES_PE:
                str = "秘鲁(西班牙语)";
                break;
            case Constant.TEXT_TYPE_ES_PY:
                str = "巴拉圭(西班牙语)";
                break;
            case Constant.TEXT_TYPE_ES_UY:
                str = "乌拉圭(西班牙语)";
                break;
            case Constant.TEXT_TYPE_FR_CA:
                str = "加拿大(法语)";
                break;
            case Constant.TEXT_TYPE_LO_LA:
                str = "老挝（老挝语）";
                break;
            case Constant.TEXT_TYPE_JV_ID:
                str = "印度尼西(爪哇语)";
                break;
            case Constant.TEXT_TYPE_HR_HR:
                str = "克罗地亚语";
                break;
            case Constant.TEXT_TYPE_LT_LT:
                str = "立陶宛语";
                break;
            case Constant.TEXT_TYPE_LV_LV:
                str = "拉脱维亚语";
                break;
            case Constant.TEXT_TYPE_MN_MO:
                str = "蒙古语";
                break;
            case Constant.TEXT_TYPE_SK_SK:
                str = "斯洛伐克语";
                break;
            case Constant.TEXT_TYPE_SL_SI:
                str = "斯洛文尼亚语";
                break;
            case Constant.TEXT_TYPE_SW_KE:
                str = "肯尼亚(斯瓦希里语)";
                break;
            case Constant.TEXT_TYPE_TA_IN:
                str = "泰米尔语";
                break;
            case Constant.TEXT_TYPE_FA_IR:
                str = "伊朗（波斯语）";
                break;
            case Constant.TEXT_TYPE_CA_ES:
                str = "加泰罗尼亚语";
                break;


        }
        return str;
    }
}
