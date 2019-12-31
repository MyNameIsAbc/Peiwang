package com.example.db;

import android.content.Context;

import com.orhanobut.logger.Logger;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class LangDaoUtils {
    private DaoManager mManager;
    private static final String DB_NAME = "Language";
    public LangDaoUtils(Context context) {
        mManager = DaoManager.getInstance();
        mManager.init(context,DB_NAME);
    }

    /**
     * 完成meizi记录的插入，如果表未创建，先创建Meizi表
     *
     * @param language
     * @return
     */
    public boolean insertLanguage(Language language) {
        boolean flag = false;
        flag = mManager.getDaoSession().getLanguageDao().insertOrReplace(language) == -1 ? false : true;
        Logger.d("insert Device :" + language + "-->" + language.toString());
        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     * @param meiziList
     * @return
     */
    public boolean insertMultLanguage(final List<Language> meiziList) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (Language meizi : meiziList) {
                        mManager.getDaoSession().insertOrReplace(meizi);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 更新数据
     * @param language
     */
    public void update(Language language){
        QueryBuilder<Language> queryBuilder = mManager.getDaoSession().queryBuilder(Language.class);
        Language tempLanguage=queryBuilder.where(LanguageDao.Properties.Langcode.eq(language.getLangcode())).build().unique();//拿到之前的记录
        if(tempLanguage !=null){
            tempLanguage.setLangcode(language.getLangcode());
            tempLanguage.setMomo(language.getMomo());
            mManager.getDaoSession().update(tempLanguage);
        }
    }

    /**
     * 修改一条数据
     * @param meizi
     * @return
     */
    public boolean updateLanguage(Language meizi){
        boolean flag = false;
        try {
            mManager.getDaoSession().update(meizi);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除单条记录
     * @param meizi
     * @return
     */
    public boolean deleteLanguage(Language meizi){
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().delete(meizi);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除所有记录
     * @return
     */
    public boolean deleteAll(){
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().deleteAll(Language.class);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询所有记录
     * @return
     */
    public List<Language> queryAllDevice(){
        return mManager.getDaoSession().loadAll(Language.class);
    }

    /**
     * 根据主键id查询记录
     * @param key
     * @return
     */
    public Language queryMeiziById(long key){
        return mManager.getDaoSession().load(Language.class, key);
    }



    /**
     * 使用native sql进行查询操作
     */
    public List<Language> queryLanguageByNativeSql(String sql, String[] conditions){
        return mManager.getDaoSession().queryRaw(Language.class, sql, conditions);
    }

    /**
     * 使用queryBuilder进行查询
     * @return
     */
    public Language queryLanguageByQueryBuilderLangID(String langid){
        QueryBuilder<Language> queryBuilder = mManager.getDaoSession().queryBuilder(Language.class);
        return queryBuilder.where(LanguageDao.Properties.Langid.eq(langid)).unique();
    }

    public Language queryLanguageByQueryBuilder(String code){
        QueryBuilder<Language> queryBuilder = mManager.getDaoSession().queryBuilder(Language.class);
        return queryBuilder.where(LanguageDao.Properties.Langcode.eq(code)).unique();
//        return queryBuilder.where(MeiziDao.Properties._id.ge(id)).list();
    }

}
