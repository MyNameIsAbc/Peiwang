package com.example.db;

import android.content.Context;

import com.orhanobut.logger.Logger;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class DeviceDaoUtils {
    private DaoManager mManager;

    public DeviceDaoUtils(Context context) {
        mManager = DaoManager.getInstance();
        mManager.init(context);
    }

    /**
     * 完成meizi记录的插入，如果表未创建，先创建Meizi表
     *
     * @param device
     * @return
     */
    public boolean insertMeizi(Device device) {
        boolean flag = false;
        flag = mManager.getDaoSession().getDeviceDao().insert(device) == -1 ? false : true;
        Logger.d("insert Device :" + flag + "-->" + device.toString());
        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     * @param meiziList
     * @return
     */
    public boolean insertMultMeizi(final List<Device> meiziList) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (Device meizi : meiziList) {
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
     * 修改一条数据
     * @param meizi
     * @return
     */
    public boolean updateDevice(Device meizi){
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
    public boolean deleteDevice(Device meizi){
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
            mManager.getDaoSession().deleteAll(Device.class);
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
    public List<Device> queryAllMeizi(){
        return mManager.getDaoSession().loadAll(Device.class);
    }

    /**
     * 根据主键id查询记录
     * @param key
     * @return
     */
    public Device queryMeiziById(long key){
        return mManager.getDaoSession().load(Device.class, key);
    }

    /**
     * 使用native sql进行查询操作
     */
    public List<Device> queryMeiziByNativeSql(String sql, String[] conditions){
        return mManager.getDaoSession().queryRaw(Device.class, sql, conditions);
    }

    /**
     * 使用queryBuilder进行查询
     * @return
     */
    public List<Device> queryMeiziByQueryBuilder(long id){
        QueryBuilder<Device> queryBuilder = mManager.getDaoSession().queryBuilder(Device.class);
        return queryBuilder.where(DeviceDao.Properties._id.eq(id)).list();
//        return queryBuilder.where(MeiziDao.Properties._id.ge(id)).list();
    }
}
