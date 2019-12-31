package com.example.utils;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;

import com.example.db.LangDaoUtils;
import com.example.db.Language;
import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class LangService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    private static final String TAG = LangService.class.getSimpleName();

    public LangService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            InputStream lan = getAssets().open("lang_code.txt");
            InputStream zh_cn = getAssets().open("zh_cn.txt");
            LangDaoUtils langDaoUtils = new LangDaoUtils(getApplicationContext());
            if (lan != null && zh_cn != null) {
                BufferedReader lanbufferedReader = new BufferedReader(new InputStreamReader(lan));
                BufferedReader zhbufferReader = new BufferedReader(new InputStreamReader(zh_cn));
                String line1 = "";
                while ((line1 = lanbufferedReader.readLine()) != null) {
                    String s[] = line1.split(" ");
                    Language language = new Language();
                    language.setLangid(s[0]);
                    language.setLangcode(s[1]);
                    langDaoUtils.insertLanguage(language);
                }

                String line2="";
                while ((line2=zhbufferReader.readLine())!=null){
                    String s[] = line2.split("=");
                    Logger.d("code:"+s[0]+"momo:"+s[1]);
                    Language language=langDaoUtils.queryLanguageByQueryBuilder(s[0]);
                    language.setMomo(s[1]);
                    langDaoUtils.update(language);
                }

                List<Language>languages=langDaoUtils.queryAllDevice();
                for (Language l:languages) {
                    Logger.d("language:"+l.toString());
                }
                Logger.d("language size:"+languages.size());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
