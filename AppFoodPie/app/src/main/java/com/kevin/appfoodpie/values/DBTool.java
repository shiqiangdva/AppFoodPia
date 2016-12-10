package com.kevin.appfoodpie.values;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by dllo on 16/12/8.
 */

public class DBTool {
    private static DBTool ourInstance = new DBTool();
    private static FoodDao foodDao;
    private static UrlDao urlDao;
    private static DataMoreDao dataMoreDao;

    public static DBTool getInstance(){
        if (ourInstance == null){
            synchronized (DBTool.class){
                if (ourInstance == null){
                    ourInstance = new DBTool();
                }
            }
        }
        dataMoreDao = MyApp.getDaoSession().getDataMoreDao();
        urlDao = MyApp.getDaoSession().getUrlDao();
        foodDao = MyApp.getDaoSession().getFoodDao();
        return ourInstance;
    }

    private DBTool(){

    }

    // 添加一条数据的方法
    public void insertPerson(Food food){
        foodDao.insert(food);
    }
    public void insertUrl(Url url){
        urlDao.insert(url);
    }
    public void insertData(DataMore dataMore){dataMoreDao.insert(dataMore);}

    // 删除所有
    public void deleteAll(){
        foodDao.deleteAll();
    }
    public void deleteUrlAll(){
        urlDao.deleteAll();
    }
    public void deleteDataAll(){dataMoreDao.deleteAll();}

//    public void deleteById(Long id){
//        foodDao.deleteByKey(id);
//    }

    // 根据某一字段删除
    public void deleteByName(String name){
        DeleteQuery<Food> deleteQuery = foodDao.queryBuilder().where(FoodDao.Properties.Name.eq(name)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
    }
    public void deleteByUrl(String url){
        DeleteQuery<Url> deleteQuery = urlDao.queryBuilder().where(UrlDao.Properties.Url.eq(url)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
    }
    public void deleteByData(String title){
        DeleteQuery<DataMore> deleteQuery = dataMoreDao.queryBuilder().where(DataMoreDao.Properties.Name.eq(title)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
    }



    // 查询所有
    public List<Food> queryAll(){
        List<Food> list = foodDao.loadAll();
        return list;
    }
    public List<Url> queryUrlAll(){
        List<Url> list = urlDao.loadAll();
        return list;
    }
    public List<DataMore> queryDataAll(){
        List<DataMore> list = dataMoreDao.loadAll();
        return list;
    }

    // 查重
    public boolean isSave(String name){
        QueryBuilder<Food> queryBuilder = foodDao.queryBuilder().where(FoodDao.Properties.Name.eq(name));
        Long size = queryBuilder.buildCount().count();
        return size > 0 ? true : false;
    }
    public boolean isUrlSave(String url){
            QueryBuilder<Url> queryBuilder = urlDao.queryBuilder().where(UrlDao.Properties.Url.eq(url));
        Long size = queryBuilder.buildCount().count();
        return size > 0 ? true : false;
    }
    public boolean isDataSave(String title){
        QueryBuilder<DataMore> queryBuilder = dataMoreDao.queryBuilder().where(DataMoreDao.Properties.Name.eq(title));
        Long size = queryBuilder.buildCount().count();
        return size > 0 ? true : false;
    }


    // 查一条
    public String urlGet(String id){
        Url queryBuilder = urlDao.queryBuilder().where(UrlDao.Properties.Id.eq(id)).build().unique();
        return queryBuilder.getUrl();
    }


}
